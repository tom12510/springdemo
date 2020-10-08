package com.example.springdemo.web;

import com.example.springdemo.pojo.Ingredient;
import com.example.springdemo.service.ItestLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class DesignTacoController {


    @Autowired
    private ItestLogService itestLogService;


    @RequestMapping("/log")
    public String testLog(@RequestParam("params") List<String> params, HttpServletRequest httpRequest) {

        try {
            Ingredient ingredient = itestLogService.getDataByParams(params);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(params.toString());
        log.info("打印httpRequest对象数据{}",httpRequest.toString());
        return "执行成功！";
    }
}
