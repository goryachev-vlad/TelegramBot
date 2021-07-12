import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        }catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message =update.getMessage();
        if (message!= null&&message.hasText()){
            switch (message.getText()){
                case "/help":
                    sendMsg(message, "Чем я могу вам помочь?");
                    break;
                case "/start":
                    sendMsg(message,"Стартуем)");
                    break;
                default:
            }
        }

    }

    @Override
    public String getBotUsername() {
        return "gnidosbot";
    }

    @Override
    public String getBotToken() {
        return "1748547962:AAHinI4iY-WURoAVyOrOZC-r7zC9VhKZdLQ";
    }
}
