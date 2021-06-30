package com.example.demessid_bot.Config;

import com.example.demessid_bot.Bot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Bean
    public Bot myBot(){
        Bot bot = new Bot();
        bot.setWebHookPath(webHookPath);
        bot.setBotToken(botToken);
        bot.setBotUserName(botUserName);
        return bot;

    }
}
