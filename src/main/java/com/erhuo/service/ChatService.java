package com.erhuo.service;

import com.erhuo.pojo.Chat;
import com.erhuo.pojo.Goods;
import com.erhuo.pojo.Order;
import com.erhuo.vo.ChatVO;
import com.erhuo.vo.GoodsVO;

import java.util.List;

public interface ChatService {
    ChatVO chatBefore(String id, String token);

    void insert(Chat chat);

    ChatVO getChatVO(String sellerId,String buyUserId);

    GoodsVO detail(String id);

    boolean newOrder(Order order);

    List<ChatVO> chatList(String token);
}
