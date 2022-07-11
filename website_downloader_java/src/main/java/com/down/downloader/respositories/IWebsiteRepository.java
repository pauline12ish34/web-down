package com.down.downloader.respositories;

import com.down.downloader.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWebsiteRepository extends JpaRepository<Website, Long> {
    Website findByWebsiteName(String websiteName);
}
