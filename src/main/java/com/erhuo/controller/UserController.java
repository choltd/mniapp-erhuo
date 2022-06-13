package com.erhuo.controller;

import com.erhuo.pojo.Advertisement;
import com.erhuo.pojo.Category;
import com.erhuo.pojo.Goods;
import com.erhuo.pojo.User;
import com.erhuo.service.UserService;
import com.erhuo.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@PropertySource({"classpath:wx/wx.properties"})
@RestController
public class UserController {

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;

    @Autowired
    private UserService userService;

    @RequestMapping("/login/code")
    public String code(String code, String encryptedData, String iv) throws IOException {
        return userService.keyCode(code, encryptedData, iv, appid, secret);
    }

    @RequestMapping("/login/userInfo")
    public User userInfo(String token) {
        return userService.userInfo(token);
    }

    @GetMapping("/index/banners")
    public List<Advertisement> banners() {
        return userService.banners();
    }

    @GetMapping("/index/freeGoods")
    public List<GoodsVO> freeGoods() {
        return userService.freeGood();
    }

    @GetMapping("/index/newGoods")
    public List<GoodsVO> newGoods() {
        return userService.newGoods();
    }

    @GetMapping("/goods/detail")
    public GoodsVO detail(String id) {
        return userService.detail(id);
    }

    @GetMapping("/goods/released")
    public List<GoodsVO> released(String token) {
        return userService.released(token);
    }

    @GetMapping("/goods/sold")
    public List<GoodsVO> sold(String token) {
        return userService.sold(token);
    }

    @GetMapping("/goods/bought")
    public List<GoodsVO> bought(String token) {
        return userService.bought(token);
    }

    @GetMapping("/goods/favorite")
    public List<GoodsVO> favorite(String token) {
        return userService.favorite(token);
    }

    @GetMapping("/categories")
    public List<Category> categories() {
        return userService.categories();
    }

    @GetMapping("/checkOpenId")
    public String checkOpenId(String openId){
        return userService.checkOpenId(openId);
    }

    @GetMapping("/collect")
    public boolean collect(String id){
        return userService.collect(id);
    }
    @GetMapping("/browse")
    public boolean browsed(String id){
        return userService.browse(id);
    }
    @GetMapping("/unCollect")
    public boolean unCollect(String id){
        return userService.unCollect(id);
    }
    @RequestMapping("/publish/upload")
    public @ResponseBody
    String upload(HttpServletRequest request, @RequestParam(value = "upfile", required = true) MultipartFile file) {
        return userService.upload(request, file);
    }

    @RequestMapping("/publish/info/{token}")
    public @ResponseBody
    String publishInfo(Goods goods, @PathVariable("token") String token) {
        return userService.publishInfo(goods,token);
    }
}
