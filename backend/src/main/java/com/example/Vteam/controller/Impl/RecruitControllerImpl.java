package com.example.Vteam.controller.Impl;

import com.example.Vteam.controller.Interface.RecruitController;
import com.example.Vteam.service.Interface.RecruitService;
import com.example.Vteam.utils.MyJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

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
                              @RequestParam("username") String username) {
        MyJson myJson = isLoggedIn(request);
        if (myJson.getStatus() == 403) return myJson;
        return myJson;
    }

    @Override
    public MyJson getRecruitInfo(HttpServletRequest request, String username, String rid) {
        MyJson myjson = isLoggedIn(request);
        if(myjson.getStatus() == 403) return myjson;
        else{
            Map<String,Object> mymap = recruitService.getRecruitInfo(username,rid);
            myjson.setResult(mymap);
            return myjson;
        }
    }
}
