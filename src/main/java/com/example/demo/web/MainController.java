package com.example.demo.web;

import com.example.demo.service.DataStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


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

}
