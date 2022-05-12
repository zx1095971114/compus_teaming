package com.example.Vteam.UserServiceTest;


import com.example.Vteam.entity.UserInfo;
import com.example.Vteam.service.Interface.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static com.example.Vteam.utils.MyFunction.colorfulPrint;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @Author Lang wenchong
 * @Date 2022/5/12 10:50
 * @Version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ContextConfiguration(locations = {"classpath:application.yml"})
public class UserServiceTest {
    @Autowired
    UserService userService;

    public String username = "langwenchong";
    public String password = "123456";


    @Test
    @Transactional
    public void loginTest() {
        String token = userService.login(username, password);
        assertTrue(!token.equals("wrong"));
    }

    @Test
    @Transactional
    public void getUserInfoTest() {
        UserInfo userInfo = userService.getUserInfo(username);
        colorfulPrint(userInfo.toString());
    }

    @Test
    @Transactional
    public void editUserInfoTest() {
        int suc = userService.editUserInfo(username, "保护最帅的翀酱", "男士", "1422257646@qq.com", "南开大学");
        assertTrue(suc == 1);
    }

    @Test
    @Transactional
    public void editUserPwdTest() {
        int suc = userService.editUserPwd(username, password, "1234567");
        assertTrue(suc == 1);
    }

}
