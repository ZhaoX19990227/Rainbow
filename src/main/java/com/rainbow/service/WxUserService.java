package com.rainbow.service;

import com.rainbow.entity.WxUser;
import com.rainbow.mapper.WxUserMapper;
import org.springframework.stereotype.Service;

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

    public int register(WxUser user) {
        if (user.getBirthday() != null) {
            user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());
        }
        if (user.getLatitude() != null && user.getLongitude() != null) {
            user.setLocation(calcLocation(user.getLatitude(), user.getLongitude()));
        }
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

    private String calcLocation(double lat, double lon) {
        if (lat >= 39 && lat <= 41 && lon >= 116 && lon <= 117) {
            return "Beijing";
        } else if (lat >= 30 && lat <= 32 && lon >= 120 && lon <= 122) {
            return "Shanghai";
        }
        return "Unknown";
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
