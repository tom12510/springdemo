package com.example.springdemo.service.impl;

import com.example.springdemo.AnnotationConfig.MyAnnotation;
import com.example.springdemo.pojo.Ingredient;
import com.example.springdemo.service.ItestLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class testLogServiceImpl implements ItestLogService {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateForString;


    @Override
    @MyAnnotation(value = "one",names = "1233456",isExits = true,age = 123)
    public Ingredient getDataByParams(List<String> params) throws ExecutionException, InterruptedException {
        log.info("这是喵喵乐的服务,测试kafka发送消息");
       // ListenableFuture<SendResult<String, String>> sendResultListenableFuture = kafkaTemplateForString.send("miaomiaole",params.toString());

        System.out.println("ok");
        return null;
    }
}
