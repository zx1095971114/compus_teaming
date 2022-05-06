package com.example.Vteam.dao.Impl;

import com.example.Vteam.dao.Interface.UserDao;
import com.example.Vteam.entity.UserInfo;
import com.example.Vteam.entity.VteamUser;
import com.example.Vteam.repository.UserInfoRepository;
import com.example.Vteam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Lang wenchong
 * @Date 2022/5/6 10:22
 * @Version 1.0
 */

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInfoRepository userInfoRepository;


    @Override
    public int insertVteamUser(VteamUser vteamUser) {
        if (!userRepository.existsById((vteamUser.getUsername()))) {
            userRepository.save(vteamUser);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int insertUserInfo(UserInfo userInfo) {
        if (!userInfoRepository.existsById((userInfo.getUsername()))) {
            userInfoRepository.save(userInfo);
            return 1;
        } else {
            return -1;
        }
    }
}
