package com.example.Vteam.dao.Impl;

import com.example.Vteam.dao.Interface.RecruitDao;
import com.example.Vteam.entity.RecruitInfo;
import com.example.Vteam.entity.UserInfo;
import com.example.Vteam.entity.VteamInfo;
import com.example.Vteam.repository.RecruitInfoRepository;
import com.example.Vteam.repository.UserInfoRepository;
import com.example.Vteam.repository.VteamInfoRepository;
import com.example.Vteam.utils.MyFunction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
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

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    VteamInfoRepository vteamInfoRepository;

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
    public List<RecruitInfo> getValidRecruitInfo(){
        //获取所有recruitInfo
        List<RecruitInfo> allRecruitInfo = recruitInfoRepository.findAllOrderByStartTimeDesc();
        List<RecruitInfo> validRecruitIfo = new ArrayList<>();

        //去除isDestroyed为1的项
        Iterator<RecruitInfo> iterator = allRecruitInfo.iterator();
        while (iterator.hasNext()) {
            RecruitInfo recruitInfo = iterator.next();
            if(recruitInfo.getIsDestroy() == 0){
                validRecruitIfo.add(recruitInfo);
            }
        }

        //返回有效recruitInfo
        return validRecruitIfo;
    }

    @Override
    public UserInfo getUserInfoByUsername(String username){
        return userInfoRepository.getById(username);
    }

    @Override
    public VteamInfo getVteamInfoByTid(String tid){
        return vteamInfoRepository.getById(tid);
    }
}
