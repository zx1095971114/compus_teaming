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

import java.util.List;
import java.util.Map;

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
    public MyJson getSettings(HttpServletRequest request){
        MyJson myJson = MyFunction.isLoggedIn(request);
        if(myJson.getStatus() == 403) { return myJson;}

        //获取class和tags
        List<Settings> result = settingsService.getSettings();
        if(!result.isEmpty()){
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
