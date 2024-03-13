package com.example.qlkh.rest;

import com.example.qlkh.dto.TokenDto;
import com.example.qlkh.dto.UserDto;
import com.example.qlkh.service.FileDescriptionService;
import com.example.qlkh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PublicController {
    private final UserService userService;
    private final FileDescriptionService fileDescriptionService;

//    @GetMapping("/change-password/{username}")
//    public boolean createVerificationToChangePassword(@PathVariable("username") String username) {
//        return userService.createVerificationToChangePassword(username);
//    }

//    @GetMapping("/register/{username}")
//    public boolean createVerificationToRegister(@PathVariable("username") String username) {
//        return userService.createVerificationToRegister(username);
//    }

    @PostMapping("/change-password")
    public boolean changePassword(@RequestBody UserDto dto) {
        return userService.changePassword(dto);
    }

//    @PostMapping("/register")
//    public TokenDto register(@RequestBody UserDto userDto) {
//        return userService.register(userDto);
//    }

    @PutMapping("/refresh-token")
    public TokenDto refreshToken(@RequestParam("token") String token) {
        return userService.refreshToken(token);
    }

//    @GetMapping("/file/{id}")
//    public void getFileById(HttpServletResponse response, @PathVariable Long id) throws IOException {
//        fileDescriptionService.getById(response, id);
//    }
}
