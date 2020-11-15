package com.example.enums;


import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public enum BotCommand {
    START("/start"),
    SETTING("/settings"),
    NONE("/none"),
    MY_CARTS("Мои карты"),
    PARTICIPANT_PROGRAMS("Участники программы"),
    CATALOG("Каталог"),
    SHARES("Акции"),
    MY_INFORMATION("Моя информация"),
    DELIVERY("Доставка"),
    HELP("Помощь"),
    SEND_PHONE_NUMBER,
    MAIN_MENU,
    INFORMATION_CART,
    LIST_BY_CATEGORY,
    WHAT_DISCOUNT,
    POPULAR_PRODUCTS,
    CATEGORIZATION,
    GET_DISCHARGE,
    INPUT_DATA,
    OPERATIONS,
    ASK_AGE,
    GET_BTN_DISCHARGE,
    LEAVE_FEEDBACK,
    FEEDBACK_FILLED,
    QUANTITY_BALLS;

    String command;

    BotCommand() {

    }

    public String getCommand() {
        return command;
    }

    BotCommand(String command) {
        this.command = command;
    }


}
