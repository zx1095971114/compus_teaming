package com.example.Vteam.service.Impl;

import com.example.Vteam.dao.Interface.HistoryDao;
import com.example.Vteam.dao.Interface.UserDao;
import com.example.Vteam.entity.History;
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
import java.util.UUID;

import static com.example.Vteam.utils.MyFunction.findKeyByValue;
import static com.example.Vteam.utils.Token.tokenMap;

/**
 * @Author Lang wenchong
 * @Date 2022/5/6 10:25
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    HistoryDao historyDao;

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
        String hid = UUID.randomUUID().toString().replaceAll("-", "");
        History history = new History(hid, username, "注册账号", name + "注册账号加入了北洋有约平台", registerTime);
        int suc3 = historyDao.insertHistory(history);
//        System.out.println(vteamUser.getUsername());
        int suc1 = userDao.insertVteamUser(vteamUser);
        int suc2 = userDao.insertUserInfo(userInfo);
//        System.out.println(suc1 + "" + suc2);
        if (suc1 == 1 && suc2 == 1 && suc3 == 1) {
            return 1;
        }
        return -1;
    }

    @Override
    public String login(String username, String password) {
        VteamUser user = userDao.getVteamUser(username);
        if (user == null || !user.getPassword().equals(password)) {
            return "wrong";
        }
        String tmp = findKeyByValue(username, tokenMap);
        String token = tmp == null ? UUID.randomUUID().toString().replaceAll("-", "") : tmp;
        tokenMap.put(token, username);
        String hid = UUID.randomUUID().toString().replaceAll("-", "");
        History history = new History(hid, username, "登录账号", "你登录了北洋有约平台", MyFunction.getTime());
        historyDao.insertHistory(history);
        return token;
    }

    public UserInfo getUserInfo(String username) {
        return userDao.getUserInfo(username);
    }

    public int editUserInfo(String username, String name, String sex, String email, String school) {
        int suc1 = userDao.editUserInfo(username, name, sex, email, school);
        String hid = UUID.randomUUID().toString().replaceAll("-", "");
        History history = new History(hid, username, "修改信息", "你修改了个人信息", MyFunction.getTime());
        int suc2 = historyDao.insertHistory(history);
        return suc1 & suc2;
    }

    @Override
    public int editUserPwd(String username, String oldPwd, String newPwd) {
        int suc1 = userDao.editUserPwd(username, oldPwd, newPwd);
        String hid = UUID.randomUUID().toString().replaceAll("-", "");
        History history = new History(hid, username, "修改信息", "你修改了个人密码", MyFunction.getTime());
        int suc2 = historyDao.insertHistory(history);
        return suc1 & suc2;
    }
}
