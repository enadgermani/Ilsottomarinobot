import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;


public class HTMLreader {
    WebDriver wd;
    WebDriver wd2;

    public HTMLreader() {

    }

    public HTMLreader(String link) {


    }

    public ArrayList<String> agisci() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\enad\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        wd = new ChromeDriver();
        String prova = findLink();
        System.out.println(prova);

        wd.close();
        wd2 = new ChromeDriver();
        wd2.get(prova);
        System.out.println("seconda pagina trovata");


        String source = wd2.getPageSource();
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
    public String findLink() {
        System.out.println("cerco il link");
        String base = "https://thesubmarine.it/author/redazione/";
        wd.get(base);
        String html = wd.getPageSource();
      //  System.out.println(html);

     String  html2 = html.substring(html.indexOf("div id=\"cb-content\" class=\"wrap cb-author-page cb-wrap-pad clearfix\">"));

                Document doc = Jsoup.parse(html2);
        Elements p = doc.select("h2");

   //     ArrayList<String> articoliRedazione = new ArrayList<String>();
        String s = "";
        String s1 = "";
        for (Element par : p) {
            s = par.text();
            Elements a = par.select("a[href]");
            for (Element links : a) {
                s1 = s1.concat("  ");
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








