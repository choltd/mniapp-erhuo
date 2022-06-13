package com.erhuo.pojo;

import java.util.Date;

public class Advertisement {
    private Integer id;

    private String adName;

    private String coverImg;

    private Date createTime;

    private Byte state;

    private Integer goodsId;

    public Advertisement() {
    }

    public Advertisement(String coverImg) {
        this.coverImg = coverImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", adName='" + adName + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", createTime=" + createTime +
                ", state=" + state +
                ", goodsId=" + goodsId +
                '}';
    }
}