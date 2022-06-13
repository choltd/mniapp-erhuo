package com.erhuo.service.impl;

import com.erhuo.pojo.Chat;
import com.erhuo.webSocket.MyWebSocketHandler;
import org.junit.Test;
import org.springframework.web.socket.TextMessage;

import java.util.*;
import java.util.stream.Collectors;


public class ChatServiceImplTest {

    @Test
    public void testSendMessage() {
        MyWebSocketHandler handler = new MyWebSocketHandler();
        handler.sendMessageToUser("20", new TextMessage("let"));
    }

    @Test
    public void testChatList() {
        List<Chat> list1 = new ArrayList<>();
        list1.add(new Chat(1, 5, 3, "test", new Date(Date.parse("Mon 6 Jan 2021 13:3:05"))));
        list1.add(new Chat(1, 2, 3, "demo", new Date(Date.parse("Mon 6 Jan 2021 13:0:00"))));
        List<Chat> list2 = new ArrayList<>();
        list2.add(new Chat(2, 1, 3, "demo", new Date(Date.parse("Mon 6 Jan 2021 13:3:00"))));
        list2.add(new Chat(2, 3, 3, "333", new Date(Date.parse("Mon 6 Jan 2021 13:4:00"))));
        list2.add(new Chat(2, 4, 3, "222", new Date(Date.parse("Mon 6 Jan 2021 13:5:00"))));
        list2.add(new Chat(2, 5, 3, "go", new Date(Date.parse("Mon 6 Jan 2021 13:2:00"))));
        list1.addAll(list2);
        List<Chat> collect = list1.stream()
                .filter(sell -> list1.stream().noneMatch(buy -> sell.getSendTime().before(buy.getSendTime()) && Objects.equals(sell.getSellerUserId(), buy.getBuyUserId()) && Objects.equals(sell.getBuyUserId(), buy.getSellerUserId()))).collect(Collectors.toList());

        collect.forEach(System.out::println);
    }

}
