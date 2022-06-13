package com.erhuo.config;

import com.erhuo.webSocket.MyHandShake;
import com.erhuo.webSocket.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Component
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;
    @Autowired
    private MyHandShake myHandShake;

    @Bean
    public MyWebSocketHandler wsHandler(){
        return new MyWebSocketHandler();
    }

    @Bean
    public MyHandShake handShake(){
        return new MyHandShake();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler,"/ws" ).addInterceptors(myHandShake).setAllowedOrigins("*");
        registry.addHandler(myWebSocketHandler,"/ws/sockjs" ).addInterceptors(myHandShake).setAllowedOrigins("*").withSockJS();
    }
}
