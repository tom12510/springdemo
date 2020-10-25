package com.example.springdemo.web;

import com.cmbchina.channel.ISMCrypt;
import com.cmbchina.channel.SMCryptException;
import com.cmbchina.channel.util.ByteUtils;
import com.example.springdemo.pojo.Ingredient;
import com.example.springdemo.service.ItestLogService;
import com.example.springdemo.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class DesignTacoController {


    @Autowired
    private ItestLogService itestLogService;

    @Autowired
    private ISMCrypt ismCrypt;

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
        log.info("打印httpRequest对象数据{}", httpRequest.toString());
        return "执行成功！";
    }


    @PostMapping("/signStrMethod")
    public Map<String,String> signStrMethod(@RequestParam("str") String str) {
        try {
            Map<String , String> resultMap = new HashMap<>();
            Map<String, byte[]> keyGen = ismCrypt.CMBSM2KeyGen();
            byte[] publickeys = keyGen.get("publickey");
            byte[] privatekeys = keyGen.get("privatekey");
            //byte数组转16进制字符
            String publicKeyString = Util.getHexString(publickeys);
            log.info("公钥{}",publicKeyString);
            resultMap.put("publickey",publicKeyString);
            //byte数组转16进制字符
            String privatekeysString = Util.getHexString(privatekeys);
            log.info("私钥{}",privatekeysString);
            resultMap.put("privatekeysString",privatekeysString);

            //公钥加密
            byte[] encryptByte = ismCrypt.CMBSM2Encrypt(publickeys, str.getBytes());
            log.info("str：{}加密后16进制字符{}",str,Util.getHexString(encryptByte));
            resultMap.put("enCryptStr",Util.getHexString(encryptByte));


            //私钥解密
            byte[] bytes = ismCrypt.CMBSM2Decrypt(privatekeys, encryptByte);
            log.info("解密后内容{}",new String(bytes));
            resultMap.put("str",new String(bytes));

            //私钥加签
            byte[] signByte = ismCrypt.CMBSM2SignWithSM3(privatekeys, str.getBytes());
            log.info("加签内容{}",Util.getHexString(signByte));
            resultMap.put("signStr",Util.getHexString(signByte));

            //公钥验证签名
            int i = ismCrypt.CMBSM2VerifyWithSM3(publickeys, str.getBytes(), signByte);
            log.info("签名{}",i);
            resultMap.put("verifySign",String.valueOf(i));
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
