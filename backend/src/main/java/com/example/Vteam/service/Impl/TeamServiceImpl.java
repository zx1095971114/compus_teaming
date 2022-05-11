package com.example.Vteam.service.Impl;

import com.example.Vteam.dao.Interface.RecruitDao;
import com.example.Vteam.dao.Interface.TeamDao;
import com.example.Vteam.dao.Interface.UserDao;
import com.example.Vteam.entity.RecruitInfo;
import com.example.Vteam.entity.UserInfo;
import com.example.Vteam.entity.VteamInfo;
import com.example.Vteam.service.Interface.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 17:37
 * @Version 1.0
 */

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    UserDao userDao;

    @Autowired
    RecruitDao recruitDao;

    @Autowired
    TeamDao teamDao;

    @Override
    public List<VteamInfo> getMyAttendedTeam(String username) {
        return teamDao.getMyAttendedTeam(username);
    }

    @Override
    public List<UserInfo> getTeamInfo(String tid) {
        return teamDao.getTeamInfo(tid);
    }

    @Override
    public List<Map<String, Object>> getUserTeamInfo(String username) {
        List<Map<String, Object>> userTeamInfo = new ArrayList<>();
        List<VteamInfo> userVteamInfoList = teamDao.getUserVteamInfo(username);

        for (VteamInfo vteamInfo : userVteamInfoList) {
            RecruitInfo recruitInfo = recruitDao.getRecruitInfoByTid(vteamInfo.getTid());
            Map<String, Object> obj = new HashMap<>();
            obj.put("maxMates", vteamInfo.getMaxMates());
            obj.put("successTime", vteamInfo.getSuccessTime());
            obj.put("tid", vteamInfo.getTid());
            obj.put("rtitle", recruitInfo.getRtitle());
            obj.put("rclass", recruitInfo.getRclass());
            String teammates = vteamInfo.getTeamMates();
            String[] members = teammates.split("-");
            List<String> avatars = new ArrayList<>();
            for (String member : members) {
                UserInfo userInfo = userDao.getUserInfo(member);
                avatars.add(userInfo.getAvatarPath());
            }
            obj.put("avatars", avatars);
            userTeamInfo.add(obj);
        }
//        //将成员队伍需要的信息组装
//        Iterator<VteamInfo> iterator = userVteamInfo.iterator();
//        while (iterator.hasNext()){
//            VteamInfo vteamInfo = iterator.next();
//            Map<String,Object> usefulVteamInfo = new HashMap<>();
//
//            usefulVteamInfo.put("maxMates",vteamInfo.getMaxMates());
//            usefulVteamInfo.put("successTime",vteamInfo.getSuccessTime());
//            usefulVteamInfo.put("tid",vteamInfo.getTid());
//
//            usefulVteamInfo.put("avatarPath",teamDao.getUserInfoByUsername(username).getAvatarPath());
//            usefulVteamInfo.put("status",teamDao.getVteamUserByUsername(username).getStatus());
//
//            List<RecruitInfo> l = teamDao.getRecruitInfoByTid(username);
//            for(RecruitInfo r : l){
//                usefulVteamInfo.put("rid",r.getRid());
//                usefulVteamInfo.put("rtitle",r.getRtitle());
//            }
//
//            userTeamInfo.add(usefulVteamInfo);
//        }

        //返回对应的组装结果
        return userTeamInfo;

    }
}
