package com.example.Vteam.service.Interface;

import com.example.Vteam.entity.RecruitInfo;
import com.example.Vteam.entity.VteamInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 14:02
 * @Version 1.0
 */
public interface RecruitService {
    int createRecruit(String username,
                      String rtitle,
                      String description,
                      int maxMates,
                      String endTime,
                      String rclass,
                      String content,
                      MultipartFile img,
                      String[] rtags);

    int joinRecruit(String rid, String username);

    List<RecruitInfo> getMyCreatedRecruit(String username);

    RecruitInfo getRecruitInfo(String tid);
    Map getRecruitInfo(String username, String rid);
}
