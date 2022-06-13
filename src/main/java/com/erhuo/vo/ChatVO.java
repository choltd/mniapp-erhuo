package com.erhuo.vo;

import java.util.Date;

public class ChatVO {

    private Integer sellerUserId;
    private String sellerAvatar;
    private Integer buyUserId;
    private String buyerAvatar;
    private String content;
    private Date sendTime;
    private String nickName;
    private String goodsId;
    private String goodsImage;

    public ChatVO() {
    }

    public ChatVO(Integer sellerUserId, String sellerAvatar, Integer buyUserId, String buyerAvatar) {
        this.sellerUserId = sellerUserId;
        this.sellerAvatar = sellerAvatar;
        this.buyUserId = buyUserId;
        this.buyerAvatar = buyerAvatar;
    }

    public Integer getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(Integer sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public String getSellerAvatar() {
        return sellerAvatar;
    }

    public void setSellerAvatar(String sellerAvatar) {
        this.sellerAvatar = sellerAvatar;
    }

    public Integer getBuyUserId() {
        return buyUserId;
    }

    public void setBuyUserId(Integer buyUserId) {
        this.buyUserId = buyUserId;
    }

    public String getBuyerAvatar() {
        return buyerAvatar;
    }

    public void setBuyerAvatar(String buyerAvatar) {
        this.buyerAvatar = buyerAvatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }
}
