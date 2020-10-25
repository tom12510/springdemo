package com.example.springdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringkafkaListener {


    /**
     * 接受的对象 message
     *
     * @param message
     */
    //@KafkaListener(topics = "miaomiaole")
    public void handle(String message) {
        log.error("这是接受的数据{}", message);
    }
}
