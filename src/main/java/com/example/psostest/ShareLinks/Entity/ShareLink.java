package com.example.psostest.ShareLinks.Entity;

import com.example.psostest.Storage.Entity.FileEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "share_links")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShareLink {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String link;
    @Column
    private Boolean isActive;

    @ManyToMany
    @JoinTable(
            name = "sharelinks_files",
            joinColumns = @JoinColumn(name = "sharelink_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private List<FileEntity> resources;
}
