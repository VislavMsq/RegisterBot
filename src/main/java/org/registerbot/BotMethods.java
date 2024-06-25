package org.registerbot;

import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotMethods {
    private OTPService otpService;

    @Transactional
    public void handleUpdate(Update update, TGBot bot) throws TelegramApiException {
        if (update.hasMessage()) {
            if (update.getMessage().getChat().isUserChat()) {
                if (update.getMessage().hasText()) {
                    if (update.getMessage().getText().equals("/auth")) {
                        OTP otp = otpService.generateOTP(update.getMessage().getFrom().getId());
                        bot.execute(new SendMessage(update.getMessage().getFrom().getId().toString(),
                                "Your OTP is: " + otp.getCode()));
                    }
                }
            }
        }
    }
}
