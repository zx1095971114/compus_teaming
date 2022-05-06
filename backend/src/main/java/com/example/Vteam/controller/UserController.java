package com.example.Vteam.controller;

import com.example.Vteam.service.Interface.UserService;
import com.example.Vteam.utils.MyJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/user/api")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register")
    private MyJson register(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("name") String name,
                            @RequestParam("email") String email,
                            @RequestParam("phoneNumber") String phoneNumber,
                            @RequestParam("school") String school,
                            @RequestParam("grade") String grade,
                            @RequestParam("sex") String sex,
                            @RequestParam("avatar") MultipartFile avatar) {
        MyJson myJson = new MyJson();
        // 新建user存储在数据库中
        int suc = userService.register(username, password, name, email, phoneNumber, school, grade, sex, avatar);
        if (suc == 1) {
            myJson.setStatus(200);
            myJson.setMessage("注册成功!");
        } else {
            myJson.setStatus(500);
            myJson.setMessage("注册失败，账号已存在！");
        }
        return myJson;
    }

}
