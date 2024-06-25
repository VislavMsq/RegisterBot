package org.registerbot;

import lombok.AllArgsConstructor;
import org.registerbot.initializer.BotConfig;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TGBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final BotMethods botMethods;

    @Override
    public void onUpdateReceived(Update update) {
        try {
            botMethods.handleUpdate(update, this);
        } catch (TelegramApiException e) {
            LoggerFactory.getLogger(ExceptionDetector.class).error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    public void registerCommands() throws TelegramApiException {
        List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand("/start", "Більш детальна інформація"));
        commands.add(new BotCommand("/connect", "Стати адміном"));
        commands.add(new BotCommand("/add_warn", "Додати слово до сірого списку"));
        commands.add(new BotCommand("/rm_warn", "Видалити слово з сірого списку"));
        commands.add(new BotCommand("/get_warn", "Переглянути сірий список"));
        commands.add(new BotCommand("/add_del", "Додати слово до чорного списку"));
        commands.add(new BotCommand("/rm_del", "Видалити слово з чорного списку"));
        commands.add(new BotCommand("/get_del", "Перегляд чорного списку"));
        commands.add(new BotCommand("/adv", "Налаштувати рекламку"));
        commands.add(new BotCommand("/ad_on", "Ввімкнути рекламку"));
        commands.add(new BotCommand("/ad_off", "Вимкнути рекламку"));
        commands.add(new BotCommand("/exit", "Перестати бути адміном"));

        SetMyCommands setCommands = new SetMyCommands();
        setCommands.setCommands(commands);

        execute(setCommands);
    }
}
