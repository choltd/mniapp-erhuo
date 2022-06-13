package com.erhuo.mapper;

import com.erhuo.pojo.User;
import com.erhuo.vo.ChatVO;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User userInfo(String openid);

    String queryId(String openid);

    String getAvatar(String userId);

    User getUer4chat(String token);
}