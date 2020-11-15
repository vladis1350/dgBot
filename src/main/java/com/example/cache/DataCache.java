package com.example.cache;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DataCache {
    private String setStatus = "stop";
}
