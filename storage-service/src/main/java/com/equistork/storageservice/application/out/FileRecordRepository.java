package com.equistork.storageservice.application.out;

import com.equistork.storageservice.domain.model.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRecordRepository extends JpaRepository<FileMetadata, Long> {
}
