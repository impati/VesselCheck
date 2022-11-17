package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.entity.Block;
import com.example.vesselcheck.domain.entity.Components;
import com.example.vesselcheck.domain.service.Dto.ComponentForm;
import com.example.vesselcheck.domain.service.Dto.UploadFile;
import com.example.vesselcheck.web.api.dto.ComponentReForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileStore {
    @Value("${file.dir}")
    private String fileDir;
    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public List<Components> storeFiles(Block block , ComponentForm componentForm) throws IOException {
        List<Components> componentsList = new ArrayList<>();
        for (int i = 0 ; i <  componentForm.getImageUploadName().size();i++) {
            MultipartFile multipartFile = componentForm.getImageUploadName().get(i);
            if (!multipartFile.isEmpty()) {

                String componentName = componentForm.getComponentName().get(i);
                String sequenceNumber =componentForm.getSequenceNumber().get(i);
                String originName = multipartFile.getOriginalFilename();
                String storeFileName = createStoreFileName(originName);
                componentsList.add(Components.createComponent(block,componentName,
                        sequenceNumber,originName,storeFileName));
                multipartFile.transferTo(new File(getFullPath(storeFileName)));//multipartFile 파일을 저장 pullPath에 저장.
            }
        }
        return componentsList;
    }
    public String restoreFile(MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            String originName = multipartFile.getOriginalFilename();
            String storeFileName = createStoreFileName(originName);
            multipartFile.transferTo(new File(getFullPath(storeFileName)));//multipartFile 파일을 저장 pullPath에 저장
            return storeFileName;
        }
        return null;
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}

