package com.erhuo.service;

import com.erhuo.pojo.Admin;
import com.erhuo.pojo.Category;
import com.erhuo.pojo.Goods;
import com.erhuo.vo.GoodsVO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AdminService {
    String login(Admin admin, HttpSession session);

    List<GoodsVO> goodsList(int offset);

    int goodsTotal();

    List<GoodsVO> goodsListQuery(Goods goods, int offset);

    List<Category> categoryList();


    boolean unShelve(String goodsId);

    int goodsQueryTotal(Goods goods);
}
