package com.example.service;

import com.example.buttonHandler.ButtonHandler;
import com.example.cache.UserDataCache;
import com.example.enums.BotState;
import com.example.processor.*;
import com.example.enums.BotCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class RequestDispatcher {
    @Autowired
    MessageService messageService;
    @Autowired
    HelpProcessor helpProcessor;
    @Autowired
    SettingsProcessor settingsProcessor;
    @Autowired
    StartProcessor startProcessor;
    @Autowired
    NoneProcessor noneProcessor;
    @Autowired
    PaymentProcessor paymentProcessor;
    @Autowired
    SendContactProcessor sendContactProcessor;
    @Autowired
    ButtonHandler buttonHandler;

    private static String state = "0";
    private static String name = "";
    private static String age = "";
    private int messageId;
    private int myMessageId;

    public void dispatch(Update update) {
        switch (getCommand(update)) {
            case HELP:
                messageService.sendMessage(update.getMessage(), helpProcessor.run());
                break;
            case START:
                messageService.sendMessage(update.getMessage(), startProcessor.run());
                break;
            case SETTING:
                messageService.sendMessage(update.getMessage(), settingsProcessor.run());
                break;
            case NONE:
                messageService.sendMessage(update.getMessage(), noneProcessor.run());
                break;
            case SEND_PHONE_NUMBER:
                messageService.sendMessage(update.getCallbackQuery().getMessage(), sendContactProcessor.run());
                break;
            case MAIN_MENU:
                    messageService.sendMessageAndKeyboard(update.getMessage(), "Главное меню");
                break;
            case MY_CARTS:
                messageService.sendMessage(buttonHandler.getButtonMyCartsAndMessage(update.getMessage().getChatId()));
                break;
            case PARTICIPANT_PROGRAMS:
                messageService.sendMessage(buttonHandler.
                        getButtonParticipantProgramsAndMessage(update.getMessage().getChatId(), "Выберите действие"));
                break;
            case CATALOG:
                messageService.sendMessage(buttonHandler.
                        getButtonCatalogAndMessage(update.getMessage().getChatId(), "Выберите действие"));
                break;
            case SHARES:
                messageService.sendMessage(update.getMessage(), "Акционные товары: \n\n1. Product 1\n2. Product 2\n3. Product 3\n");
                break;
            case DELIVERY:
                messageService.sendMessage(buttonHandler.
                        getButtonDeliveryInfoAndMessage(update.getMessage().getChatId(), "Заказ в пути"));
                break;
            case MY_INFORMATION:
                messageService.sendMessage(buttonHandler.
                        getButtonMyInformationAndMessage(update.getMessage().getChatId(), "Выберите действие"));
                break;
            case INFORMATION_CART:
                messageService.sendMessage(buttonHandler.
                        getButtonStatusUpAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId,"Карта: Card Name"));
                break;
            case QUANTITY_BALLS:
                messageService.sendMessage(buttonHandler.
                        getButtonQuantityBallsAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId, "Количество баллов: 10"));
                break;
            case LIST_BY_CATEGORY:
                messageService.sendMessage(buttonHandler.
                        getButtonAddressesAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId,"Список:\n\n1. Name.\n2. Name.\n3. Name.\n"));
                break;
            case WHAT_DISCOUNT:
                messageService.sendMessage(buttonHandler.
                        getButtonInviteFriendAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId,"Скидка 10%"));
                break;
            case POPULAR_PRODUCTS:
                messageService.sendMessage(buttonHandler.
                        getButtonPopularProductsAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId,"Популярные товары: \n\n1. Товар 1\n2. Товар 2\n3. Товар 3\n"));
                break;
            case CATEGORIZATION:
                messageService.editMessage(update.getCallbackQuery().getMessage(), messageId,"Категории: \n\n1. Category one\n2. Category two\n3. Category three\n");
                break;
            case GET_DISCHARGE:
                messageService.sendMessage(update.getCallbackQuery().getMessage(), "Выписка: \n\n" + name + "\n" + age);
                break;
            case OPERATIONS:
                messageService.sendMessage(update.getCallbackQuery().getMessage(), "Операции");
                break;
            case INPUT_DATA:
                messageService.sendMessage(update.getCallbackQuery().getMessage(), "Введите ФИО");
                state = "askName";
                break;
            case ASK_AGE:
                messageService.sendMessage(update.getMessage(), "Введите возраст");
                state = "askAge";
                break;
            case GET_BTN_DISCHARGE:
                messageService.sendMessage(buttonHandler.getButtonDischargeAndMessage(update.getMessage().getChatId(), "Можно получить выписку"));
                break;
            case LEAVE_FEEDBACK:
                messageService.sendMessage(update.getCallbackQuery().getMessage(), "Введите ваш отзыв");
                state = "askFeedback";
                break;
            case FEEDBACK_FILLED:
                messageService.sendMessage(update.getMessage(), "Спасибо за ваш отзыв!");
                break;
            default:
                messageService.sendMessage(update.getMessage(), "+");
                break;
        }
    }

    private BotCommand getCommand(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                myMessageId = message.getMessageId();
                String msgText = message.getText();
                if (msgText.startsWith(BotCommand.HELP.getCommand())) {
                    return BotCommand.HELP;
                } else if (msgText.startsWith(BotCommand.START.getCommand())) {
                    return BotCommand.START;
                } else if (msgText.startsWith(BotCommand.SETTING.getCommand())) {
                    return BotCommand.SETTING;
                } else if (msgText.startsWith(BotCommand.MY_CARTS.getCommand())) {
                    return BotCommand.MY_CARTS;
                } else if (msgText.startsWith(BotCommand.PARTICIPANT_PROGRAMS.getCommand())) {
                    return BotCommand.PARTICIPANT_PROGRAMS;
                } else if (msgText.startsWith(BotCommand.CATALOG.getCommand())) {
                    return BotCommand.CATALOG;
                } else if (msgText.startsWith(BotCommand.SHARES.getCommand())) {
                    return BotCommand.SHARES;
                } else if (msgText.startsWith(BotCommand.MY_INFORMATION.getCommand())) {
                    return BotCommand.MY_INFORMATION;
                } else if (msgText.startsWith(BotCommand.DELIVERY.getCommand())) {
                    return BotCommand.DELIVERY;
                } else if (state.equals("askName")) {
                    name = msgText;
                    return BotCommand.ASK_AGE;
                } else if (state.equals("askAge")) {
                    age = msgText;
                    return BotCommand.GET_BTN_DISCHARGE;
                } else if (state.equals("askFeedback")) {
                    return BotCommand.FEEDBACK_FILLED;
                } else if (msgText.startsWith("Нет")) {
                    return BotCommand.MAIN_MENU;
                } else if (msgText.startsWith("Привет я бот")) {
                    return BotCommand.MAIN_MENU;
                }
            } else if (message != null && message.hasContact()) {
                return BotCommand.MAIN_MENU;
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery buttonQuery = update.getCallbackQuery();
            messageId = buttonQuery.getMessage().getMessageId();
            if (buttonQuery.getData().equals("buttonMyCarts")) {
                return BotCommand.INFORMATION_CART;
            } else if (buttonQuery.getData().equals("buttonQuantityBalls")) {
                return BotCommand.QUANTITY_BALLS;
            } else if (buttonQuery.getData().equals("buttonListByCategory")) {
                return BotCommand.LIST_BY_CATEGORY;
            } else if (buttonQuery.getData().equals("buttonWhatDiscount")) {
                return BotCommand.WHAT_DISCOUNT;
            } else if (buttonQuery.getData().equals("buttonPopularProducts")) {
                return BotCommand.POPULAR_PRODUCTS;
            } else if (buttonQuery.getData().equals("buttonCategorization")) {
                return BotCommand.CATEGORIZATION;
            } else if (buttonQuery.getData().equals("buttonDischarge")) {
                return BotCommand.GET_DISCHARGE;
            } else if (buttonQuery.getData().equals("buttonInputData")) {
                return BotCommand.INPUT_DATA;
            } else if (buttonQuery.getData().equals("buttonOperations")) {
                return BotCommand.OPERATIONS;
            } else if (buttonQuery.getData().equals("buttonLeaveFeedBack")) {
                return BotCommand.LEAVE_FEEDBACK;
            }
        }
        return BotCommand.NONE;
    }
}