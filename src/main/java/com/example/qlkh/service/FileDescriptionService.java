package com.example.qlkh.service;

import com.example.qlkh.dto.FileDescriptionDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface FileDescriptionService {
    FileDescriptionDto upload(MultipartFile file);
    List<FileDescriptionDto> upload(MultipartFile[] files);
    void downloadById(HttpServletResponse response, Long fileId) throws IOException;
    void getById(HttpServletResponse response, Long fileId) throws IOException;
}
