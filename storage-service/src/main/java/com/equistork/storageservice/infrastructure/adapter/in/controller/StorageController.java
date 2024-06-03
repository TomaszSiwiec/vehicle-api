package com.equistork.storageservice.infrastructure.adapter.in.controller;

import com.equistork.storageservice.application.in.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class StorageController {

    private final FileService fileService;

    @PostMapping
    public ResponseEntity<Long> uploadFile(@RequestParam("file") MultipartFile file) {
        Long id = fileService.uploadFile(file);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InputStream> downloadFile(@PathVariable long id) {
        InputStream fileStream = fileService.downloadFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileId=" + id)
                .body(fileStream);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFile(@PathVariable long id) {
        fileService.deleteFile(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(204))
                .build();
    }
}
