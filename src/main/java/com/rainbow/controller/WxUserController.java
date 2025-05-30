package com.rainbow.controller;

import com.rainbow.entity.WxUser;
import com.rainbow.service.WxUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class WxUserController {
    private final WxUserService service;

    public WxUserController(WxUserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public int register(@RequestBody WxUser user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public WxUser login(@RequestParam String nickname, @RequestParam String password) {
        return service.login(nickname, password);
    }

    @GetMapping("/list/{id}")
    public java.util.List<WxUser> list(@PathVariable("id") Long id) {
        return service.listWithDistance(id);
    }
}
