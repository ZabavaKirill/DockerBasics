package com.example.demo.web;

import com.example.demo.dto.FileInfoDto;
import com.example.demo.service.DataStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@Tag(name = "MainController", description = "Главный контроллер")
@RequiredArgsConstructor
public class MainController {

    private final DataStorageService service;


    @GetMapping("/")
    @Operation(description = "Hello World")
    public String home() {
        return "Hello World!";
    }

    @Operation(description = "Загрузка файла и получение его размера")
    @PostMapping(path = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String loadFile(@RequestParam("fileName") String fileName,
                           @RequestParam("file") MultipartFile file) {
        service.store(fileName, file);
        return "File uploaded. File size: " + file.getSize() + " bytes";
    }

    @Operation(description = "Получение списка файлов")
    @GetMapping("/files")
    public List<FileInfoDto> getFiles() {
        return service.getAllFiles();
    }

    @Operation(description = "Удаление файла")
    @DeleteMapping("/files/{id}")
    public String deleteFile(@PathVariable(name = "id") Integer id) {
        return service.deleteFile(id);
    }
}
