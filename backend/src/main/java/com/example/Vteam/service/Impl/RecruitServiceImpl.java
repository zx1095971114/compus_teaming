package com.example.Vteam.service.Impl;

import com.example.Vteam.dao.Interface.RecruitDao;
import com.example.Vteam.dao.Interface.TeamDao;
import com.example.Vteam.dao.Interface.UserDao;
import com.example.Vteam.entity.RecruitInfo;
import com.example.Vteam.entity.VteamInfo;
import com.example.Vteam.entity.VteamUser;
import com.example.Vteam.service.Interface.RecruitService;
import com.example.Vteam.utils.MyFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 14:03
 * @Version 1.0
 */

@Component
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    UserDao userDao;

    @Autowired
    RecruitDao recruitDao;

    @Autowired
    TeamDao teamDao;

    @Value("${root}")
    private String root;

    @Override
    public int createRecruit(String username, String rtitle, String description, int maxMates, String endTime, String rclass, String content, MultipartFile img, String[] rtags) {
        RecruitInfo recruitInfo = recruitDao.createRecruitInfo();
        String tags = "";
        for (String rtag : rtags) {
            tags += rtag;
        }
        String startTime = MyFunction.getTime();
        VteamUser user = userDao.getVteamUser(username);
        if (user == null) {
            //未找到用户
            return -1;
        }
        int byTeacher = user.getStatus().equals("学生") ? 0 : 1;
        VteamInfo vteamInfo = teamDao.createTeamInfo();
//        存储团队初始化信息
        vteamInfo.setMaxMates(maxMates);
        vteamInfo.setTeamMates(username);
//        存储招募帖子信息
        recruitInfo.setRtitle(rtitle);
        recruitInfo.setDescription(description);
        recruitInfo.setCreator(username);
        recruitInfo.setRclass(rclass);
        recruitInfo.setRtags(tags);
        recruitInfo.setContent(content);
        recruitInfo.setCreator(username);
        recruitInfo.setByTeacher(byTeacher);
        recruitInfo.setEndTime(endTime);
        recruitInfo.setTid(vteamInfo.getTid());
        if (img != null) {
//            处理图片
            File directory = new File(root + "/recruitImg/");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String imgPath = "/recruitImg/" + recruitInfo.getRid() + ".png";
            try {
                img.transferTo(new File(new File(root).getAbsolutePath() + imgPath));
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
            recruitInfo.setImg(imgPath);
        }
        int suc1 = teamDao.insertTeamInfo(vteamInfo);
        int suc2 = recruitDao.insertRecruitInfo(recruitInfo);
        if (suc1 == 1 && suc2 == 1) {
            return 1;
        } else {
            return -1;
        }
    }
}
