package com.erhuo.mapper;

import com.erhuo.pojo.GoodsInfo;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    GoodsInfo selectByGoodsId(String goodsId);

    int collect(String id);

    int unCollect(String id);

    int browse(String id);
}