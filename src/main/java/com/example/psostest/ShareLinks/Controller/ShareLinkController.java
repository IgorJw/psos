package com.example.psostest.ShareLinks.Controller;

import com.example.psostest.ShareLinks.Request.GenerateLinkRequest;
import com.example.psostest.ShareLinks.Service.ShareLinkService;
import com.example.psostest.Shared.Response.ResponseWithMessage;
import com.example.psostest.Storage.Entity.FileEntity;
import com.example.psostest.Storage.Repository.FileStorageRepository;
import com.example.psostest.User.Entity.User;
import com.example.psostest.User.Service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class ShareLinkController {

    private final UsersService usersService;
    private final ShareLinkService shareLinkService;
    private final FileStorageRepository fileStorageRepository;

    @PostMapping("/share/generate-link")
    public ResponseEntity<String> generateLink(@RequestBody GenerateLinkRequest request) {
        List<FileEntity> files = new LinkedList<>();
        for (Integer id : request.getFileIds()) {
            FileEntity f = fileStorageRepository.getReferenceById(id);
            files.add(f);
        }
        String link = shareLinkService.createShareLinkForFiles(files);
        return ResponseEntity.status(HttpStatus.OK).body(link);
    }

    @PostMapping("/share/process-link/{link}")
    public ResponseEntity<ResponseWithMessage> processLink(HttpServletRequest request, @PathVariable String link) {
        try {
            User user = usersService.getLoggedUser(request);
            shareLinkService.processShareLink(link, user);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseWithMessage("Success")
            );
        } catch (Exception e) {
            String message = "Process share-link: Something went wrong";
            if (e instanceof NoSuchElementException) {
                message = "Provided link is invalid";
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseWithMessage(message)
            );
        }
    }
}
