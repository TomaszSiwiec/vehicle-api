package com.equistork.storageservice.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
public class FileMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String folderName;
    private String filename;
    private String extension;
    private Long size;

    public String getFileKey() {
        return String.format("%s/%s", folderName, filename);
    }
}
