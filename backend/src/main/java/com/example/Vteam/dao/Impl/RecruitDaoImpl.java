package com.example.Vteam.dao.Impl;

import com.example.Vteam.dao.Interface.RecruitDao;
import com.example.Vteam.entity.RecruitInfo;
import com.example.Vteam.repository.RecruitInfoRepository;
import com.example.Vteam.utils.MyFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 14:36
 * @Version 1.0
 */
@Component
public class RecruitDaoImpl implements RecruitDao {

    @Autowired
    RecruitInfoRepository recruitInfoRepository;

    @Override
    public RecruitInfo createRecruitInfo() {
        String rid = UUID.randomUUID().toString().replaceAll("-", "");
        return new RecruitInfo(rid, "", "", 0, "", "", "", "", "", MyFunction.getTime(), "", "", 0, 0);
    }

    @Override
    public int insertRecruitInfo(RecruitInfo recruitInfo) {
        if (!recruitInfoRepository.existsById(recruitInfo.getRid())) {
            recruitInfoRepository.save(recruitInfo);
            return 1;
        } else {
            return -1;
        }
    }
}
