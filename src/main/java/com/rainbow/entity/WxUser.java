package com.rainbow.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WxUser {
    private Long id;
    private String nickname;
    /** 头像在服务器上的访问地址 */
    private String avatar;
    private String starSign;
    private LocalDate birthday;
    /** 年龄在注册时直接计算存储，便于展示 */
    private Integer age;
    private Integer height;
    private Integer weight;
    /**
     * 1:positive 0:negative side:side
     */
    private String attribute;
    private String location;
    private Double latitude;
    private Double longitude;
    private String password;

    /**
     * 距离登录用户的距离，非数据库字段
     */
    private transient Double distance;
}
