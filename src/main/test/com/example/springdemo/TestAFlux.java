package com.example.springdemo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author miaomiaole
 * @date 2020/10/18 14:26
 * @DESCRIBE
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAFlux {
    @Test
    public void createAFlux_just() {
        //数据发布者
        Flux<String> fruitFlux = Flux.just(
                "Apple", "Orange", "miaomiaole"
        );

        //数据订阅者 调用subscribe 数据使用
        fruitFlux.subscribe(x-> System.out.println(x));


        StepVerifier.create(fruitFlux).expectNext("Apple").verifyComplete();
    }
}
