package com.example.processor;

import org.springframework.stereotype.Service;

@Service
public class StartProcessor implements Processor {
    @Override
    public String run() {
        return "Hello";
    }
}
