package com.example.qlkh.rest;

import com.example.qlkh.dto.FileDescriptionDto;
import com.example.qlkh.service.FileDescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileDescriptionController {
    private final FileDescriptionService fileDescriptionService;

    @PostMapping("/upload")
    public FileDescriptionDto upload(@RequestParam MultipartFile file) {
        return fileDescriptionService.upload(file);
    }

    @PostMapping("/uploads")
    public List<FileDescriptionDto> uploads(@RequestParam MultipartFile[] files) {
        return fileDescriptionService.upload(files);
    }

    @GetMapping("/{fileId}")
    public void downloadById(HttpServletResponse response, @PathVariable Long fileId) throws IOException {
        fileDescriptionService.downloadById(response, fileId);
    }
}
