package com.rainbow.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WxUser {
    private Long id;
    private String nickname;
    private String starSign;
    private LocalDate birthday;
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
}
