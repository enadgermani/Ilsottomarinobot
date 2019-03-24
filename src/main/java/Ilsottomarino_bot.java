import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
            msg.setText("porcamadonna");
            msg.setChatId(update.getMessage().getChatId());


            try {

                for (int i = 0; i < 10; i++) {

                    execute(msg);
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
