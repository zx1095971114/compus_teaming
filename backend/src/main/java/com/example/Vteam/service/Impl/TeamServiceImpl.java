package com.example.Vteam.service.Impl;

import com.example.Vteam.dao.Interface.TeamDao;
import com.example.Vteam.entity.VteamInfo;
import com.example.Vteam.service.Interface.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 17:37
 * @Version 1.0
 */

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamDao teamDao;

    @Override
    public List<VteamInfo> getMyAttendedTeam(String username) {
        return teamDao.getMyAttendedTeam(username);
    }
}
