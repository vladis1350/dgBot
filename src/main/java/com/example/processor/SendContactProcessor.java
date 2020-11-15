package com.example.processor;

import com.example.bean.TraineeChatBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendContactProcessor implements Processor {
    @Autowired
    TraineeChatBot traineeChatBot;

    @Override
    public String run() {
        return "Спасибо!!!";
    }
}
