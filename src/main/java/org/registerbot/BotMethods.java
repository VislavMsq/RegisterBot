package org.registerbot;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@AllArgsConstructor
public class BotMethods {
    private OTPService otpService;

    @Transactional
    public void handleUpdate(Update update, TGBot bot) throws TelegramApiException {
        System.out.println(update);
        if (update.hasMessage()) {
            if (update.getMessage().getChat().isUserChat()) {
                if (update.getMessage().hasText()) {
                    if (update.getMessage().getText().equals("/start auth") ||
                            update.getMessage().getText().equals("/auth")) {
                        OTP otp = otpService.generateOTP(update.getMessage().getFrom().getId());
                        bot.execute(new SendMessage(update.getMessage().getFrom().getId().toString(),
                                "Your OTP is:\n" + otp.getCode()));
                    } else if (update.getMessage().getText().startsWith("/start")) {
                        bot.execute(new SendMessage(update.getMessage().getFrom().getId().toString(),
                                "Welcome to the bot!"));
                    } else {
                        bot.execute(new SendMessage(update.getMessage().getFrom().getId().toString(),
                                "Wrong command!"));
                    }
                }
            }
        }
    }
}
