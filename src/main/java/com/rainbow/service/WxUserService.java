package com.rainbow.service;

import com.rainbow.entity.WxUser;
import com.rainbow.mapper.WxUserMapper;
import com.rainbow.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

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

    public int register(WxUser user, MultipartFile avatar) throws Exception {
        if (user.getBirthday() != null) {
            user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());
        }
        // 头像已由前端上传并设置为URL，无需处理avatar文件
        return mapper.insert(user);
    }

    public List<WxUser> listWithDistance(Long userId) {
        WxUser current = mapper.findById(userId);
        List<WxUser> all = mapper.findAll();
        for (WxUser u : all) {
            if (current.getLatitude() != null && current.getLongitude() != null &&
                    u.getLatitude() != null && u.getLongitude() != null) {
                u.setDistance(distance(current.getLatitude(), current.getLongitude(), u.getLatitude(), u.getLongitude()));
            }
        }
        return all;
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371.0; // km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }
}
