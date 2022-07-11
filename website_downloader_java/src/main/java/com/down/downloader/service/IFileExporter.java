package com.down.downloader.service;

import com.down.downloader.model.Website;

import java.io.IOException;
import java.nio.file.Path;

public interface IFileExporter {
    public String getHtml(String url) throws IOException, InterruptedException;
    public Website export(String fileContent, String pathName, String fileName, String webSiteName) throws IOException;
}
