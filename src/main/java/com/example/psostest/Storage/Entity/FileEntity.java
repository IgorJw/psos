package com.example.psostest.Storage.Entity;

import com.example.psostest.ShareLinks.Entity.ShareLink;
import com.example.psostest.User.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url;

    @ManyToMany(mappedBy = "files")
    @JsonIgnore
    private Set<User> users;

    @ManyToMany(mappedBy = "resources")
    private List<ShareLink> shareLinks;
}
