package com.example.Vteam.controller.Impl;

import com.example.Vteam.controller.Interface.SettingsController;
import com.example.Vteam.entity.Settings;
import com.example.Vteam.service.Interface.SettingsService;
import com.example.Vteam.utils.MyJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import com.example.Vteam.utils.MyFunction;

import java.util.*;

/**
 * @projectName: project
 * @package: com.example.Vteam.controller.Impl
 * @className: SettingsControllerImpl
 * @author: Zhou xiang
 * @description: 获取页面tags和class
 * @date: 2022/5/9 13:58
 * @version: 1.0
 */
@RestController
@RequestMapping(value = "settings/api")
public class SettingsControllerImpl implements SettingsController {

    @Autowired
    SettingsService settingsService;

    @RequestMapping(value = "/getSettings")
    public MyJson getSettings(HttpServletRequest request) {
        MyJson myJson = MyFunction.isLoggedIn(request);
        if (myJson.getStatus() == 403) {
            return myJson;
        }

        //获取class和tags
        List<Settings> list = settingsService.getSettings();
        Map<String, Object> result = new HashMap();
        List<String> tags = new ArrayList();
        Set<String> s = new HashSet<>();
        for (Settings settings : list) {
            tags.add(settings.getId().getTags());
            s.add(settings.getId().getClasses());
        }
        s.add("全部板块");
        List<Map<String, String>> classes = new ArrayList();
        for (String s1 : s) {
            Map<String, String> option = new HashMap<>();
            option.put("value", s1);
            option.put("label", s1);
            classes.add(option);
        }
        result.put("tags", tags);
        result.put("options", classes);
//        System.out.println(result);
        if (!result.isEmpty()) {
            myJson.setStatus(200);
            myJson.setMessage("获取全部标签，板块成功");
            myJson.setResult(result);
        } else {
            myJson.setStatus(500);
            myJson.setMessage("已知标签，板块为空");
            myJson.setResult(result);
        }

        return myJson;
    }

}
