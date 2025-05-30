package com.rainbow.mapper;

import com.rainbow.entity.WxUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WxUserMapper {
    @Select("SELECT * FROM wx_user WHERE nickname = #{nickname} LIMIT 1")
    WxUser findByNickname(@Param("nickname") String nickname);

    int insert(WxUser user);
}
