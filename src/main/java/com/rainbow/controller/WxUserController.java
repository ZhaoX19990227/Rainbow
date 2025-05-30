package com.rainbow.controller;

import com.rainbow.entity.WxUser;
import com.rainbow.service.WxUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
public class WxUserController {
    private final WxUserService service;

    public WxUserController(WxUserService service) {
        this.service = service;
    }

    @PostMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile avatar) throws Exception {
        if (avatar == null || avatar.isEmpty()) {
            throw new IllegalArgumentException("未选择文件");
        }
        String url = com.rainbow.util.FileUtil.uploadFile(avatar);
        return url;
    }

    @PostMapping("/register")
    public int register(@ModelAttribute WxUser user) throws Exception {
        return service.register(user, null);
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
