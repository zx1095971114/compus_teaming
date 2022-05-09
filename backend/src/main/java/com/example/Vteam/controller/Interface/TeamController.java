package com.example.Vteam.controller.Interface;

import com.example.Vteam.utils.MyJson;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public interface TeamController {
    MyJson getTeamInfo(HttpServletRequest request,
                       String tid);

    //获取用户组队信息
    MyJson getUserTeamInfo(HttpServletRequest request,
                           @RequestParam("username") String username);
}
