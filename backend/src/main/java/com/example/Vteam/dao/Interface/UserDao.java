package com.example.Vteam.dao.Interface;

import com.example.Vteam.entity.UserInfo;
import com.example.Vteam.entity.VteamUser;

/**
 * @Author Lang wenchong
 * @Date 2022/5/6 10:19
 * @Version 1.0
 */


public interface UserDao {
    int insertVteamUser(VteamUser vteamUser);
    int insertUserInfo(UserInfo userInfo);
}
