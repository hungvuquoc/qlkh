package com.example.qlkh.utils;

import com.example.qlkh.constant.enums.DirectoryType;
import com.example.qlkh.error.status.CommonStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.dto.FileDescriptionDto;
import com.example.qlkh.entity.FileDescription;
import com.example.qlkh.entity.FileCache;
import com.example.qlkh.repository.FileDescriptionRepository;
import com.example.qlkh.repository.FileCacheRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EbsFileUtils {
    private final FileDescriptionRepository fileDescriptionRepository;
    private final FileCacheRepository fileCacheRepository;

    private final Environment environment;

    private static final Logger log = LoggerFactory.getLogger(EbsFileUtils.class);

    private static final String patternDateTime = "yyyyMMdd_HHmmssSSS";

    private static final DateFormat dateFormat = new SimpleDateFormat(patternDateTime);

    private static FileDescriptionRepository staticFileDescriptionRepository;

    private static FileCacheRepository staticFileCacheRepository;

    private static String path;

    public EbsFileUtils(FileDescriptionRepository fileDescriptionRepository, FileCacheRepository fileCacheRepository, Environment environment) {
        this.fileDescriptionRepository = fileDescriptionRepository;
        this.fileCacheRepository = fileCacheRepository;
        this.environment = environment;
    }

    @PostConstruct
    private void init() {
        staticFileDescriptionRepository = this.fileDescriptionRepository;
        staticFileCacheRepository = this.fileCacheRepository;
        path = this.environment.getProperty("upload.path");
        if (!StringUtils.hasText(path)) { // nếu không chỉ định nơi lưu trữ sẽ mặc định ở cùng thư mục với chương trình
            System.getProperty("user.dir");
            Path currentPath = Paths.get(System.getProperty("user.dir"));
            path = currentPath.getParent().toString();
        }
    }

    public static FileDescription saveFile(MultipartFile file) {
        try {
            String dateToString = dateFormat.format(new Date());

            String baseName = FilenameUtils.getBaseName(file.getOriginalFilename());
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String fileName = String.format("%s_%s.%s", dateToString, baseName, extension);

            Path folder = Paths.get(path, DirectoryType.getByExtension(extension)).normalize();
            Files.createDirectories(folder);

            File fileToSave = folder.resolve(fileName).toFile();
            file.transferTo(fileToSave);

            FileDescription fileDescription = new FileDescription();
            fileDescription.setContentSize(file.getSize());
            fileDescription.setContentType(file.getContentType());
            fileDescription.setExtension(extension);
            fileDescription.setName(fileName);
            fileDescription.setFilePath(folder.toString());
            fileDescription = staticFileDescriptionRepository.save(fileDescription);
            return fileDescription;
        } catch (IOException e) {
            throw new DataRuntimeException(CommonStatus.INPUT_OUTPUT_FILE_ERROR, e);
        }
    }

    public static FileDescriptionDto getFile(Long fileId) {
        FileDescription fileDescription = staticFileDescriptionRepository.findById(fileId)
                .orElseThrow(() -> new DataRuntimeException(CommonStatus.FIND_FILE_ERROR));

        File file = new File(fileDescription.getFilePath(), fileDescription.getName());
        log.info("Get file: {}", ToStringBuilder.reflectionToString(file));
        if (!file.exists())
            throw new DataRuntimeException(CommonStatus.FIND_FILE_ERROR);

        try (FileInputStream in = new FileInputStream(file);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            IOUtils.copy(in, out);
            return new FileDescriptionDto(fileDescription, out);
        } catch (IOException e) {
            throw new DataRuntimeException(CommonStatus.INPUT_OUTPUT_FILE_ERROR, e);
        }
    }

    public static void delete(String path) {
        if (!StringUtils.hasText(path))
            return;

        File file = new File(path);
        if (!file.exists())
            return;

        if (file.delete())
            log.info("delete file success: {}", path);
        else
            log.info("delete file fail: {}", path);
    }

    public static void delete(@NotNull FileDescription entity) {
        String entityPath = Paths.get(entity.getFilePath(), entity.getName()).toString();
        delete(entityPath);
    }

    public static void tickFileToDelete(FileDescription fileDescription) {
        FileCache entity = new FileCache();
        Path path = Paths.get(fileDescription.getFilePath(), fileDescription.getName()).normalize();
        entity.setPath(path.toString());
        staticFileCacheRepository.save(entity);
    }
}
