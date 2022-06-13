package com.erhuo.mapper;

import com.erhuo.pojo.Goods;
import com.erhuo.vo.ChatVO;
import com.erhuo.vo.GoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    List<GoodsVO> goodsList(int offset);

    int goodsTotal();

    List<GoodsVO> goodsListQuery(@Param("goods") Goods goods, @Param("offset") int offset);

    List<GoodsVO> newGoods();

    List<GoodsVO> freeGoods();

    GoodsVO detail(String id);

    List<GoodsVO> released(String openid);

    List<GoodsVO> sold(String openid);

    List<GoodsVO> bought(String openid);

    List<GoodsVO> favorite(String openid);

    int unShelve(String goodsId);

    int goodsQueryTotal(Goods goods);


    int insertBackId(Goods goods);

    String getUserId(String goodsId);

    ChatVO getUser4chat(String goodsId);

}