package com.down.downloader.util;

import com.down.downloader.model.Link;
import com.down.downloader.model.Website;

import java.util.List;

public class Report {
    public static void printReport(Website website, List<Link> links) {
        System.out.println("\n\n|---------------------------------------------------------------------------------------------------------------------|");
        System.out.println("Website Name: " + website.getWebsiteName());
        System.out.println("Total downloaded kilobytes: " + website.getTotalDownloadedKilobytes());
        System.out.println("Start Time: " + website.getDownloadStartDateTime());
        System.out.println("End Time: " + website.getDownloadEndDateTime());
        System.out.println("Total elapse time: " + website.getTotalElapseTime()+" milliseconds");
        System.out.println("|---------------------------------------------------------------------------------------------------------------------|\n");


        //loop in list of links
        for (Link link : links) {
            int index = links.indexOf(link)+1;
            System.out.println("\nLink No "+ index);
            System.out.println("Link Name: " + link.getLinkName());
            System.out.println("Total downloaded kilobytes: " + link.getTotalDownloadedKilobytes());
            System.out.println("Total elapse time: " + link.getTotalElapseTime() + " milliseconds");
        }
        System.out.println("|---------------------------------------------------------------------------------------------------------------------|");
    }
}
