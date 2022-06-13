package com.erhuo.pojo;

import java.util.Date;
import java.util.Objects;

public class Chat {
    private Integer id;

    private Integer sellerUserId;

    private Integer buyUserId;

    private Date sendTime;

    private Integer goodsId;

    private String content;
    private Integer state;

    public Chat() {
    }

    public Chat(Integer sellerUserId, Integer buyUserId, Integer goodsId, String content) {
        this.sellerUserId = sellerUserId;
        this.buyUserId = buyUserId;
        this.goodsId = goodsId;
        this.content = content;
    }
    public Chat(Integer sellerUserId, Integer buyUserId, Integer goodsId, String content,Date sendTime) {
        this.sellerUserId = sellerUserId;
        this.buyUserId = buyUserId;
        this.goodsId = goodsId;
        this.content = content;
        this.sendTime = sendTime;
    }
    public Chat(Integer sellerUserId, Integer buyUserId, Integer goodsId, String content,Date sendTime,Integer state) {
        this.sellerUserId = sellerUserId;
        this.buyUserId = buyUserId;
        this.goodsId = goodsId;
        this.content = content;
        this.sendTime = sendTime;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerUserId() {
        return sellerUserId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setSellerUserId(Integer sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public Integer getBuyUserId() {
        return buyUserId;
    }

    public void setBuyUserId(Integer buyUserId) {
        this.buyUserId = buyUserId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return sellerUserId.equals(chat.sellerUserId) && buyUserId.equals(chat.buyUserId) && sendTime.equals(chat.sendTime) && goodsId.equals(chat.goodsId) && content.equals(chat.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyUserId, sendTime, goodsId, content);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", sellerUserId=" + sellerUserId +
                ", buyUserId=" + buyUserId +
                ", sendTime=" + sendTime +
                ", goodsId=" + goodsId +
                ", content='" + content + '\'' +
                '}';
    }
}