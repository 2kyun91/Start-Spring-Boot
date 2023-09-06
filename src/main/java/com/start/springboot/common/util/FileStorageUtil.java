package com.start.springboot.common.util;

import com.start.springboot.common.errors.errorcode.CommonErrorCode;
import com.start.springboot.common.errors.exception.CustomException;
import com.start.springboot.domain.attach.dto.AttachDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Component
public class FileStorageUtil {
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;
    private List<AttachDto> attachDtos = new ArrayList<>();

    private String CreatePhysicalFileName(String originalFileName) {
        String ext = extractExt(originalFileName);
        return new SimpleDateFormat("yyyyMMdd").format(new Date()) + "_" + UUID.randomUUID().toString() + "." + ext;
    }

    private String extractExt(String originalFileName) {
        int index = originalFileName.lastIndexOf(".");
        return originalFileName.substring(index + 1);
    }

    public void uploadFiles(List<MultipartFile> files) {
        attachDtos.clear();
        if (!files.isEmpty()) {
            files.stream().forEach(file -> {
                String originalFileName = file.getOriginalFilename();
                if (!originalFileName.isEmpty()) {
                    String physicalFileName = CreatePhysicalFileName(originalFileName);
                    Long fileSize = file.getSize();
                    String fileType = file.getContentType();

                    if (!new File(uploadPath).exists()) {
                        new File(uploadPath).mkdir();
                    }
                    String physicalFilePath = uploadPath + "/" + physicalFileName;

                    try {
                        file.transferTo(new File(physicalFilePath));

                        AttachDto attachDto = new AttachDto();
                        attachDto.setAttachPath(physicalFilePath);
                        attachDto.setAttachOriginalName(originalFileName);
                        attachDto.setAttachPhysicalName(physicalFileName);

                        attachDtos.add(attachDto);
                    } catch (IOException e) {
                        throw new CustomException(CommonErrorCode.FILE_NOT_FOUND);
                    }
                }
            });
        }
    }

    public Path load(String filename) {
        return Paths.get(uploadPath).resolve(filename);
    }

    public Resource downloadFile(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new MalformedURLException("Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }
}
