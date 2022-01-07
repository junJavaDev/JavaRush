package com.javarush.task.pro.task09.task0912;

/* 
Проверка URL-адреса
*/

public class Solution {
    public static void main(String[] args) {
        String[] urls = {"https://javarush.ru", "https://google.com", "http://wikipedia.org", "facebook.com", "https://instagram", "codegym.cc"};
        for (String url : urls) {
            String protocol = checkProtocol(url);
            String domain = checkDomain(url);

            System.out.println("У URL-адреса - " + url + ", сетевой протокол - " + protocol + ", домен - " + domain);
        }
    }

    public static String checkProtocol(String url) {
        int protocolEnds = url.indexOf("://");
        if (protocolEnds > 0) {
            String protocol = url.substring(0, protocolEnds);
            return switch (protocol) {
                case "http" -> "http";
                case "https" -> "https";
                default -> "неизвестный";
            };
        }
        else return "неизвестный";
    }

    public static String checkDomain(String url) {
        int domainStarts = url.lastIndexOf('.') + 1;
        if (domainStarts > 0) {
            String domain = url.substring(domainStarts);
            return switch (domain) {
                case "ru" -> "ru";
                case "com" -> "com";
                case "net" -> "net";
                case "org" -> "org";
                default -> "неизвестный";
            };
        }
        else return "неизвестный";
    }
}
