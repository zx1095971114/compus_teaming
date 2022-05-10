package com.example.Vteam.service.Impl;

import com.example.Vteam.dao.Interface.HistoryDao;
import com.example.Vteam.dao.Interface.RecruitDao;
import com.example.Vteam.dao.Interface.TeamDao;
import com.example.Vteam.dao.Interface.UserDao;
import com.example.Vteam.entity.*;
import com.example.Vteam.service.Interface.RecruitService;
import com.example.Vteam.utils.MyFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.*;

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

    @Autowired
    HistoryDao historyDao;
    @Value("${root}")
    private String root;

    @Override
    public int createRecruitWithImg(String username, String rtitle, String description, int maxMates, String endTime, String rclass, String content, MultipartFile img, String rtags) {
        RecruitInfo recruitInfo = recruitDao.createRecruitInfo();
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
        recruitInfo.setRtags(rtags);
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
        String hid = UUID.randomUUID().toString().replaceAll("-", "");
        History history = new History(hid, username, "创建招募", "你创建了招募：" + rtitle, MyFunction.getTime());
        int suc3 = historyDao.insertHistory(history);
        int suc1 = teamDao.insertTeamInfo(vteamInfo);
        int suc2 = recruitDao.insertRecruitInfo(recruitInfo);
        if (suc1 == 1 && suc2 == 1 && suc3 == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int createRecruitWithoutImg(String username, String rtitle, String description, int maxMates, String endTime, String rclass, String content, String rtags) {
        RecruitInfo recruitInfo = recruitDao.createRecruitInfo();
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
        recruitInfo.setRtags(rtags);
        recruitInfo.setContent(content);
        recruitInfo.setCreator(username);
        recruitInfo.setByTeacher(byTeacher);
        recruitInfo.setEndTime(endTime);
        recruitInfo.setTid(vteamInfo.getTid());
        String hid = UUID.randomUUID().toString().replaceAll("-", "");
        History history = new History(hid, username, "创建招募", "你创建了招募：" + rtitle, MyFunction.getTime());
        int suc3 = historyDao.insertHistory(history);
        int suc1 = teamDao.insertTeamInfo(vteamInfo);
        int suc2 = recruitDao.insertRecruitInfo(recruitInfo);
        if (suc1 == 1 && suc2 == 1 && suc3 == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int joinRecruit(String rid, String username) {
        RecruitInfo recruitInfo = recruitDao.getRecruitInfo(rid);
        if (recruitInfo == null) {
            return -1;
        }
        VteamInfo vteaminfo = teamDao.getVteamInfo(recruitInfo.getTid());
        if (vteaminfo == null) {
            return -1;
        }
        if (vteaminfo.getCurrentMates() + 1 > vteaminfo.getMaxMates()) {
            return -1;
        }
//        如果已经加入过了，就不允许再加入了
        if (vteaminfo.getTeamMates().contains(username)) {
            return -1;
        }
        vteaminfo.setTeamMates(vteaminfo.getTeamMates() + "-" + username);
        vteaminfo.setCurrentMates(vteaminfo.getCurrentMates() + 1);
        if (vteaminfo.getCurrentMates() == vteaminfo.getMaxMates()) {
//            队伍组好了
            vteaminfo.setSuccessTime(MyFunction.getTime());
            vteaminfo.setIsSuccess(1);
            recruitInfo.setIsDestroy(1);
        }

        int suc1 = recruitDao.updateRecruitInfo(recruitInfo);
        int suc2 = teamDao.updateVteamInfo(vteaminfo);
        String hid = UUID.randomUUID().toString().replaceAll("-", "");
        History history = new History(hid, username, "加入招募", "你加入了招募：" + recruitInfo.getRtitle(), MyFunction.getTime());
        int suc3 = historyDao.insertHistory(history);
        if (suc1 == 1 && suc2 == 1 && suc3 == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public List<RecruitInfo> getMyCreatedRecruit(String username) {
        return recruitDao.getMyCreatedRecruit(username);
    }

    @Override
    public RecruitInfo getRecruitInfo(String tid) {
        return recruitDao.getRecruitInfoByTid(tid);
    }

    public Map getRecruitInfo(String username, String rid) {
        return recruitDao.getRecruitInfo(username, rid);
    }

    public List<Map<String, Object>> getScreenRecruitInfo() {
        List<Map<String, Object>> screenRecruitInfo = new ArrayList<>();

        //获取改装List对象，使其符合显示的要求
        List<RecruitInfo> validRecruitInfo = recruitDao.getValidRecruitInfo();
        Iterator<RecruitInfo> iterator = validRecruitInfo.iterator();
        while (iterator.hasNext()) {
            RecruitInfo recruitInfo = iterator.next();
            Map<String, Object> usefulRecruitInfo = new HashMap<>();

            usefulRecruitInfo.put("rtitle", recruitInfo.getRtitle());
            usefulRecruitInfo.put("description", recruitInfo.getDescription());
            usefulRecruitInfo.put("subscribe", recruitInfo.getSubscribe());
            usefulRecruitInfo.put("rclass", recruitInfo.getRclass());
            usefulRecruitInfo.put("rtags", recruitInfo.getRtags());
            usefulRecruitInfo.put("startTime", recruitInfo.getStartTime());
            usefulRecruitInfo.put("img", recruitInfo.getImg());
            usefulRecruitInfo.put("byTeacher", recruitInfo.getByTeacher());

            //获取avatorPath，name
            String username = recruitInfo.getCreator();
            UserInfo userInfo = recruitDao.getUserInfoByUsername(username);
            String avatorPath = userInfo.getUsername();
            String name = userInfo.getName();

            usefulRecruitInfo.put("avatorPath", avatorPath);
            usefulRecruitInfo.put("name", name);

            //获取maxTeammates,currentTeammates
            String tid = recruitInfo.getTid();
            VteamInfo vteamInfo = recruitDao.getVteamInfoByTid(tid);
            int maxMates = vteamInfo.getMaxMates();
            int currentMates = vteamInfo.getCurrentMates();

            usefulRecruitInfo.put("maxMates", maxMates);
            usefulRecruitInfo.put("currentMates", currentMates);

            screenRecruitInfo.add(usefulRecruitInfo);
        }

        //返回处理好的该显示的List对象
        return screenRecruitInfo;
    }

    @Override
    public String uploadImg(MultipartFile img) {
        String imgName = UUID.randomUUID().toString().replaceAll("-", "");
        String imgPath = "/richEditorImg/" + imgName + ".png";
        File directory = new File(new File(root).getAbsolutePath() + "/richEditorImg/");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            img.transferTo(new File(new File(root).getAbsolutePath() + imgPath));
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return imgPath;
    }

    @Override
    public int watchRecruit(String username, String rid) {
        RecruitInfo recruitInfo = recruitDao.getRecruitInfo(rid);
        recruitInfo.setSubscribe((recruitInfo.getSubscribe() + 1));
        String hid = UUID.randomUUID().toString().replaceAll("-", "");
        History history = new History(hid, username, "查看招募", "你查看了招募：" + recruitInfo.getRtitle(), MyFunction.getTime());
        int suc1 = historyDao.insertHistory(history);
        int suc2 = recruitDao.updateRecruitInfo(recruitInfo);
        return suc1 & suc2;
    }
}
