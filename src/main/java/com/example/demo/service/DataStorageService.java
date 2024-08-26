package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class DataStorageService {

    private final static Path IMAGE_DIR = Paths.get("images");

    public void store(String fileName, MultipartFile file) {
        try {
            init();
            Path destinationFile = IMAGE_DIR.resolve(fileName).normalize().toAbsolutePath();
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void init() throws IOException {
        if (!Files.exists(IMAGE_DIR))
            Files.createDirectory(IMAGE_DIR);
    }
}
