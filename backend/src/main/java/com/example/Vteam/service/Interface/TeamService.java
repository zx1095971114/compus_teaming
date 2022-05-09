package com.example.Vteam.service.Interface;

import com.example.Vteam.entity.VteamInfo;

import java.util.List;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 17:37
 * @Version 1.0
 */
public interface TeamService {

    List<VteamInfo> getMyAttendedTeam(String username);
}
