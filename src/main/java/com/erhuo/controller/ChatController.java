package com.erhuo.controller;

import com.erhuo.pojo.Chat;
import com.erhuo.pojo.Order;
import com.erhuo.service.ChatService;
import com.erhuo.vo.ChatVO;
import com.erhuo.vo.GoodsVO;
import com.erhuo.webSocket.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    MyWebSocketHandler webSocketHandler;

    @Bean
    public MyWebSocketHandler webSocketHandler() {
        return new MyWebSocketHandler();
    }

    @GetMapping("/chat/before")
    public ChatVO chatBefore(String id, String token) {
        return chatService.chatBefore(id, token);
    }

    @GetMapping("/chat/goods")
    public GoodsVO detail(String id) {
        return chatService.detail(id);
    }

    @GetMapping("/buy/newOrder")
    public boolean newOrder(Order order) {
        System.out.println(order);
        return chatService.newOrder(order);
    }

    @GetMapping("/chat/chatList")
    public List<ChatVO> chatList(String token) {
        return chatService.chatList(token);
    }
}
