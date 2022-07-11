package com.down.downloader.service;

import com.down.downloader.model.Link;
import com.down.downloader.model.Website;

import java.util.List;

public interface ILinkService {
    public List<Link> saveLinks(String[] url, Website websiteId, String parentUrl);
    public List<Link> export(List<Link> links, Website website, String path);
}
