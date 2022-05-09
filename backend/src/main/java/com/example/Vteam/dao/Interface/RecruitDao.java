package com.example.Vteam.dao.Interface;

import com.example.Vteam.entity.RecruitInfo;
import org.springframework.stereotype.Component;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 14:36
 * @Version 1.0
 */

@Component
public interface RecruitDao {
    RecruitInfo createRecruitInfo();

    int insertRecruitInfo(RecruitInfo recruitInfo);
}
