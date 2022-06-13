package com.erhuo.pojo;

public class GoodsInfo {
    private Integer id;

    private Integer collected;

    private Integer browsed;

    private Integer goodsId;

    public GoodsInfo() {
    }

    public GoodsInfo(Integer collected, Integer browsed, Integer goodsId) {
        this.collected = collected;
        this.browsed = browsed;
        this.goodsId = goodsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCollected() {
        return collected;
    }

    public void setCollected(Integer collected) {
        this.collected = collected;
    }

    public Integer getBrowsed() {
        return browsed;
    }

    public void setBrowsed(Integer browsed) {
        this.browsed = browsed;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}