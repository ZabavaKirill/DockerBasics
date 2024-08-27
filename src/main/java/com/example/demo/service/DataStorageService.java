package com.example.demo.service;

import com.example.demo.dto.FileInfoDto;
import com.example.demo.jpa.FileInfoEntity;
import com.example.demo.jpa.FileInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataStorageService {

    private final static Path IMAGE_DIR = Paths.get("images");

    private final FileInfoRepository repository;

    public List<FileInfoDto> getAllFiles() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public String deleteFile(Integer id) {
        FileInfoEntity fileInfo = repository.findById(id).orElse(null);

        if (fileInfo == null)
            return "File " + id + " not found";

        try {
            Path destinactionFile = IMAGE_DIR.resolve(fileInfo.getFilename()).normalize().toAbsolutePath();
            Files.delete(destinactionFile);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        repository.delete(fileInfo);
        return "File " + id + " deleted";
    }

    public void store(String fileName, MultipartFile file) {
        try {
            init();
            Path destinationFile = IMAGE_DIR.resolve(fileName).normalize().toAbsolutePath();
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            repository.save(new FileInfoEntity(fileName, file.getSize(), Timestamp.from(Instant.now())));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void init() throws IOException {
        if (!Files.exists(IMAGE_DIR))
            Files.createDirectory(IMAGE_DIR);
    }

    private FileInfoDto toDto(FileInfoEntity entity) {
        return new FileInfoDto(entity.getId(), entity.getFilename(), entity.getSize(), entity.getLastModified());
    }
}
