package com.erhuo.mapper;

import com.erhuo.pojo.WxAccessToken;

public interface WxAccessTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxAccessToken record);

    int insertSelective(WxAccessToken record);

    WxAccessToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxAccessToken record);

    int updateByPrimaryKey(WxAccessToken record);

    int getToken(String token);

    int deleteToken(String token);

    int getUserIdByToken(String token);

}