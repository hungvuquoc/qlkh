package com.example.qlkh.service.impl;

import com.example.qlkh.dto.FileDescriptionDto;
import com.example.qlkh.service.FileDescriptionService;
import com.example.qlkh.utils.EbsFileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileDescriptionServiceImpl implements FileDescriptionService {
    @Override
    public FileDescriptionDto upload(MultipartFile file) {
        return new FileDescriptionDto(EbsFileUtils.saveFile(file));
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public List<FileDescriptionDto> upload(MultipartFile[] files) {
        List<FileDescriptionDto> result = new ArrayList<>();
        for (MultipartFile file : files) {
            result.add(new FileDescriptionDto(EbsFileUtils.saveFile(file)));
        }
        return result;
    }

    @Override
    public void downloadById(HttpServletResponse response, Long fileId) throws IOException {
        FileDescriptionDto file = EbsFileUtils.getFile(fileId);

//        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setContentType(file.getContentType());
        response.setContentLengthLong(file.getContentSize());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        OutputStream outputStream = response.getOutputStream();
        file.getDataByte().writeTo(outputStream);
        outputStream.flush();
    }

    @Override
    public void getById(HttpServletResponse response, Long fileId) throws IOException {
        FileDescriptionDto file = EbsFileUtils.getFile(fileId);

        response.setContentType(file.getContentType());
        response.setContentLengthLong(file.getContentSize());

        OutputStream outputStream = response.getOutputStream();
        file.getDataByte().writeTo(outputStream);
        outputStream.flush();
    }
}
