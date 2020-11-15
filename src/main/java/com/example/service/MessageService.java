package com.example.service;

import com.example.bean.TraineeChatBot;
import com.example.buttonHandler.ButtonHandler;
import com.example.buttonHandler.MainMenuButton;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.payments.LabeledPrice;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    TraineeChatBot traineeChatBot;
    @Autowired
    ButtonHandler buttonHandler;
    @Autowired
    MainMenuButton mainMenuButton;

    public void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        if (message.hasText()) {
            if (message.getText().equals("/start")) {
                sendMessage.setReplyMarkup(mainMenuButton.getExtraMainMenuKeyboard());
            } else {
                sendMessage.setReplyMarkup(mainMenuButton.getMainMenuKeyboard());
            }
        } else {
            sendMessage.setReplyMarkup(mainMenuButton.getMainMenuKeyboard());
        }
        try {
            traineeChatBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public void editMessage(Message message, int messageId, String text) {
        EditMessageText editMessageText = new EditMessageText()
                .setText(text)
                .setMessageId(messageId)
                .setChatId(message.getChatId());
        traineeChatBot.execute(editMessageText);
    }

    public void sendMessage(SendMessage sendMessage) {
        try {
            traineeChatBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public void deleteMessage(long chatId, int messageId) {
        DeleteMessage deleteMessage = new DeleteMessage()
                .setChatId(chatId)
                .setMessageId(messageId);
        traineeChatBot.execute(deleteMessage);
    }

    public void sendMessage(EditMessageText sendMessage) {
        try {
            traineeChatBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public void sendContact(Message message) {
        SendContact sendContact = new SendContact()
                .setChatId(message.getChatId())
                .setFirstName(message.getFrom().getFirstName())
                .setLastName(message.getFrom().getLastName())
                .setPhoneNumber(message.getContact().getPhoneNumber());
        traineeChatBot.execute(sendContact);
    }

    public void sendInvoice(Message message) {
        LabeledPrice labeledPrice = new LabeledPrice();
        labeledPrice.setAmount(10);
        labeledPrice.setLabel("dollar");
        List<LabeledPrice> labeledPrices = new ArrayList<>();
        labeledPrices.add(labeledPrice);
        SendInvoice sendInvoice = new SendInvoice();
        sendInvoice.setChatId(message.getFrom().getId());
        sendInvoice.setTitle("Оплата услуги");
        sendInvoice.setDescription("Что бы что то получить надо денег заплатить.");
        sendInvoice.setPayload("ьпл");
        sendInvoice.setProviderToken("410694247:TEST:6314bbf1-03b1-4475-9dc8-9dd34c7c5ed4");
        sendInvoice.setStartParameter("410694247");
        sendInvoice.setCurrency("USD");
        sendInvoice.setPrices(labeledPrices);
        try {
            traineeChatBot.execute(sendInvoice);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageAndKeyboard(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(mainMenuButton.getMainMenuKeyboard());
        try {
            traineeChatBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
