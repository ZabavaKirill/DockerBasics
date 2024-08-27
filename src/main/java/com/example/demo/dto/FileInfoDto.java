package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Schema(title = "Информация о файле")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoDto {

    @Schema(title = "Идентификатор")
    private Integer id;

    @Schema(title = "Наименование файла")
    private String fileName;

    @Schema(title = "Размер файла в байтах")
    private Long size;

    @Schema(title = "Дата и время изменения файла")
    private Timestamp lastModified;

}
