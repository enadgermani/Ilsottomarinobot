import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//import org.telegram.telegrambots.meta.api.objects.Update;

public class MainClass implements RequestStreamHandler {

    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
       bot();
    }

    public void bot(){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Ilsottomarino_bot());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }
}