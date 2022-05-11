package com.example.Vteam.utils;

import com.example.Vteam.dao.Interface.MessageDao;
import com.example.Vteam.dao.Interface.RecruitDao;
import com.example.Vteam.dao.Interface.TeamDao;
import com.example.Vteam.entity.Message;
import com.example.Vteam.entity.RecruitInfo;
import com.example.Vteam.entity.VteamInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * @Author Lang wenchong
 * @Date 2022/5/11 9:23
 * @Version 1.0
 */

@Component
@RestController(value = "/utils/api")
public class refresher {
    @Autowired
    RecruitDao recruitDao;

    @Autowired
    TeamDao teamDao;

    @Autowired
    MessageDao messageDao;

    @Scheduled(cron = "0 */1 * * * ?")
    @Transactional
    @RequestMapping(value = "/refresh")
    public void RecruitListRefresh() {
//        获取当前日期
        String currentTime = MyFunction.getTime();
        List<RecruitInfo> list = recruitDao.getValidRecruitInfo();
        for (RecruitInfo recruitInfo : list) {
            if (recruitInfo.getEndTime().compareTo(currentTime) > 0 || recruitInfo.getIsDestroy() == 1) {
                continue;
            }
//            否则截止时间早于现在且还在组队中，说明组队失败了
            VteamInfo vteamInfo = teamDao.getVteamInfo(recruitInfo.getTid());
            recruitInfo.setIsDestroy(1);
            if (vteamInfo.getCurrentMates() < vteamInfo.getMaxMates()) {
                vteamInfo.setIsSuccess(0);
            }
            recruitDao.updateRecruitInfo(recruitInfo);
            String teammates = vteamInfo.getTeamMates();
            String[] members = teammates.split("-");
            teamDao.updateVteamInfo(vteamInfo);
            for (String member : members) {
                String mid = UUID.randomUUID().toString().replaceAll("-", "");
                Message message = new Message(mid, 0, member, "组队失败", "您的关于招募：" + recruitInfo.getRtitle() + "的团队未在规定的预期时间内组队成功，该招募队伍已自动解散！", currentTime);
                messageDao.insertMessage(message);
            }
        }

    }

}
