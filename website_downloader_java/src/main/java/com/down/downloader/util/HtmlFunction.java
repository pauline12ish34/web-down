package com.down.downloader.util;

public class HtmlFunction {
    // extract links from html
    public static String[] extractLinks(String html) {
        String[] links = html.split("<a href=\"");
        for(int i = 0; i < links.length; i++) {
            links[i] = links[i].split("\">")[0];
        }

        return links;
    }
}
