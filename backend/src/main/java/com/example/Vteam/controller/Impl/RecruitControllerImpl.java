package com.example.Vteam.controller.Impl;

import com.example.Vteam.controller.Interface.RecruitController;
import com.example.Vteam.dao.Interface.RecruitDao;
import com.example.Vteam.dao.Interface.TeamDao;
import com.example.Vteam.entity.History;
import com.example.Vteam.entity.RecruitInfo;
import com.example.Vteam.entity.VteamInfo;
import com.example.Vteam.service.Interface.RecruitService;
import com.example.Vteam.service.Interface.TeamService;
import com.example.Vteam.utils.MyFunction;
import com.example.Vteam.utils.MyJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import static com.example.Vteam.utils.MyFunction.isLoggedIn;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 13:59
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/recruit/api")
public class RecruitControllerImpl implements RecruitController {
    @Autowired
    RecruitService recruitService;

    @Autowired
    TeamService teamService;

    @Override
    @RequestMapping("/creat")
    public MyJson createRecruit(HttpServletRequest request,
                                @RequestParam("username") String username,
                                @RequestParam("rtitle") String rtitle,
                                @RequestParam("description") String description,
                                @RequestParam("maxMates") int maxMates,
                                @RequestParam("endTime") String endTime,
                                @RequestParam("rclass") String rclass,
                                @RequestParam("content") String content,
                                @RequestParam("img") MultipartFile img,
                                @RequestBody String[] rtags) {
        MyJson myJson = isLoggedIn(request);
        if (myJson.getStatus() == 403) return myJson;
        int suc = recruitService.createRecruit(username, rtitle, description, maxMates, endTime, rclass, content, img, rtags);
        if (suc == 1) {
            myJson.setStatus(200);
            myJson.setMessage("招募任务发布成功！");
        } else {
            myJson.setStatus(500);
            myJson.setMessage("招募任务发布失败");
        }
        return myJson;
    }

    @Override
    public MyJson joinRecruit(HttpServletRequest request,
                              @RequestParam("rid") String rid,
                              @RequestParam("username") String username) {
        MyJson myJson = isLoggedIn(request);
        if (myJson.getStatus() == 403) return myJson;
        int suc = recruitService.joinRecruit(rid, username);
        if (suc == 1) {
            myJson.setStatus(200);
            myJson.setMessage("加入招募成功！");
        } else {
            myJson.setStatus(500);
            myJson.setMessage("加入招募失败！");
        }
        return myJson;
    }

    @Override
    @RequestMapping(value = "/getMyCreatedRecruit")
    public MyJson getMyCreatedRecruit(HttpServletRequest request,
                                      @RequestParam("username") String username) {
        MyJson myJson = isLoggedIn(request);
        if (myJson.getStatus() == 403) return myJson;
        List<RecruitInfo> result = recruitService.getMyCreatedRecruit(username);
        myJson.setResult(200);
        myJson.setResult(result);
        return myJson;
    }

    @Override
    @RequestMapping(value = "/getMyAttendedRecruit")
    public MyJson getMyAttendedRecruit(HttpServletRequest request, String username) {
        MyJson myJson = isLoggedIn(request);
        if (myJson.getStatus() == 403) return myJson;
        List<VteamInfo> teams = teamService.getMyAttendedTeam(username);
        List<RecruitInfo> result = new ArrayList<>();
        for (VteamInfo team : teams) {
            RecruitInfo recruitInfo = recruitService.getRecruitInfo(team.getTid());
            result.add(recruitInfo);
        }
        myJson.setStatus(200);
        myJson.setResult(result);
        return myJson;
    }

    @Override

    public MyJson uploadImg(HttpServletRequest request, @RequestParam("img") MultipartFile img) {
        MyJson myjson = isLoggedIn(request);
        if (myjson.getStatus() == 403) return myjson;
        String imgPath = recruitService.uploadImg(img);
        if (imgPath.equals("error")) {
            myjson.setStatus(500);
        } else {
            myjson.setStatus(200);
            Map<String, String> result = new HashMap<>();
            result.put("imgPath", imgPath);
            myjson.setResult(result);
        }
        return myjson;
    }


    public MyJson getRecruitInfo(HttpServletRequest request, String username, String rid) {
        MyJson myjson = isLoggedIn(request);
        if (myjson.getStatus() == 403) return myjson;
        else {
            Map<String, Object> mymap = recruitService.getRecruitInfo(username, rid);
            myjson.setResult(mymap);
            return myjson;
        }
    }

    public MyJson getScreenRecruitInfo(HttpServletRequest request) {
        MyJson myJson = MyFunction.isLoggedIn(request);
        if (myJson.getStatus() == 403) {
            return myJson;
        }


        List<Map<String, Object>> result = recruitService.getScreenRecruitInfo();
        if (result != null) {
            myJson.setResult(result);
            myJson.setMessage("获取需要显示的招募信息成功");
            myJson.setStatus(200);
        } else {
            myJson.setMessage("获取需要显示的招募信息失败");
            myJson.setStatus(500);
        }

        return myJson;
    }

    @Override
    @RequestMapping(value = "/watchRecruit")
    public MyJson watchRecruit(HttpServletRequest request,
                               @RequestParam("username") String username,
                               @RequestParam("rid") String rid) {
        MyJson myJson = MyFunction.isLoggedIn(request);
        if (myJson.getStatus() == 403) {
            return myJson;
        }
        int suc = recruitService.watchRecruit(username, rid);
        if (suc == 1) {
            myJson.setStatus(200);
            myJson.setMessage("热度值更新成功！");
            return myJson;
        } else {
            myJson.setStatus(500);
            myJson.setMessage("热度值更新失败！");
        }
        return myJson;
    }
}
