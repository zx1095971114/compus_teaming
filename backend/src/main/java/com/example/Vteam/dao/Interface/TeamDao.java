package com.example.Vteam.dao.Interface;

import com.example.Vteam.entity.VteamInfo;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 14:18
 * @Version 1.0
 */
public interface TeamDao {
    VteamInfo createTeamInfo();

    int insertTeamInfo(VteamInfo vteamInfo);

    VteamInfo getVteamInfo(String tid);

    int updateVteamInfo(VteamInfo vteaminfo);
}
