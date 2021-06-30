package com.example.demessid_bot;


import com.example.demessid_bot.JSON.Root;
import com.google.gson.Gson;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramWebhookBot {

    private String webHookPath;
    private String botUserName;
    private String botToken;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();


            try {
                if(update.getMessage().getText().matches("^[АВЕКМНОРСТУХавекмнорстух]\\d{3}(?<!000)[АВЕКМНОРСТУХавекмнорстух]{2}\\d{2,3}$")){
                    String stringPosts = restTemplate.getForObject(
                            "https://vin01.ru/v2/getVin.php?gosNumber=" + update.getMessage().getText() +
                                    "&key=03AGdBq26oI_-rls0WLqzSGOfUrFVuAx05MhkGP-z-ow44tiQhVS5hrwfe88fX-aWpYhlwivJFB3KhrBAMGSlFTJKX0Z4Xp8N1SnJ_P9p5CnSPzd8NIM5kTGE6CttjT0ixEkdNIuCcanPND0IknMrbVuNbmnPGofiEv7_ai2HXCr5iGxMiZ_AJ3glmbsSGm181p7wFlt7UQJC5MURsS-IaX54MZIfphLLCmRFhxtGrXOSOcknTE7opmRIRaF2DhqFg2KcvI22rHKrDfE8FRYBvMtetfOpd--eedH4OXiy6Vro1zUAzTzop_kY_LU584Iyw_FkISIA28ES13KNb8MwZYvEAREIObxFFN0BncyoiGKC2jVd2xqCM4iFHmiaRqwW1vfVoOrMgEDe-9Ecln6EodtIhiQkW1KvgIfIpTEfALqawRdWxP4YY_ZbASqRYoz9Rt1RB6lsfX1Yy" +
                                    "&site=1", String.class);

                    try {
                        Gson gson = new Gson();
                        Root root = gson.fromJson(stringPosts, Root.class);
                        System.out.println(root.getData().getVin());

                        execute(new SendMessage(Long.toString(chat_id), root.getData().getVin()));
                    }catch (NullPointerException e){
                        execute(new SendMessage(Long.toString(chat_id), "В базе нет информации о данном автомобиле или произошла ошибка!"));
                    }
                }
                else{
                    execute(new SendMessage(Long.toString(chat_id), "Введены неверные данные"));
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}
