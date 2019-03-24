import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class HTMLreader {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\fisso\\Downloads\\chromedriver_win32\\chromedriver.exe");

        WebDriver wd = new ChromeDriver();
        wd.get("https://thesubmarine.it/2019/03/24/un-milione-in-piazza-contro-la-brexit/");
        String source = wd.getPageSource();

        int inizio = source.indexOf("<h3><b><em>Questa " +
                "è </em>Hello, World!, <em>la nostra rassegna mattiniera di attualità, cultura e internet." +
                "</em></b> Tutte le mattine, un pugno di link da leggere, vedere e ascoltare.</h3>");

        int fine = source.indexOf("Se ti piacciono Hello, World e the Submarine, ricorda di recensire la pagina su Facebook. A domani!");

        String cut = source.substring((inizio + 188), (fine - 3)) + "§";


        Document doc = Jsoup.parse(cut);
        Elements p = doc.select("p");

        for (Element par : p) {
            System.out.println(par.text());
            Elements a = par.select("a[href]");
            for (Element links : a) {
                System.out.println(links.attr("href"));
            }
        }

    }

}







