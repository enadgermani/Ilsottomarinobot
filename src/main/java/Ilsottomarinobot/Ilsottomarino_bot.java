package Ilsottomarinobot;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Ilsottomarino_bot extends TelegramLongPollingBot {
    int timer = 5;
    String botToken="763191121:AAHtYs_DNdnL-R0DITBtbU61msE0vhAG4kA";   //inserire qui la stringa bot token
    ArrayList<String> articoli = new ArrayList<String>();
    boolean ordine = true;
    String welcome = "<b>Questa " +
            "è Hello, World!, la nostra rassegna mattiniera di attualità, cultura e internet." +
            "Tutte le mattine, un pugno di link da leggere, vedere e ascoltare.</b>";



    public void onUpdateReceived(Update update) {

        leggi(new SendMessage(), update.getMessage().getText(),  update);

//.setChatId(update.getMessage().getChatId())
        System.out.println(update.getMessage().getText());
//        System.out.println(update.getMessage().getFrom().getFirstName());
    }

  public void  leggi(SendMessage msg, String command, Update update){

        if (command.equals("/aggiorna")) {
            aggiorna(msg, update);
        }



        if (command.equals("/manda_con_calma")) {
            invia(msg, 5, update);
        }


//mandamandamandansdasndnasdnasndnasndansdnasddddddddasddddddddddddddddddddddd
        if (command.equals("/manda_tutto_insieme")) {
            invia(msg, 0, update);

        }
      if (command.equals("/manda1")) {

          msg.setChatId(update.getMessage().getChatId());
          msg.setText("solito test");
          try {
              execute(msg);
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

        public void aggiorna (SendMessage msg, Update update){

            if (!articoli.isEmpty()) {
                articoli.clear();
            }

            articoli = HTMLreader.agisci();
            System.out.println("finito aggiornamento");
            msg.setChatId(update.getMessage().getChatId());
            msg.setText("finito di aggiornare");
            try {
                execute(msg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

        public boolean oggi (String link){
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String strDate = formatter.format(date);

            if (link.contains(strDate)) {
                return true;
            }
            return false;
        }

    public void invia(SendMessage msg, int wait, Update update){

        if(wait==0 && ordine == true) Collections.reverse(articoli);  //raffica va a contrario
        // if(wait==0 && ordine == false) Collections.reverse(articoli);  //raffica corretta
//      if(wait!=0 && ordine == true) Collections.reverse(articoli);
        if(wait!=0 && ordine == false) Collections.reverse(articoli);    //relax


            if(articoli.isEmpty()){
                aggiorna(msg, update);
            }

            if(!ordine && wait == 0) {
                Collections.reverse(articoli);
                ordine = true;
            }
            //   msg.setText("porcamadonna");
            msg.setChatId(update.getMessage().getChatId());
            msg.setParseMode("html");

            msg.setText(welcome);

            try {
                execute(msg);           //invia welcome
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            try {

                for (int i = 1; i < articoli.size(); i++) {                         //invia ogni articolo
                    if (articoli.get(i) != null
                            & !articoli.get(i).equals("")                           //tranne vuoti
                            & !articoli.get(i).contains(articoli.get(i-1)))         //tranne duplicati
                    {
                        msg.setText(articoli.get(i));
                        execute(msg);
                        //           clearWebhook();
                    }
                        Thread.sleep(wait * 1000);
                }

            } catch (TelegramApiException e) {
                e.printStackTrace();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        msg.setText("E' stato un piacere");

            try {
                execute(msg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }


    }

    //"763191121:AAHtYs_DNdnL-R0DITBtbU61msE0vhAG4kA" bot token

