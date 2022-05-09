package com.example.Vteam.controller.Interface;

import com.example.Vteam.utils.MyJson;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 13:10
 * @Version 1.0
 */

public interface UserController {

    MyJson register(@RequestParam("username") String username,
                    @RequestParam("password") String password,
                    @RequestParam("name") String name,
                    @RequestParam("email") String email,
                    @RequestParam("phoneNumber") String phoneNumber,
                    @RequestParam("school") String school,
                    @RequestParam("grade") String grade,
                    @RequestParam("sex") String sex,
                    @RequestParam("avatar") MultipartFile avatar);

    MyJson login(@RequestParam("username") String username,
                 @RequestParam("password") String password);
}
