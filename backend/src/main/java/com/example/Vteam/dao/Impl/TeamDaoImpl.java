package com.example.Vteam.dao.Impl;

import com.example.Vteam.dao.Interface.TeamDao;
import com.example.Vteam.entity.RecruitInfo;
import com.example.Vteam.entity.UserInfo;
import com.example.Vteam.entity.VteamInfo;
import com.example.Vteam.entity.VteamUser;
import com.example.Vteam.repository.RecruitInfoRepository;
import com.example.Vteam.repository.UserInfoRepository;
import com.example.Vteam.repository.VteamInfoRepository;
import com.example.Vteam.repository.UserRepository;
import com.example.Vteam.utils.MyFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 14:20
 * @Version 1.0
 */

@Component
public class TeamDaoImpl implements TeamDao {
    @Autowired
    VteamInfoRepository vteamInfoRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserRepository vteamUser;

    @Autowired
    RecruitInfoRepository recruitInfoRepository;

    @Override
    public VteamInfo createTeamInfo() {
        String tid = UUID.randomUUID().toString().replaceAll("-", "");
        return new VteamInfo(tid, 0, 0, "", MyFunction.getTime(), 0);
    }

    @Override
    public int insertTeamInfo(VteamInfo vteamInfo) {
        if (!vteamInfoRepository.existsById(vteamInfo.getTid())) {
            vteamInfoRepository.save(vteamInfo);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public VteamInfo getVteamInfo(String tid) {
        return vteamInfoRepository.getById(tid);
    }

    @Override
    public int updateVteamInfo(VteamInfo vteaminfo) {
        vteamInfoRepository.save(vteaminfo);
        return 1;
    }

    @Override
    public List<VteamInfo> getMyAttendedTeam(String username) {
        return vteamInfoRepository.findVTeamInfoByUsernameAndIsNotSuccess('-' + username);
    }

    @Override
    public List<UserInfo> getTeamInfo(String tid) {
        List<UserInfo> mylist = new ArrayList<UserInfo>();
        String[] users = vteamInfoRepository.getById(tid).getTeamMates().split("-");
        for (String user : users) {
            UserInfo userinfo = userInfoRepository.getById(user);
            mylist.add(userinfo);
        }
        return mylist;
    }

    @Override
    public UserInfo getUserInfoByUsername(String username) {
        return userInfoRepository.getById(username);
    }

    @Override
    public List<VteamInfo> getUserVteamInfo(String username) {
        return vteamInfoRepository.findVTeamInfoByUsernameAndIsSuccess(username);
    }

    @Override
    public VteamUser getVteamUserByUsername(String username) {
        return vteamUser.getById(username);
    }

    @Override
    public List<RecruitInfo> getRecruitInfoByTid(String tid) {
        return recruitInfoRepository.findRecruitInfoByTid(tid);
    }

}
