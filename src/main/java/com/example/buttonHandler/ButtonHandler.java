package com.example.buttonHandler;

import com.example.service.LocaleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;


@Component
public class ButtonHandler {

    @Autowired
    private LocaleMessageService localeMessageService;

    public SendMessage getButtonSendPhoneNumberAndMessage(long chatId) {
        return new SendMessage(chatId, localeMessageService.getMessage("reply.Start")).setReplyMarkup(getButtonSendPhoneNumber());
    }

    public SendMessage getButtonMyCartsAndMessage(long chatId) {
        return new SendMessage(chatId, "Выберите действие").setReplyMarkup(getButtonMyCarts());
    }

    public EditMessageText getButtonStatusUpAndMessage(long chatId, int messageId, String text) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(getButtonStatusUp());
    }

    public EditMessageText getButtonQuantityBallsAndMessage(long chatId, int messageId, String text) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(getButtonQuantityBalls());
    }

    public SendMessage getButtonParticipantProgramsAndMessage(long chatId, String text) {
        return new SendMessage(chatId, text).setReplyMarkup(getButtonParticipantPrograms());
    }

    public EditMessageText getButtonAddressesAndMessage(long chatId, int messageId, String text) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(getButtonAddresses());
    }

    public EditMessageText getButtonInviteFriendAndMessage(long chatId, int messageId, String text) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(getButtonInviteFriend());
    }

    public SendMessage getButtonCatalogAndMessage(long chatId, String text) {
        return new SendMessage(chatId, text).setReplyMarkup(getButtonCatalog());
    }

    public EditMessageText getButtonPopularProductsAndMessage(long chatId, int messageId, String text) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(getButtonPopularProducts());
    }

    public SendMessage getButtonMyInformationAndMessage(long chatId, String text) {
        return new SendMessage(chatId, text)
                .setReplyMarkup(getButtonMyInformation());
    }

    public SendMessage getButtonDischargeAndMessage(long chatId, String text) {
        return new SendMessage(chatId, text).setReplyMarkup(getButtonDischarge());
    }

    public SendMessage getButtonDeliveryInfoAndMessage(long chatId, String text) {
        return new SendMessage(chatId, text).setReplyMarkup(getButtonDeliveryInfo());
    }

    public InlineKeyboardMarkup getButtonSendPhoneNumber() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonSendPhoneNumber = new InlineKeyboardButton().setText("Отправить номер телефона");
        InlineKeyboardButton buttonNotSendPhoneNumber = new InlineKeyboardButton().setText("Нет");

        buttonSendPhoneNumber.setCallbackData("buttonSendPhoneNumber");
        buttonNotSendPhoneNumber.setCallbackData("buttonNotSendPhoneNumber");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(buttonSendPhoneNumber);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonNotSendPhoneNumber);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonMyCarts() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonMyCarts = new InlineKeyboardButton().setText("Информация о карте");
        InlineKeyboardButton buttonQuantityBalls = new InlineKeyboardButton().setText("Количество баллов");

        buttonMyCarts.setCallbackData("buttonMyCarts");
        buttonQuantityBalls.setCallbackData("buttonQuantityBalls");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonMyCarts);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonQuantityBalls);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonStatusUp() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonStatusUp = new InlineKeyboardButton().setText("Повышение статуса");

        buttonStatusUp.setCallbackData("buttonStatusUp");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonStatusUp);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonDeliveryInfo() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonLeaveFeedBack = new InlineKeyboardButton().setText("Оставить отзыв");

        buttonLeaveFeedBack.setCallbackData("buttonLeaveFeedBack");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonLeaveFeedBack);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonQuantityBalls() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonnQuantityBalls = new InlineKeyboardButton().setText("Отозвать карту");

        buttonnQuantityBalls.setCallbackData("buttonnQuantityBalls");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonnQuantityBalls);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonParticipantPrograms() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonListByCategory = new InlineKeyboardButton().setText("Список по категориям");
        InlineKeyboardButton buttonWhatDiscount = new InlineKeyboardButton().setText("Какая скидка");

        buttonListByCategory.setCallbackData("buttonListByCategory");
        buttonWhatDiscount.setCallbackData("buttonWhatDiscount");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonListByCategory);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonWhatDiscount);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonCatalog() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonPopularProducts = new InlineKeyboardButton().setText("Предложение популярных товаров");
        InlineKeyboardButton buttonCategorization = new InlineKeyboardButton().setText("Разделение по категориям");

        buttonPopularProducts.setCallbackData("buttonPopularProducts");
        buttonCategorization.setCallbackData("buttonCategorization");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonPopularProducts);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonCategorization);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonPopularProducts() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonSearchPopularProduct = new InlineKeyboardButton().setText("Поиск");

        buttonSearchPopularProduct.setCallbackData("buttonSearchPopularProduct");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonSearchPopularProduct);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonAddresses() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonAddresses = new InlineKeyboardButton().setText("Адреса(поиск по геопозиции)");

        buttonAddresses.setCallbackData("buttonAddresses");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonAddresses);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonInviteFriend() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonInviteFriend = new InlineKeyboardButton().setText("Пригласи друга");

        buttonInviteFriend.setCallbackData("buttonInviteFriend");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonInviteFriend);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonMyInformation() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonInputData = new InlineKeyboardButton().setText("Ввод данных");
        InlineKeyboardButton buttonOperations = new InlineKeyboardButton().setText("Операции");

        buttonInputData.setCallbackData("buttonInputData");
        buttonOperations.setCallbackData("buttonOperations");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonInputData);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonOperations);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonDischarge() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonDischarge = new InlineKeyboardButton().setText("Получение выписки");

        buttonDischarge.setCallbackData("buttonDischarge");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonDischarge);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
