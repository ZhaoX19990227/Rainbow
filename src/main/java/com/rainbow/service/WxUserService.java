package com.rainbow.service;

import com.rainbow.entity.WxUser;
import com.rainbow.mapper.WxUserMapper;
import org.springframework.stereotype.Service;

@Service
public class WxUserService {
    private final WxUserMapper mapper;

    public WxUserService(WxUserMapper mapper) {
        this.mapper = mapper;
    }

    public WxUser login(String nickname, String password) {
        WxUser user = mapper.findByNickname(nickname);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    public int register(WxUser user) {
        return mapper.insert(user);
    }
}
