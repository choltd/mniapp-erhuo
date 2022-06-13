package com.erhuo.service;

import com.erhuo.pojo.Advertisement;
import com.erhuo.pojo.Category;
import com.erhuo.pojo.Goods;
import com.erhuo.pojo.User;
import com.erhuo.vo.GoodsVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface UserService {
    String keyCode(String code, String encryptedData, String iv, String appid, String secret) throws IOException;

    User userInfo(String token);

    List<Advertisement> banners();

    List<GoodsVO> newGoods();

    List<GoodsVO> freeGood();

    GoodsVO detail(String id);

    List<GoodsVO> released(String token);

    List<GoodsVO> sold(String token);

    List<GoodsVO> bought(String token);

    List<GoodsVO> favorite(String token);

    List<Category> categories();

    String upload(HttpServletRequest request, MultipartFile file);

    String checkOpenId(String openId);

    boolean collect(String id);

    boolean unCollect(String id);

    boolean browse(String id);

    String publishInfo(Goods goods,String token);
}
