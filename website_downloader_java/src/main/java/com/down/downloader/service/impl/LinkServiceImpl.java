package com.down.downloader.service.impl;

import com.down.downloader.model.Link;
import com.down.downloader.model.Website;
import com.down.downloader.respositories.ILinkRepository;
import com.down.downloader.respositories.IWebsiteRepository;
import com.down.downloader.service.ILinkService;
import com.down.downloader.util.FileFunctions;
import com.down.downloader.util.UrlFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Vector;

@Service
public class LinkServiceImpl implements ILinkService {
    @Autowired
    private ILinkRepository linkRepository;

    @Autowired
    private IWebsiteRepository websiteRepository;

    @Override
    public List<Link> saveLinks(String[] urls, Website websiteId, String parentUrl) {
        for (int i =0; i < urls.length; i++) {
                Link link = new Link();
                LocalDateTime now = LocalDateTime.now();

                link.setLinkName(parentUrl+ urls[i]);
                link.setWebsite(websiteId);
                link.setTotalDownloadedKilobytes(0);
                link.setTotalElapseTime(0);


                  if (UrlFunctions.isValidUrl(parentUrl+urls[i]) &&
                  !urls[i].equals("./") &&
                  !urls[i].contains("#") &&
                  !urls[i].contains("?") &&
                  !urls[i].contains(":")&&
                  !urls[i].contains("=")) {
                      linkRepository.save(link);
                  }
        }

        System.out.println("List of extracted links from url: " + parentUrl);
        List<Link> downloadedLinks = linkRepository.findByWebsite(websiteId);
        return downloadedLinks;
    }

    @Override
    public List<Link> export(List<Link> links, Website website, String path) {
        LocalDateTime start = LocalDateTime.now();

        try {
            //loop in links downloading each link and saving it in the path
            for (int i = 0; i < links.size(); i++) {
                System.out.print("Downloading link: " + links.get(i).getLinkName() + ".");
                Link link = links.get(i);
                String url = link.getLinkName();
                String fileName = UrlFunctions.getDirectoryName(url);
                String fileContent = new FileExporterImpl().getHtml(url);
                Path filePath = Path.of(path+"\\"+fileName);
                Files.write(filePath, fileContent.getBytes());
                link.setTotalDownloadedKilobytes(fileContent.length() / 1024);
                System.out.print(".");
                LocalDateTime end = LocalDateTime.now();
                long milliSeconds = ChronoUnit.MILLIS.between(start, end);

                link.setTotalElapseTime(milliSeconds);
                website.setDownloadEndDateTime(LocalDateTime.now());
                website.setTotalElapseTime(ChronoUnit.MILLIS.between(website.getDownloadStartDateTime(), end));
                linkRepository.save(link);
                websiteRepository.save(website);
                System.out.println(".Done!");
            }

        }catch (Exception e){
            System.out.println("Error while writing file" + e.getMessage());
        }
        return links;
    }

    public List<Link> getExportedLinks(Website website){
        return linkRepository.findByWebsite(website);
    }

}
