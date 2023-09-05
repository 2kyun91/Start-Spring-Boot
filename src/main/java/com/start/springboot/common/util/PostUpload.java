package com.start.springboot.common.util;

import com.start.springboot.common.errors.errorcode.CommonErrorCode;
import com.start.springboot.common.errors.exception.CustomException;
import com.start.springboot.domain.attach.dto.AttachDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Component
public class PostUpload {
    private List<AttachDto> attachDtos = new ArrayList<>();

    private String CreatePhysicalFileName(String originalFileName) {
        String ext = extractExt(originalFileName);
        return new SimpleDateFormat("yyyyMMdd").format(new Date()) + "_" + UUID.randomUUID().toString() + "." + ext;
    }

    private String extractExt(String originalFileName) {
        int index = originalFileName.lastIndexOf(".");
        return originalFileName.substring(index + 1);
    }

    public void saveFiles(List<MultipartFile> files) {
        if (!files.isEmpty()) {
            files.stream().forEach(file -> {
                String originalFileName = file.getOriginalFilename();
                if (!originalFileName.isEmpty()) {
                    String physicalFileName = CreatePhysicalFileName(originalFileName);
                    Long fileSize = file.getSize();
                    String fileType = file.getContentType();
                    String uploadPath = "C:/uploadTestFolder";

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
}
