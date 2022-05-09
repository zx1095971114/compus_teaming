package com.example.Vteam.dao.Interface;

import com.example.Vteam.entity.RecruitInfo;
import com.example.Vteam.entity.UserInfo;
import com.example.Vteam.entity.VteamInfo;
import com.example.Vteam.entity.VteamUser;

import java.util.List;

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


    List<VteamInfo> getMyAttendedTeam(String username);
    List<UserInfo> getTeamInfo(String tid);


    UserInfo getUserInfoByUsername(String username);

    List<VteamInfo> getUserVteamInfo(String username);

    VteamUser getVteamUserByUsername(String username);

    List<RecruitInfo> getRecruitInfoByTid(String tid);
}
