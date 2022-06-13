package com.erhuo.service.impl;

import com.alibaba.druid.support.opds.udf.ExportSelectListColumns;
import com.erhuo.global.Constants;
import com.erhuo.mapper.*;
import com.erhuo.pojo.*;
import com.erhuo.service.UserService;
import com.erhuo.utils.HttpClientUtils;
import com.erhuo.utils.MD5Util;
import com.erhuo.utils.RedisUtil;
import com.erhuo.utils.WXCore;
import com.erhuo.vo.GoodsVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Autowired
    private WxAccessTokenMapper wxAccessTokenMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CategoryMapper categoryMapper;

    public ObjectMapper objectMapper = new ObjectMapper();

    public List<Advertisement> advertisements = new ArrayList<>();


    private String getOpenId() {
        return (String) redisUtil.get(Constants.OPENID);
    }

    private String getSKey() {
        return (String) redisUtil.get(Constants.SESSION_KEY);
    }

    /**
     * 验证token，存入redis
     *
     * @param token
     * @return
     */
    private boolean checkToken(String token) {
        String openid = (String) redisUtil.get(Constants.OPENID);
        String sKey = (String) redisUtil.get(Constants.SESSION_KEY);
        String uToken = MD5Util.getMD5(openid + sKey);
        if (token.equals(uToken)) {
            redisUtil.set(Constants.TOKEN, token);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public String keyCode(String code, String encryptedData, String iv, String appid, String secret) throws IOException {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + appid + "&secret=" + secret + "&js_code=" + code +
                "&grant_type=authorization_code";
        String openid = "";
        String sessionKey = "";
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(HttpClientUtils.doGet(url, new HashMap<>()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonNode != null) {
            openid = jsonNode.get(Constants.OPENID).textValue();
            sessionKey = jsonNode.get("session_key").textValue();

            redisUtil.set(Constants.OPENID, openid);
            redisUtil.set(Constants.SESSION_KEY, sessionKey);

            String userInfo = codeDecrypt(appid, sessionKey, encryptedData, iv);
            User user = objectMapper.readValue(userInfo, User.class);

            user.setOpenid(openid);
            userMapper.insert(user);

            String token = MD5Util.getMD5(openid + sessionKey);
            wxAccessTokenMapper.insertSelective(new WxAccessToken(null, token, null, Integer.parseInt(userMapper.queryId(openid))));//保存token
            return token;
        } else {
            return null;
        }
    }


    @Override
    public User userInfo(String token) {
        if (checkToken(token)) {
            return userMapper.userInfo(getOpenId());
        }
        return null;
    }

    @Override
    public List<Advertisement> banners() {
        return advertisementMapper.banners();
    }


    @Override
    public List<GoodsVO> freeGood() {
        return voDo(goodsMapper.freeGoods());
    }

    @Override
    public List<GoodsVO> newGoods() {
        return voDo(goodsMapper.newGoods());
    }

    @Transactional
    @Override
    public GoodsVO detail(String id) {
        GoodsVO goodsVO = goodsMapper.detail(id);
        List<String> images = advertisementMapper.getImg(id);
        goodsVO.setImages(images);
        infoSave(goodsVO, Integer.parseInt(id));
        browse(id);
        return goodsVO;
    }


    @Transactional
    @Override
    public List<GoodsVO> released(String token) {
        if (checkToken(token)) {
            List<GoodsVO> goodsVOList = goodsMapper.released((String) redisUtil.get(Constants.OPENID));
            return voDo(goodsVOList);
        }
        return null;
    }

    @Override
    public List<GoodsVO> sold(String token) {
        if (checkToken(token)) {
            List<GoodsVO> goodsVOList = goodsMapper.sold((String) redisUtil.get(Constants.OPENID));
            return voDo(goodsVOList);
        }
        return null;
    }


    @Override
    public List<GoodsVO> bought(String token) {
        if (checkToken(token)) {
            List<GoodsVO> goodsVOList = goodsMapper.bought((String) redisUtil.get(Constants.OPENID));
            return voDo(goodsVOList);
        }
        return null;
    }

    @Override
    public List<GoodsVO> favorite(String token) {
        if (checkToken(token)) {
            List<GoodsVO> goodsVOList = goodsMapper.favorite((String) redisUtil.get(Constants.OPENID));
            return voDo(goodsVOList);
        }
        return null;
    }

    @Override
    public List<Category> categories() {
        return categoryMapper.categoryList();
    }

    @Override
    public String upload(HttpServletRequest request, MultipartFile file) {
        String type;
        String fileName;
        if (!file.isEmpty()) {
            fileName = file.getOriginalFilename();
            type = (fileName != null && fileName.contains(Constants.DOT)) ? fileName.substring(fileName.lastIndexOf(Constants.DOT) + 1) : null;
            if (type != null) {
                if (Constants.GIF.equalsIgnoreCase(type) || Constants.PNG.equalsIgnoreCase(type) || Constants.JPG.equalsIgnoreCase(type)) {
                    String root = request.getSession().getServletContext().getRealPath("/");
                    String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 9);
                    String filePath = root + Constants.IMAGE_PATH + "\\" + uuid + Constants.DOT + type;
                    String realPath = Constants.IMAGE_REALPATH + uuid + Constants.DOT + type;
                    advertisements.add(new Advertisement(realPath));
                    try {
                        file.transferTo(new File(filePath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return filePath;
                }
            }
        }
        return null;
    }

    @Transactional
    @Override
    public String publishInfo(Goods goods,String token) {
        goods.setSellerUserId(wxAccessTokenMapper.getUserIdByToken(token));
        goodsMapper.insertBackId(goods);
        if(advertisements.size() > 0 && advertisements.size() <=3){
            for (Advertisement advertisement : advertisements) {
                advertisement.setAdName(goods.getGoodsName());
                advertisement.setGoodsId(goods.getId());
                advertisementMapper.insert(advertisement);
            }
            advertisements.clear();
            return String.valueOf(goods.getId());
        }
        return null;
    }

    @Override
    public String checkOpenId(String openId) {
        String value = (String) redisUtil.get(Constants.OPENID);
        if (openId.equals(value)) {
            return "true";
        }
        return "false";
    }

    @Override
    public boolean collect(String id) {
        collected(id, Constants.ADD);
        return goodsInfoMapper.collect(id) == 1;
    }

    @Override
    public boolean unCollect(String id) {
        collected(id, Constants.CUT);
        return goodsInfoMapper.unCollect(id) == 1;
    }

    @Override
    public boolean browse(String id) {
        browsed(id);
        return goodsInfoMapper.browse(id) == 1;
    }

    /**
     * 收藏次数操作，浏览次数+1，存入redis和mysql
     *
     * @param id     goodsId
     * @param option add:+1;cut:-1
     */
    public void collected(String id, String option) {
        GoodsInfo goodsInfo = browsed(id);
        if (option.equals(Constants.ADD)) {
            goodsInfo.setCollected(goodsInfo.getCollected() + 1);
        } else if (option.equals(Constants.CUT) && goodsInfo.getCollected() >= 1) {
            goodsInfo.setCollected(goodsInfo.getCollected() - 1);
        }
        redisUtil.hset(Constants.GOODS + id, id, goodsInfo);

    }

    /**
     * 获取redis中goodsInfo，浏览次数+1，存入redis
     *
     * @param id goodsId
     * @return goodsInfo
     */
    public GoodsInfo browsed(String id) {
        GoodsInfo goodsInfo = objectMapper.convertValue(redisUtil.hget(Constants.GOODS + id, id), GoodsInfo.class);
        goodsInfo.setBrowsed(goodsInfo.getBrowsed() + 1);
        redisUtil.hset(Constants.GOODS + id, id, goodsInfo);
        return goodsInfo;
    }


    /**
     * 给vo添加第一张图片并将info存入redis
     *
     * @param goodsVOList
     * @return
     */
    public List<GoodsVO> voDo(List<GoodsVO> goodsVOList) {
        for (GoodsVO goodsVO : goodsVOList) {
            Integer id = goodsVO.getId();
            goodsVO.setImage(advertisementMapper.getImg(id + "").get(0));
            infoSave(goodsVO, id);
        }
        return goodsVOList;
    }

    /**
     * 向redis添加数据
     *
     * @param goodsVO
     * @param id      goodsId
     */
    public void infoSave(GoodsVO goodsVO, int id) {
        if (!redisUtil.hHasKey(Constants.GOODS + id, id + "")) {
            redisUtil.hset(Constants.GOODS + id, id + "", new GoodsInfo(goodsVO.getCollected(), goodsVO.getBrowsed(), id));
        }
    }

    /**
     * 小程序数据解密
     *
     * @param appid
     * @param sessionKey
     * @param encryptedData
     * @param iv
     * @return 用户信息json字符串
     */
    public String codeDecrypt(String appid, String sessionKey, String encryptedData, String iv) {
        return WXCore.decrypt(appid, encryptedData, sessionKey, iv);
    }

}
