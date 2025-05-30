package com.rainbow.mapper;

import com.rainbow.entity.WxUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WxUserMapper {
    @Select("SELECT * FROM wx_user WHERE nickname = #{nickname} LIMIT 1")
    WxUser findByNickname(@Param("nickname") String nickname);

    @Select("SELECT * FROM wx_user WHERE id = #{id}")
    WxUser findById(@Param("id") Long id);

    @Select("SELECT * FROM wx_user")
    java.util.List<WxUser> findAll();

    int insert(WxUser user);
}
