package com.example.Vteam.controller.Impl;

import com.example.Vteam.controller.Interface.UserController;
import com.example.Vteam.entity.UserInfo;
import com.example.Vteam.service.Interface.UserService;
import com.example.Vteam.utils.MyFunction;
import com.example.Vteam.utils.MyJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import static com.example.Vteam.utils.MyFunction.isLoggedIn;


@RestController
@RequestMapping(value = "/user/api")
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;

    @Override
    @RequestMapping(value = "/register")
    public MyJson register(@RequestParam("username") String username,
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

    @Override
    @RequestMapping(value = "/login")
    public MyJson login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        MyJson myJson = new MyJson();
        String token = userService.login(username, password);
        if (token.equals("wrong")) {
            myJson.setStatus(500);
            myJson.setMessage("账号或者密码错误");
        } else {
            myJson.setStatus(200);
            myJson.setMessage("登陆成功");
            Map<String, String> result = new HashMap<>();
            result.put("token", token);
            myJson.setResult(result);
        }
        return myJson;
    }

    @RequestMapping(value = "/getUserInfo")
    public MyJson getUserInfo(HttpServletRequest request,
                              @RequestParam("username") String username) {
        MyJson myJson = MyFunction.isLoggedIn(request);
        if (myJson.getStatus() == 403) {
            return myJson;
        }

        UserInfo userInfo = userService.getUserInfo(username);
//        System.out.println(userInfo.getAvatarPath());

        if (userInfo != null) {
            myJson.setResult(userInfo);
            myJson.setMessage("获取个人信息成功");
            myJson.setStatus(200);
        } else {
            myJson.setStatus(500);
            myJson.setMessage("获取个人信息失败");
        }

        return myJson;

    }

    @Override
    @RequestMapping(value = "/editUserInfo")
    public MyJson editUserInfo(HttpServletRequest request, String username, String name, String sex, String email, String school) {
        MyJson myjson = isLoggedIn(request);
        if (myjson.getStatus() == 403)
            return myjson;
        else {
            int suc = userService.editUserInfo(username, name, sex, email, school);
            if (suc == 1) {
                myjson.setStatus(200);
                myjson.setMessage("修改成功！");
            } else {
                myjson.setStatus(500);
                myjson.setMessage("修改失败！");
            }
            return myjson;
        }
    }

    @Override
    @RequestMapping(value = "/editUserPwd")
    public MyJson editUserPwd(HttpServletRequest request, String username, String oldPwd, String newPwd) {
        MyJson myjson = isLoggedIn(request);
        if (myjson.getStatus() == 403)
            return myjson;
        else {
            int suc = userService.editUserPwd(username, oldPwd, newPwd);
            if (suc == 1) {
                myjson.setStatus(200);
                myjson.setMessage("修改成功！");
            } else {
                myjson.setStatus(500);
                myjson.setMessage("修改失败！");
            }
            return myjson;
        }
    }


}
