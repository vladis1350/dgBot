package com.example.processor;

import com.example.service.ReplyMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartProcessor implements Processor {

    @Autowired
    ReplyMessagesService replyMessagesService;

    @Override
    public String run() {
        return "Привет я бот BG, чтобы продолжить нужно отправить номер телефона";
    }
}
