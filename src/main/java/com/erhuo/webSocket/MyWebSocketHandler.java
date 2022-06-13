package com.erhuo.webSocket;

import com.erhuo.global.Constants;
import com.erhuo.pojo.Chat;
import com.erhuo.service.ChatService;
import com.erhuo.utils.Json;
import com.erhuo.vo.ChatVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class MyWebSocketHandler implements org.springframework.web.socket.WebSocketHandler {

    @Autowired
    private ChatService chatService;


    private static final List<WebSocketSession> users = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        users.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("message:" + message.getPayload());
        String params = message.getPayload().toString();
        sendMessage2User(params);
//        HashMap hashMap = Json.toObject(params, HashMap.class);
//        Integer sellerId = (Integer) hashMap.get(Constants.SELLER_ID);
//        Integer userId = (Integer) hashMap.get(Constants.USER_ID);
//        Integer goodsId = (Integer) hashMap.get(Constants.GOODS_ID);
//        String msg = (String) hashMap.get(Constants.MSG);
//        chatService.insert(new Chat(sellerId,userId,goodsId,msg));
//        sendMessageToUser(sellerId.toString(), new TextMessage(msg));

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    public void sendMessageToUser(String userId, TextMessage message) {
        for (WebSocketSession user : users) {
            Object obj = user.getAttributes().get(Constants.USER + userId);
            if (userId.equals(obj)) {
                try {
                    // isOpen()在线就发送
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendMessage2User(String params) throws IOException {
        HashMap hashMap = Json.toObject(params, HashMap.class);
        Integer sellerId = (Integer) hashMap.get(Constants.SELLER_ID);
        Integer userId = (Integer) hashMap.get(Constants.USER_ID);
        Integer goodsId = (Integer) hashMap.get(Constants.GOODS_ID);
        String msg = (String) hashMap.get(Constants.MSG);
        chatService.insert(new Chat(sellerId,userId,goodsId,msg));
        for (WebSocketSession user : users) {
            if (user.getAttributes().get(Constants.USER + sellerId).equals(sellerId)) {
                // isOpen()在线就发送
                if (user.isOpen()) {
                    user.sendMessage(new TextMessage(msg));
                }

            }
        }
    }

    public void sendMessagesToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                //isOpen()在线就发送
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
