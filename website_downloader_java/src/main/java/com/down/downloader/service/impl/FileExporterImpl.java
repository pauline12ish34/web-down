package com.down.downloader.service.impl;

import com.down.downloader.model.Website;
import com.down.downloader.respositories.IWebsiteRepository;
import com.down.downloader.service.IFileExporter;
import com.down.downloader.util.HtmlFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@Service
public class FileExporterImpl implements IFileExporter {

     @Autowired
     private IWebsiteRepository websiteRepository;


        @Override
        public String getHtml(String url) throws IOException, InterruptedException {
//            get html from url
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }

        @Override
        public Website export(String fileContent, String pathName, String fileName, String websiteName){
            try {
                LocalDateTime now = LocalDateTime.now();

                Path filePath = Path.of(pathName+"\\"+fileName);
                Files.write(filePath, fileContent.getBytes());

                File file = new File(pathName+"\\"+fileName);

                LocalDateTime end = LocalDateTime.now();

                float fileSizeInKB = file.length() / 1024;
                Website website = new Website();
                website.setWebsiteName(websiteName);
                website.setDownloadStartDateTime(now);
                website.setDownloadEndDateTime(end);
                website.setTotalElapseTime(ChronoUnit.MILLIS.between(now, end));
                website.setTotalDownloadedKilobytes(fileSizeInKB);
                websiteRepository.save(website);

                // get links in html document

                return website;
            }catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }

        public Website getWebsiteByName(String websiteName){
            return websiteRepository.findByWebsiteName(websiteName);
        }

}
