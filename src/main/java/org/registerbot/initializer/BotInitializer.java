package org.registerbot.initializer;

import lombok.Getter;
import org.registerbot.TGBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Getter
@Component
public class BotInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotInitializer.class);
    private final TGBot telegramBot;

    @Autowired
    public BotInitializer(TGBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBot.registerCommands();
            telegramBotsApi.registerBot(telegramBot);
            LOGGER.info("готовий до роботи");
        } catch (TelegramApiException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
