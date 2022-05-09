package com.example.Vteam.controller.Interface;

import com.example.Vteam.utils.MyJson;

import javax.servlet.http.HttpServletRequest;

public interface TeamController {
    MyJson getTeamInfo(HttpServletRequest request,
                       String tid);
}
