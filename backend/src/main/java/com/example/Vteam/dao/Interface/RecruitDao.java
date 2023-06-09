package com.example.Vteam.dao.Interface;

import com.example.Vteam.entity.RecruitInfo;
import com.example.Vteam.entity.UserInfo;
import com.example.Vteam.entity.VteamInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 14:36
 * @Version 1.0
 */

@Component
public interface RecruitDao {
    RecruitInfo createRecruitInfo();

    int insertRecruitInfo(RecruitInfo recruitInfo);

    RecruitInfo getRecruitInfo(String rid);

    int updateRecruitInfo(RecruitInfo recruitInfo);

    List<RecruitInfo> getMyCreatedRecruit(String username);

    RecruitInfo getRecruitInfoByTid(String tid);
    Map getRecruitInfo(String username, String rid);
    List<RecruitInfo> getValidRecruitInfo();

    UserInfo getUserInfoByUsername(String username);

    VteamInfo getVteamInfoByTid(String tid);
}
