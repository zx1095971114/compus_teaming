package com.example.Vteam.controller.Impl;


import com.example.Vteam.controller.Interface.TeamController;
import com.example.Vteam.entity.UserInfo;
import com.example.Vteam.service.Interface.TeamService;
import com.example.Vteam.service.Interface.UserService;
import com.example.Vteam.utils.MyFunction;
import com.example.Vteam.utils.MyJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.Vteam.utils.MyFunction.isLoggedIn;

@RestController
@RequestMapping(value = "/team/api")
public class TeamControllerImpl implements TeamController {
    @Autowired
    TeamService teamService;

    @Override
    @RequestMapping(value = "/getTeamInfo")
    public MyJson getTeamInfo(HttpServletRequest request, String tid) {
        MyJson myjson = isLoggedIn(request);
        if(myjson.getStatus() == 403)
            return myjson;
        else{
            List<UserInfo> mylist = teamService.getTeamInfo(tid);
            myjson.setResult(mylist);
            return myjson;
        }
    }

    @Override
    @RequestMapping(value = "/getUserTeamInfo")
    public MyJson getUserTeamInfo(HttpServletRequest request,
                                  @RequestParam("username") String username){
        MyJson myJson = MyFunction.isLoggedIn(request);
        if(myJson.getStatus() == 403){ return myJson; }

        List<Map<String,Object>> result = teamService.getUserTeamInfo(username);
        if (result != null){
            myJson.setResult(result);
            myJson.setStatus(200);
            myJson.setMessage("获取用户队伍信息成功");
        } else {
            myJson.setStatus(500);
            myJson.setMessage("获取用户队伍信息失败");
        }

        return myJson;
    }
}
