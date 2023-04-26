package com.example.psostest.Storage.Controller;


import com.example.psostest.Shared.Response.ResponseWithMessage;
import com.example.psostest.Storage.Entity.FileEntity;
import com.example.psostest.Storage.Model.FileInfo;
import com.example.psostest.Storage.Service.StorageService;
import com.example.psostest.User.Entity.User;
import com.example.psostest.User.Service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8081")
public class FilesController {

    private final UsersService usersService;
    private final StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseWithMessage> uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            User user = usersService.getLoggedUser(request);
            storageService.save(user, file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWithMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseWithMessage(message));
        }
    }

    @GetMapping("/all_files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files")
    @ResponseBody
    public ResponseEntity<List<FileEntity>> getAllUserFiles(HttpServletRequest request) {
        User u = usersService.getLoggedUser(request);
        List<FileEntity> files = storageService.getAllFilesFromUser(u);
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{userDir}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(HttpServletRequest request, @PathVariable String userDir, @PathVariable String filename) {
        try {
            User user = usersService.getLoggedUser(request);
            Resource file = storageService.load(userDir, user, filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseWithMessage(e.getMessage()));
        }
    }
}