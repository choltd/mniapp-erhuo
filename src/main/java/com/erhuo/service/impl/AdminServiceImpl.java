package com.erhuo.service.impl;

import com.erhuo.mapper.AdminMapper;
import com.erhuo.mapper.CategoryMapper;
import com.erhuo.mapper.GoodsMapper;
import com.erhuo.pojo.Admin;
import com.erhuo.pojo.Category;
import com.erhuo.pojo.Goods;
import com.erhuo.service.AdminService;
import com.erhuo.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private CategoryMapper categoryMapper;



    @Override
    public String login(Admin admin, HttpSession session) {
        if(adminMapper.login(admin) == 1){
            session.setAttribute("user",admin);
            return "true";
        }else{
            return "false";
        }
    }

    @Override
    public List<GoodsVO> goodsList(int offset) {
        return goodsMapper.goodsList(offset);
    }

    @Override
    public int goodsTotal() {
        return goodsMapper.goodsTotal();
    }

    @Override
    public List<GoodsVO> goodsListQuery(Goods goods, int offset) {
        return goodsMapper.goodsListQuery(goods,offset);
    }

    @Override
    public List<Category> categoryList() {
        return categoryMapper.categoryList();
    }

    @Override
    public boolean unShelve(String goodsId) {
        return goodsMapper.unShelve(goodsId) == 1;
    }

    @Override
    public int goodsQueryTotal(Goods goods) {
        return goodsMapper.goodsQueryTotal(goods);
    }


}
