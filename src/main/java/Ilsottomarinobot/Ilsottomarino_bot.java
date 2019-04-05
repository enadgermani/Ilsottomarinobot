package Ilsottomarinobot;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;

public class Ilsottomarino_bot extends TelegramLongPollingBot {
    int timer = 5;
    String botToken="763191121:AAHtYs_DNdnL-R0DITBtbU61msE0vhAG4kA";   //inserire qui la stringa bot token
    ArrayList<String> articoli = new ArrayList<String>();




    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
//        System.out.println(update.getMessage().getFrom().getFirstName());
        String command = update.getMessage().getText();




        if (command.equals("/aggiorna")){
            if(!articoli.isEmpty()){
                articoli.clear();
            }
            SendMessage  msg = new SendMessage();

            HTMLreader h = new HTMLreader();
            articoli = h.agisci();
            System.out.println("finito aggiornamento");
            msg.setChatId(update.getMessage().getChatId());
            msg.setText("finito di aggiornare");
            try {
                execute(msg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (command.equals("/timer")) {

            timer = Integer.parseInt(update.getMessage().getText());
        /*    HTMLreader h = new HTMLreader();
            articoli = h.agisci();*/
        }
        if (command.equals("/manda1")) {
            System.out.println("porcodio");
            SendMessage  msg = new SendMessage();

            msg.setText("porcodio");

            msg.setChatId(update.getMessage().getChatId());


            try {
                execute(msg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if (command.equals("/manda_con_calma")) {
            if(articoli.size()==0||articoli.isEmpty()){
                aggiorna(update);
            }
            SendMessage  msg = new SendMessage();


            System.out.println("porcamadojnna");

            //  Collections.reverse(articoli);

            //   msg.setText("porcamadonna");
            msg.setChatId(update.getMessage().getChatId());
          //  msg.setParseMode("html");
            msg.setText("<b>Questa " +
                    "è Hello, World!, la nostra rassegna mattiniera di attualità, cultura e internet." +
                    "Tutte le mattine, un pugno di link da leggere, vedere e ascoltare.</b>");

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(articoli);

            try {
                execute(msg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        //    msg.setParseMode("html");

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
            SendMessage  msg = new SendMessage();


            if(articoli.isEmpty()){
                aggiorna(update);
            }
      //      System.out.println(articoli.get(0));

        /*    System.out.println("porcamadojnna");
            HTMLreader h = new HTMLreader();
            ArrayList<String> articoli = h.agisci();
         */
            Collections.reverse(articoli);


            //   msg.setText("porcamadonna");
            msg.setChatId(update.getMessage().getChatId());
            msg.setParseMode("html");

            msg.setText("<b>Questa " +
                            "è Hello, World!, la nostra rassegna mattiniera di attualità, cultura e internet." +
                            "Tutte le mattine, un pugno di link da leggere, vedere e ascoltare.</b>");
            try {
                execute(msg);           //si rompe quisi rompe quisi rompe quisi rompe quisi rompe quisi rompe quisi rompe quisi rompe quisi rompe quisi rompe qui
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            try {

                for (int i = 1; i < articoli.size(); i++) {
                    if (articoli.get(i) != null & !articoli.get(i).equals("")) {
                        msg.setText(articoli.get(i));
                        execute(msg);
             //           clearWebhook();
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
                return botToken;


            }

            public String creaLink () {
                return null;
            }

            public void aggiorna(Update update){
                SendMessage  msg = new SendMessage();

                if(!articoli.isEmpty()){
                    articoli.clear();
                }
                HTMLreader h = new HTMLreader();
                articoli = h.agisci();
                System.out.println("finito aggiornamento");
                msg.setChatId(update.getMessage().getChatId());
                msg.setText("finito di aggiornare");
                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }


    }

    //"763191121:AAHtYs_DNdnL-R0DITBtbU61msE0vhAG4kA" bot token

