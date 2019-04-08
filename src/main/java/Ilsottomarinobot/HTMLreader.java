//manda con calma no html
//Se prima manda tutto insieme, dopo manda_con_calma tiene l'rdine invertito



package Ilsottomarinobot;





import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class HTMLreader {

    public HTMLreader() {

    }

    public HTMLreader(String link) {


    }


     public static ArrayList<String> agisci() {


        String prova = findLink();
        System.out.println(prova);

        Document d = null;
        try {
            d = Jsoup.connect(prova).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String html = d.toString();


        Document d2 = null;
        try {
            d2 = Jsoup.connect(prova).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String source = d2.toString();
        System.out.println("seconda html trovata");

        int inizio = source.indexOf("<h3><b><em>Questa " +
                "è </em>Hello, World!, <em>la nostra rassegna mattiniera di attualità, cultura e internet." +
                "</em></b> Tutte le mattine, un pugno di link da leggere, vedere e ascoltare.</h3>");

        int fine = source.indexOf("Se ti piacciono Hello, World e the Submarine, ricorda di recensire la pagina su Facebook. A domani!");

        String cut = source.substring((inizio + 188), (fine - 3)) + "§";

        System.out.println("inizio" + inizio);
        System.out.println("fine" + fine);
        //  System.out.println(cut);

        Document doc = Jsoup.parse(cut);
        Elements p = doc.select("p");

        ArrayList<String> articoli = new ArrayList<String>();
        String s = "";
        String s1 = "";
        for (Element par : p) {
            s = par.text();
            Elements a = par.select("a[href]");
            for (Element links : a) {
                s1 = s1.concat("  ");
                s1 = s1.concat(links.attr("href"));
                //      System.out.println(s + s1);
                articoli.add(s + s1);
                s = "";
                s1 = "";
            }
        }
        return articoli;

    }
    public static String findLink() {

        System.out.println("cerco il link");
        String base = "https://thesubmarine.it/author/redazione/";
        //   wd.get(base);
        //   String html = wd.getPageSource();
        //  System.out.println(html);

        Document d = null;
        try {
            d = Jsoup.connect(base).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String html = d.toString();
        String html2 = html.substring(html.indexOf("div id=\"cb-content\" class=\"wrap cb-author-page cb-wrap-pad clearfix\">"));

        Document doc = Jsoup.parse(html2);
        Elements p = doc.select("h2");

        //     ArrayList<String> articoliRedazione = new ArrayList<String>();
        String s = "";
        String s1 = "";
        for (Element par : p) {
            s = par.text();
            Elements a = par.select("a[href]");
            for (Element links : a) {
                s1 = s1.concat("");
                s1 = s1.concat(links.attr("href"));
            }
            System.out.println("link non ancora trovato");
            return s1;
            // s = "";
            //    s1 = "";
        }


        return "ciao";
    }
}








