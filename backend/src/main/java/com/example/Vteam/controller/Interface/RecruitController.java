package com.example.Vteam.controller.Interface;

import com.example.Vteam.utils.MyJson;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 13:52
 * @Version 1.0
 */
public interface RecruitController {
    MyJson createRecruitWithImg(HttpServletRequest request,
                                @RequestParam("username") String username,
                                @RequestParam("rtitle") String rtitle,
                                @RequestParam("description") String description,
                                @RequestParam("maxMates") int maxMates,
                                @RequestParam("endTime") String endTime,
                                @RequestParam("rclass") String rclass,
                                @RequestParam("content") String content,
                                @RequestParam("rtags") String rtags,
                                @RequestParam("img") MultipartFile img
    );

    MyJson createRecruitWithoutImg(HttpServletRequest request,
                                   @RequestParam("username") String username,
                                   @RequestParam("rtitle") String rtitle,
                                   @RequestParam("description") String description,
                                   @RequestParam("maxMates") int maxMates,
                                   @RequestParam("endTime") String endTime,
                                   @RequestParam("rclass") String rclass,
                                   @RequestParam("content") String content,
                                   @RequestParam("rtags") String rtags
    );

    MyJson joinRecruit(HttpServletRequest request,
                       @RequestParam("rid") String rid,
                       @RequestParam("username") String username);

    MyJson getMyCreatedRecruit(HttpServletRequest request,
                               @RequestParam("username") String username);

    MyJson getMyAttendedRecruit(HttpServletRequest request,
                                @RequestParam("username") String username);

    MyJson uploadImg(HttpServletRequest request,
                     @RequestParam("img") MultipartFile img);

    MyJson getRecruitInfo(HttpServletRequest request,
                          @RequestParam("username") String username,
                          @RequestParam("rid") String rid);

    //获取首页招募信息列表
    MyJson getScreenRecruitInfo(HttpServletRequest request);

    //    查看招募信息
    MyJson watchRecruit(HttpServletRequest request,
                        @RequestParam("username") String username,
                        @RequestParam("rid") String rid);
}
