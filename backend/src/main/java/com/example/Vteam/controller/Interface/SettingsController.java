package com.example.Vteam.controller.Interface;

import com.example.Vteam.utils.MyJson;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName: project
 * @package: com.example.Vteam.controller.Interface
 * @className: SettingsController
 * @author: Zhou xiang
 * @description: 获取页面tags和class
 * @date: 2022/5/9 13:53
 * @version: 1.0
 */
public interface SettingsController {
    MyJson getSettings(HttpServletRequest request);
}
