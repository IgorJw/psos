package com.example.psostest.User.Service;

import com.example.psostest.Auth.Token.Repository.TokenRepository;
import com.example.psostest.Config.Service.JwtService;
import com.example.psostest.Storage.Entity.FileEntity;
import com.example.psostest.User.Entity.User;
import com.example.psostest.User.Repository.UserRepository;
import com.example.psostest.User.Repository.UsersBasicInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UsersService {

    private UsersBasicInfoRepository usersBasicInfoRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    public User getLoggedUser(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        var jwt = authHeader.substring(7);
        return userRepository.findByUsername(jwtService.extractUsername(jwt)).orElseThrow(
                NoSuchElementException::new
        );
    }

    public Boolean hasFileAccess(User u, FileEntity f) {
        List<FileEntity> files = u.getFiles();
        return files.contains(f);

    }

}
