package com.example.psostest.ShareLinks.Repository;

import com.example.psostest.ShareLinks.Entity.ShareLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ShareLinkRepository extends JpaRepository<ShareLink, Integer> {
    Optional<ShareLink> findShareLinkByLink(String link);
}
