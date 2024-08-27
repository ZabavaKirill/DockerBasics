package com.example.demo.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "file_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoEntity {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "file_info_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_info_seq")
    private Integer id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "size")
    private Long size;

    @Column(name = "last_modified")
    private Timestamp lastModified;

    public FileInfoEntity(String filename, Long size, Timestamp lastModified) {
        this.filename = filename;
        this.size = size;
        this.lastModified = lastModified;
    }
}

