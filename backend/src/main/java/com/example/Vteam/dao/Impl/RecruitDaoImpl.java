package com.example.Vteam.dao.Impl;

import com.example.Vteam.dao.Interface.RecruitDao;
import com.example.Vteam.entity.RecruitInfo;
import com.example.Vteam.repository.RecruitInfoRepository;
import com.example.Vteam.utils.MyFunction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 14:36
 * @Version 1.0
 */
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

    @Override
    public RecruitInfo getRecruitInfo(String rid) {
        return recruitInfoRepository.getById(rid);
    }

    @Override
    public int updateRecruitInfo(RecruitInfo recruitInfo) {
        recruitInfoRepository.save(recruitInfo);
        return 1;
    }

    @Override
    public List<RecruitInfo> getMyCreatedRecruit(String username) {
        return recruitInfoRepository.findRecruitInfoByUsernameAndIsDestroy(username);
    }

    @Override
    public RecruitInfo getRecruitInfoByTid(String tid) {
        List<RecruitInfo> list = recruitInfoRepository.findRecruitInfoByTid(tid);
        for (RecruitInfo recruitInfo : list) {
//            实际上只会有这一个，因此直接return
            return recruitInfo;
        }
        return null;
    }
}
