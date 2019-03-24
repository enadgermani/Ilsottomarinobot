import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class Ilsottomarino_bot extends TelegramLongPollingBot{

    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
//        System.out.println(update.getMessage().getFrom().getFirstName());
        String command = update.getMessage().getText();

        SendMessage msg = new SendMessage();

        if (command.equals("/manda1")) {
            System.out.println("porcodio");
            msg.setText("porcodio");

            msg.setChatId(update.getMessage().getChatId());


            try {
                execute(msg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (command.equals("/manda10")) {


            System.out.println("porcamadojnna");
            HTMLreader h = new HTMLreader();
           ArrayList<String> articoli = h.agisci("https://thesubmarine.it/2019/03/24/un-milione-in-piazza-contro-la-brexit/");


            //   msg.setText("porcamadonna");
            msg.setChatId(update.getMessage().getChatId());


            try {

                for (int i = 0; i < articoli.size(); i++) {
                 if(articoli.get(i)!= null && !articoli.get(i).equals("")){   msg.setText(articoli.get(i));
                    execute(msg);}
                }

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    public String getBotUsername() {
        return "Ilsottomarinobot";
    }

    public String getBotToken() {
        return  "763191121:AAHtYs_DNdnL-R0DITBtbU61msE0vhAG4kA";


    }

    public String creaLink(){
                return null;
    }




}
