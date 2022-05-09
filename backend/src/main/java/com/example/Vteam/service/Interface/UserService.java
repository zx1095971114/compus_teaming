package com.example.Vteam.service.Interface;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Lang wenchong
 * @Date 2022/5/6 10:45
 * @Version 1.0
 */

public interface UserService {
    int register(String username,
                 String password,
                 String name,
                 String email,
                 String phoneNumber,
                 String school,
                 String grade,
                 String sex,
                 MultipartFile avatar);
    int editUserInfo(String username,
                     String name,
                     String sex,
                     String email,
                     String school);
    int editUserPwd(String username,
                    String oldPwd,
                    String newPwd);
}
