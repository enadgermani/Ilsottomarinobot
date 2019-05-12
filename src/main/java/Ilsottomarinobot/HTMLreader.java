

package com.amazonaws.lambda.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLreader {
    public HTMLreader() {
    }

    public HTMLreader(String link) {
    }

    public static ArrayList<String> agisci(String start, String end) {
        String prova = findLink();
        System.out.println(prova);
        Document d = null;

        try {
            d = Jsoup.connect(prova).get();
        } catch (IOException var21) {
            var21.printStackTrace();
        }

        String html = d.toString();
        Document d2 = null;

        try {
            d2 = Jsoup.connect(prova).get();
        } catch (IOException var20) {
            var20.printStackTrace();
        }

        String source = d2.toString();
        System.out.println("seconda html trovata");
        int inizio = source.indexOf(start);
        int fine = source.indexOf(end);
        String cut = source.substring(inizio + 188, fine - 3) + "§";
        System.out.println("inizio" + inizio);
        System.out.println("fine" + fine);
        Document doc = Jsoup.parse(cut);
        Elements p = doc.select("p");
        ArrayList<String> articoli = new ArrayList();
        String s = "";
        String s1 = "";
        Iterator var16 = p.iterator();
        
        while(var16.hasNext()) {
            Element par = (Element)var16.next();
            s = par.text();
            Elements a = par.select("a[href]");

            for(Iterator var19 = a.iterator(); var19.hasNext(); s1 = "") {
                Element links = (Element)var19.next();
                s1 = s1.concat("  ");
                s1 = s1.concat(links.attr("href"));
                articoli.add(s + s1 + "¿º");
                s = "";
            }
        }

        return articoli;
    }

    public static String findLink() {
        System.out.println("cerco il link");
        String base = "https://thesubmarine.it/author/redazione/";
        Document d = null;

        try {
            d = Jsoup.connect(base).get();
        } catch (IOException var13) {
            var13.printStackTrace();
        }

        String html = d.toString();
        String html2 = html.substring(html.indexOf("div id=\"cb-content\" class=\"wrap cb-author-page cb-wrap-pad clearfix\">"));
        Document doc = Jsoup.parse(html2);
        Elements p = doc.select("h2");
        String s = "";
        String s1 = "";
        Iterator var9 = p.iterator();
        if (!var9.hasNext()) {
            return "ciao";
        } else {
            Element par = (Element)var9.next();
            s = par.text();
            Elements a = par.select("a[href]");

            Element links;
            for(Iterator var12 = a.iterator(); var12.hasNext(); s1 = s1.concat(links.attr("href"))) {
                links = (Element)var12.next();
                s1 = s1.concat("");
            }

            System.out.println("link non ancora trovato");
            return s1;
        }
    }
}
