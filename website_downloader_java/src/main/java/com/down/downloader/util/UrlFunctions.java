package com.down.downloader.util;

public class UrlFunctions {
    public static boolean isValidUrl(String url) {
        return url.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }

    // get domain name
    public static String getDomainName(String url) {
        String domainName = url.replaceAll("^(https?|ftp|file)://", "");
        domainName = domainName.replaceAll("/.*$", "");
        //replace everything after . with .html
        domainName = domainName.replaceAll("\\..*$", "");
        return domainName;
    }

    //get website name
    public static String getWebsiteName(String url) {
        String websiteName = url.replaceAll("^(https?|ftp|file)://", "");
        websiteName = websiteName.replaceAll("/.*$", "");
        websiteName = websiteName.replaceAll("\\..*$", "");
        return websiteName;
    }

    //get directory name
    public static String getDirectoryName(String url) {
        //get last directory name in url
        String directoryName = url.replaceAll("^(https?|ftp|file)://", "");
        directoryName = directoryName.replaceAll("^.*/", "");
        //replace everything after . with .html
        directoryName = directoryName.replaceAll("\\..*$", "");

        if(!directoryName.isEmpty()) {
            return directoryName+".html";
        } else {
            return "index.html";
        }
    }
}