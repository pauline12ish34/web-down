package com.down.downloader.controller;

import com.down.downloader.model.Link;
import com.down.downloader.model.Website;
import com.down.downloader.service.impl.FileExporterImpl;
import com.down.downloader.service.impl.LinkServiceImpl;
import com.down.downloader.util.FileFunctions;
import com.down.downloader.util.HtmlFunction;
import com.down.downloader.util.Report;
import com.down.downloader.util.UrlFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping
public class DownloadController {

    @Autowired
    private  FileExporterImpl fileExporter;

    @Autowired
    private LinkServiceImpl linkService;

    @GetMapping
    public String download() {
        return "index";
    }

    // download .html file from url
    @GetMapping("/html")
    public String downloadHtml(HttpServletRequest request, Model model) throws IOException, InterruptedException {
        String url = request.getParameter("url");
        if(UrlFunctions.isValidUrl(url)) {
            String domainName = UrlFunctions.getDomainName(url);
            String createDir = FileFunctions.makeDirectory(domainName);
            String directoryName = UrlFunctions.getDirectoryName(url);
            String websiteName = UrlFunctions.getWebsiteName(url);
            String html = fileExporter.getHtml(url);

            //register website in database
            Website website = fileExporter.export(html, createDir, directoryName, websiteName);

            //get links in html document
            String[] links = HtmlFunction.extractLinks(html);

            // save links in database
            List<Link> saveLinks = linkService.saveLinks(links, website, url);

             // export links
            List<Link> exported = linkService.export(saveLinks, website, createDir);

            Report.printReport(website, exported);

            model.addAttribute("websiteName", website.getWebsiteName());
            model.addAttribute("exported", exported);

            return "redirect:/?success=true";
        } else {
            return "redirect:/?error=true&description=Invalid url";
        }
    }

    @GetMapping("/report")
    public String report() {
        return "report";
    }

    // get report by website name
    @PostMapping("/report")
    public String postReport(HttpServletRequest request, Model model) {
        String websiteName = request.getParameter("websiteName");
        System.out.println(websiteName);
       try {
           Website website = fileExporter.getWebsiteByName(websiteName);
           List<Link> exported = linkService.getExportedLinks(website);
           Report.printReport(website, exported);

           request.getSession().setAttribute("website", Objects.requireNonNull(website));
           request.getSession().setAttribute("links", Objects.requireNonNull(exported));

           return "redirect:/report?success=true";
       }catch (Exception e) {
           return "redirect:/report?error=true&description=Website not found";
       }
    }
}
