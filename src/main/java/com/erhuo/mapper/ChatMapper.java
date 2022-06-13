package com.erhuo.mapper;

import com.erhuo.pojo.Chat;
import com.erhuo.vo.ChatVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Chat record);

    int insertSelective(Chat record);

    Chat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKeyWithBLOBs(Chat record);

    int updateByPrimaryKey(Chat record);

    List<ChatVO> sellChatList(String token);

    List<ChatVO> buyChatList(String token);

}