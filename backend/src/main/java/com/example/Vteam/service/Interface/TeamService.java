package com.example.Vteam.service.Interface;

import com.example.Vteam.entity.UserInfo;
import com.example.Vteam.entity.VteamInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 17:37
 * @Version 1.0
 */
public interface TeamService {

    List<VteamInfo> getMyAttendedTeam(String username);
    List<UserInfo> getTeamInfo(String tid);


    //用户参加的所有已经组好队的队伍信息
    List<Map<String,Object>> getUserTeamInfo(String username);
}
