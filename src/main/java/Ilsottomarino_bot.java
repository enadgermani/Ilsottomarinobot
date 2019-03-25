import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Ilsottomarino_bot extends TelegramLongPollingBot {
    int timer = 5;

    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
//        System.out.println(update.getMessage().getFrom().getFirstName());
        String command = update.getMessage().getText();

        SendMessage msg = new SendMessage();
        if (command.equals("/timer")) {

            timer = Integer.parseInt(update.getMessage().getText());
        }
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
        if (command.equals("/manda_con_calma")) {


            System.out.println("porcamadojnna");
            HTMLreader h = new HTMLreader();
            ArrayList<String> articoli = h.agisci();
            //  Collections.reverse(articoli);

            //   msg.setText("porcamadonna");
            msg.setChatId(update.getMessage().getChatId());


            try {

                for (int i = 0; i < articoli.size(); i++) {
                    if (articoli.get(i) != null && !articoli.get(i).equals("")) {
                        msg.setText(articoli.get(i));
                        execute(msg);
                    }
                     Thread.sleep(timer * 1000);
                }

            } catch (TelegramApiException e) {
                e.printStackTrace();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        if (command.equals("/manda_tutto_insieme")) {


            System.out.println("porcamadojnna");
            HTMLreader h = new HTMLreader();
            ArrayList<String> articoli = h.agisci();
            Collections.reverse(articoli);

            //   msg.setText("porcamadonna");
            msg.setChatId(update.getMessage().getChatId());


            try {

                for (int i = 0; i < articoli.size(); i++) {
                    if (articoli.get(i) != null && !articoli.get(i).equals("")) {
                        msg.setText(articoli.get(i));
                        execute(msg);
                    }
                    //    Thread.sleep(timer * 1000);
                }

            } catch (TelegramApiException e) {
                e.printStackTrace();

            }

        }

    }
            public String getBotUsername () {
                return "Ilsottomarinobot";
            }

            public String getBotToken () {
                return "763191121:AAHtYs_DNdnL-R0DITBtbU61msE0vhAG4kA";


            }

            public String creaLink () {
                return null;
            }



    }