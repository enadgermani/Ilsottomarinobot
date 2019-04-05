package Ilsottomarinobot;


import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



    public class MainClass{
//            implements RequestHandler<InputStream, String> {
      //  private static final ObjectMapper MAPPER = new ObjectMapper();


        public static void main (String []args) {
            Update update;
            System.out.println("provo creazione BOT");


            bot();
            System.out.println("BOT CREATO");


            System.out.println("funziona?");
        }

    public static void bot(){
        ApiContextInitializer.init();
       TelegramBotsApi  telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Ilsottomarino_bot());
            System.out.println("arrivo qui");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }



    }