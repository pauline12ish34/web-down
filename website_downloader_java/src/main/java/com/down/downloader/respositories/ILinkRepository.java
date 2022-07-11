package com.down.downloader.respositories;

import com.down.downloader.model.Link;
import com.down.downloader.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByWebsite(Website website);
}
