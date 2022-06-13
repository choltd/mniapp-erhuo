package com.erhuo.mapper;

import com.erhuo.pojo.UserFavorites;

public interface UserFavoritesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFavorites record);

    int insertSelective(UserFavorites record);

    UserFavorites selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFavorites record);

    int updateByPrimaryKey(UserFavorites record);
}