package com.erhuo.controller;

import com.erhuo.pojo.Admin;
import com.erhuo.pojo.Goods;
import com.erhuo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public @ResponseBody
    String login(Admin admin, HttpSession session) {
        return adminService.login(admin, session);
    }

    @GetMapping("/loginS")
    public String loginS() {
        return "index";
    }

    @GetMapping("/goods/{page}/{offset}")
    public String goodsList(@PathVariable("page") int page, @PathVariable("offset") int offset, Model model) {
        model.addAttribute("goodsList", adminService.goodsList(offset));
        model.addAttribute("total", adminService.goodsTotal());
        model.addAttribute("categoryList", adminService.categoryList());
        model.addAttribute("page", "page" + page);
        return "goods";
    }

    @GetMapping("/queryBy")
    public String goodsListQuery(Model model, Goods goods) {
        model.addAttribute("goodsList", adminService.goodsListQuery(goods, 0));
        return "goods::listBody";
    }

    @GetMapping("/queryByTotal")
    public @ResponseBody int goodsQueryTotal(Goods goods){
        return adminService.goodsQueryTotal(goods);
    }

    @GetMapping("/unShelve")
    public @ResponseBody
    boolean unShelve(String goodsId) {
        return adminService.unShelve(goodsId);
    }


}
