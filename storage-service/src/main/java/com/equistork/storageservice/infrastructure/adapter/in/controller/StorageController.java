package com.equistork.storageservice.infrastructure.adapter.in.controller;

import com.equistork.storageservice.application.in.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<String> downloadFile(@PathVariable long id) {
        String path = fileService.downloadFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileId=" + id)
                .body(path);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFile(@PathVariable long id) {
        fileService.deleteFile(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(204))
                .build();
    }
}
