package com.example.enums;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public enum BotCommand {
    START("/start"),
    HELP("/help"),
    SETTING("/settings"),
    NONE("/none");

    String command;

    public String getCommand() {
        return command;
    }

    BotCommand(String command) {
        this.command = command;
    }

    private BotCommand getCommand(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                String msgText = message.getText();
                if (msgText.startsWith(BotCommand.HELP.getCommand())) {
                    return BotCommand.HELP;
                } else if (msgText.startsWith(BotCommand.START.getCommand())) {
                    return BotCommand.START;
                } else if (msgText.startsWith(BotCommand.SETTING.getCommand())) {
                    return BotCommand.SETTING;
                }
            }
        }
        return BotCommand.NONE;
    }

    public void dispatch(Update update) {
        switch (getCommand(update)) {
            case HELP:
                break;
            case START:
                break;
            case SETTING:
                break;
            case NONE:
                break;
        }
    }

}
