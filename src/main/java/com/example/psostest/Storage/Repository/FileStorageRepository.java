package com.example.psostest.Storage.Repository;

import com.example.psostest.Storage.Entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileStorageRepository extends JpaRepository<FileEntity, Integer> {
    FileEntity findFileStorageByUrl(String url);

    @Query("select f from FileEntity f join User ON :userId = f.id")
    List<FileEntity> findAllByUser(@Param("userId") Integer userId);
}
