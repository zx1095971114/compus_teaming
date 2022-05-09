package com.example.Vteam.service.Impl;

import com.example.Vteam.dao.Interface.UserDao;
import com.example.Vteam.entity.UserInfo;
import com.example.Vteam.entity.VteamUser;
import com.example.Vteam.service.Interface.UserService;
import com.example.Vteam.utils.MyFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author Lang wenchong
 * @Date 2022/5/6 10:25
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Value("${root}")
    private String root;

    @Override
    public int register(String username, String password, String name, String email, String phoneNumber, String school, String grade, String sex, MultipartFile avatar) {
        VteamUser vteamUser = new VteamUser(username, password, "学生");
        File directory = new File(root + "/userAvatar/");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String avatarPath = "/userAvatar/" + username + ".png";
        try {
            avatar.transferTo(new File(new File(root).getAbsolutePath() + avatarPath));
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        String registerTime = MyFunction.getTime();
        UserInfo userInfo = new UserInfo(username, email, name, phoneNumber, school, grade, sex, avatarPath, registerTime);
        System.out.println(vteamUser.getUsername());
        int suc1 = userDao.insertVteamUser(vteamUser);
        int suc2 = userDao.insertUserInfo(userInfo);
        System.out.println(suc1 + "" + suc2);
        if (suc1 == 1 && suc2 == 1) {
            return 1;
        }
        return -1;
    }

    @Override
    public UserInfo getUserInfo(String username){
        return userDao.getUserInfo(username);
    }
}
