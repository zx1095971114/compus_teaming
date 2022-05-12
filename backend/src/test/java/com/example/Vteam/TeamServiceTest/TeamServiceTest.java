package com.example.Vteam.TeamServiceTest;

import com.example.Vteam.entity.UserInfo;
import com.example.Vteam.entity.VteamInfo;
import com.example.Vteam.service.Interface.RecruitService;
import com.example.Vteam.service.Interface.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static com.example.Vteam.utils.MyFunction.colorfulPrint;

/**
 * @Author Lang wenchong
 * @Date 2022/5/12 12:17
 * @Version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ContextConfiguration(locations = {"classpath:application.yml"})
public class TeamServiceTest {
    @Autowired
    TeamService teamService;
    public String username = "langwenchong";

    @Test
    @Transactional
    public void getMyAttendedTeamTest() {
        List<VteamInfo> list = teamService.getMyAttendedTeam(username);
        for (VteamInfo vteamInfo : list) {
            colorfulPrint(vteamInfo.toString());
        }
    }

    @Test
    @Transactional
    public void getTeamInfoTest() {
        List<UserInfo> list = teamService.getTeamInfo("cee5a3407f9943468adcb3d1db5491f8");
        for (UserInfo userInfo : list) {
            colorfulPrint(userInfo.toString());

        }
    }

    @Test
    @Transactional
    public void getUserTeamInfoTest() {
        List<Map<String, Object>> list = teamService.getUserTeamInfo(username);
        for (Map<String, Object> stringObjectMap : list) {
            for (String s : stringObjectMap.keySet()) {
                colorfulPrint(stringObjectMap.get(s).toString());

            }

        }
    }


}
