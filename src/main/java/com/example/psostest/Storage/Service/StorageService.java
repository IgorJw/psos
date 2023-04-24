package com.example.psostest.Storage.Service;

import com.example.psostest.Storage.Entity.FileEntity;
import com.example.psostest.Storage.Repository.FileStorageRepository;
import com.example.psostest.User.Entity.User;
import com.example.psostest.User.Repository.UserRepository;
import com.example.psostest.User.Service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class StorageService {

    private final FileStorageRepository fileStorageRepository;
    private final UsersService usersService;
    private final UserRepository userRepository;
    private final Path root = Paths.get("uploads");

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public void save(User user, MultipartFile file) {
        try {
            Path userDir = root.resolve(user.getUsername());
            FileEntity f = FileEntity
                    .builder()
                    .url("/uploads/" + user.getUsername() + "/" + file.getOriginalFilename())
                    .build();
            fileStorageRepository.save(f);
            user.getFiles().add(f);
            userRepository.save(user);
            Files.createDirectories(userDir);
            Files.copy(file.getInputStream(), userDir.resolve(file.getOriginalFilename()));

        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }

    public Resource load(String userDirString, User user, String filename) {
        try {
            System.out.println("/uploads/" + userDirString + filename);
            FileEntity f = fileStorageRepository.findFileStorageByUrl("/uploads/" + userDirString + "/" + filename);

            if (f == null) {
                throw new NoSuchElementException();
            }

            if (!usersService.hasFileAccess(user, f)) {
                throw new RuntimeException("User has no access to this file");
            }

            Path userDir = root.resolve(user.getUsername());
            Path file = userDir.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Error: No such file");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }


    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
