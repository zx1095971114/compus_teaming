package com.example.Vteam.dao.Interface;

import com.example.Vteam.entity.RecruitInfo;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
