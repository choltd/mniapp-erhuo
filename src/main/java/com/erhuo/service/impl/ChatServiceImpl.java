package com.erhuo.service.impl;

import com.erhuo.mapper.*;
import com.erhuo.pojo.Chat;
import com.erhuo.pojo.Goods;
import com.erhuo.pojo.Order;
import com.erhuo.pojo.User;
import com.erhuo.service.ChatService;
import com.erhuo.vo.ChatVO;
import com.erhuo.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private WxAccessTokenMapper wxAccessTokenMapper;
    @Autowired
    private AdvertisementMapper advertisementMapper;
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ChatVO chatBefore(String goodsId, String token) {
        ChatVO chatVO = goodsMapper.getUser4chat(goodsId);
        User user = userMapper.getUer4chat(token);
        chatVO.setBuyUserId(user.getId());
        chatVO.setBuyerAvatar(user.getAvatarUrl());
        return chatVO;
    }

    @Override
    public void insert(Chat chat) {
        chatMapper.insert(chat);
    }

    @Override
    public ChatVO getChatVO(String sellerId, String buyUserId) {
        return new ChatVO(Integer.parseInt(sellerId), userMapper.getAvatar(sellerId), Integer.parseInt(buyUserId), userMapper.getAvatar(buyUserId));
    }

    @Override
    public GoodsVO detail(String id) {
        GoodsVO goodsVO = goodsMapper.detail(id);
        List<String> images = advertisementMapper.getImg(id);
        goodsVO.setImage(images.get(0));
        return goodsVO;
    }

    @Override
    public boolean newOrder(Order order) {
        return orderMapper.insert(order) == 1;
    }

    @Override
    public List<ChatVO> chatList(String token) {
        List<ChatVO> sellList = chatMapper.sellChatList(token);//作为卖家
        List<ChatVO> buyList = chatMapper.buyChatList(token);//作为卖家
        sellList.addAll(buyList);
        return sellList.stream()
                .filter(sell -> sellList.stream().noneMatch(buy -> sell.getSendTime().before(buy.getSendTime()) && Objects.equals(sell.getSellerUserId(), buy.getBuyUserId()) && Objects.equals(sell.getBuyUserId(), buy.getSellerUserId()))).collect(Collectors.toList());
    }
}
