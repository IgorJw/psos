package com.example.psostest.ShareLinks.Service;

import com.example.psostest.ShareLinks.Entity.ShareLink;
import com.example.psostest.ShareLinks.Repository.ShareLinkRepository;
import com.example.psostest.Storage.Entity.FileEntity;
import com.example.psostest.Storage.Repository.FileStorageRepository;
import com.example.psostest.User.Entity.User;
import com.example.psostest.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ShareLinkService {

    private final ShareLinkRepository shareLinkRepository;
    private final FileStorageRepository fileStorageRepository;
    private final UserRepository userRepository;

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 20;

    private static String generateRandomLink() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public String createShareLinkForFiles(List<FileEntity> files) {
        String link = generateRandomLink();
        ShareLink shareLink = ShareLink
                .builder()
                .link(link)
                .resources(new LinkedList<>())
                .isActive(true)
                .build();
        for (FileEntity file : files) {
            file.getShareLinks().add(shareLink);
            fileStorageRepository.save(file);
            shareLink.getResources().add(file);
        }
        shareLinkRepository.save(shareLink);
        return shareLink.getLink();
    }

    public void processShareLink(String link, User user) {
        ShareLink shareLink = shareLinkRepository.findShareLinkByLink(link).orElseThrow(
                NoSuchElementException::new
        );

        List<FileEntity> files = shareLink.getResources();
        for (FileEntity file : files) {
            user.getFiles().add(file);
            file.getUsers().add(user);
            fileStorageRepository.save(file);
        }
        userRepository.save(user);
    }
}
