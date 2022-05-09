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

    @Override
    public VteamUser getVteamUser(String username) {
        return userRepository.getById(username);
    }

    @Override
    public UserInfo getUserInfo(String username){
        return userInfoRepository.getById(username);
    }

    public int editUserInfo(String username,String name, String sex, String email, String school) {
        UserInfo userinfo = userInfoRepository.getById(username);
        userinfo.setName(name);
        userinfo.setSex(sex);
        userinfo.setEmail(email);
        userinfo.setSchool(school);
        userInfoRepository.save(userinfo);
        return 1;
    }

    @Override
    public int editUserPwd(String username, String oldPwd, String newPwd) {
        VteamUser vteamuser = userRepository.getById(username);
        String myOldPwd = vteamuser.getPassword();
        if(oldPwd.equals(myOldPwd)){
            vteamuser.setPassword(newPwd);
            userRepository.save(vteamuser);
            return 1;
        }else{
            return -1;
        }
    }
}
