package com.example.psostest.Storage.Repository;

import com.example.psostest.Storage.Entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStorageRepository extends JpaRepository<FileEntity,Integer> {
    FileEntity findFileStorageByUrl(String url);
}
