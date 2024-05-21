package com.equistork.storageservice.domain.model;

import lombok.Builder;

@Builder
public record FileMetadata(
        String fileName,
        long fileSize
) {
}
