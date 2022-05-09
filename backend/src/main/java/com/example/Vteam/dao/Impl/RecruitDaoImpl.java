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
    public Map getRecruitInfo(String username, String rid) {
        Map<String,Object> mymap = new HashMap();
        RecruitInfo recruitinfo = recruitInfoRepository.getById(rid);
        mymap.put("rtitle",recruitinfo.getRtitle());
        mymap.put("startTime",recruitinfo.getStartTime());
        mymap.put("endTime",recruitinfo.getEndTime());
        mymap.put("content",recruitinfo.getContent());
        mymap.put("rclass",recruitinfo.getRclass());
        mymap.put("creator",recruitinfo.getCreator());
        String mytid = recruitinfo.getTid();
        VteamInfo vteaminfo = vteamInfoRepository.getById(mytid);
        String[] myteammates = vteaminfo.getTeamMates().split("-");
        int flag = 0;
        boolean res = Arrays.asList(myteammates).contains(username);
        if(res)
            flag = 1;
        mymap.put("flag",flag);

        List myavator = new ArrayList<String>();
        for (String myteammate : myteammates) {
            UserInfo userinfo = userInfoRepository.getById(myteammate);
            myavator.add(userinfo.getAvatarPath());
        }

        mymap.put("avatorPath",myavator);

        return mymap;
    }
}
