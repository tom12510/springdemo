package com.example.springdemo.config;

import com.cmbchina.channel.ISMCrypt;
import com.cmbchina.channel.SMCryptKY;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public ISMCrypt createISMCrypt(){
        return new SMCryptKY();
    }
}
