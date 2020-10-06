package com.example.springdemo.service.impl;

import com.example.springdemo.pojo.Ingredient;
import com.example.springdemo.service.ItestLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class testLogServiceImpl implements ItestLogService {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public Ingredient getDataByParams(List<String> params) {
        log.info("这是喵喵乐的服务,测试kafka发送消息");
        kafkaTemplate.sendDefault(params.toString());
        return null;
    }
}
