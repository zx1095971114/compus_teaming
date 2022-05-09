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
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.*;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 14:36
 * @Version 1.0
 */
@Component
public class RecruitDaoImpl implements RecruitDao {

    @Autowired
    RecruitInfoRepository recruitInfoRepository;

    @Autowired
    VteamInfoRepository vteamInfoRepository;

    @Autowired
    UserInfoRepository userInfoRepository;


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

    public Map getRecruitInfo(String username, String rid) {
        Map<String, Object> mymap = new HashMap();
        RecruitInfo recruitinfo = recruitInfoRepository.getById(rid);
        mymap.put("rtitle", recruitinfo.getRtitle());
        mymap.put("startTime", recruitinfo.getStartTime());
        mymap.put("endTime", recruitinfo.getEndTime());
        mymap.put("content", recruitinfo.getContent());
        mymap.put("rclass", recruitinfo.getRclass());
        mymap.put("creator", recruitinfo.getCreator());
        String mytid = recruitinfo.getTid();
        VteamInfo vteaminfo = vteamInfoRepository.getById(mytid);
        String[] myteammates = vteaminfo.getTeamMates().split("-");
        int flag = 0;
        boolean res = Arrays.asList(myteammates).contains(username);
        if (res)
            flag = 1;
        mymap.put("flag", flag);

        List myavator = new ArrayList<String>();
        for (String myteammate : myteammates) {
            UserInfo userinfo = userInfoRepository.getById(myteammate);
            myavator.add(userinfo.getAvatarPath());
        }



        mymap.put("avatorPath", myavator);


        return mymap;
    }
}
