package com.example.Vteam.service.Interface;

import com.example.Vteam.entity.Settings;

import java.util.List;
import java.util.Map;

/**
 * @projectName: project
 * @package: com.example.Vteam.service.Interface
 * @className: SettingsService
 * @author: Zhou xiang
 * @description: 获取所有的class和tag存在Map中
 * @date: 2022/5/9 14:04
 * @version: 1.0
 */
public interface SettingsService {
    List<Settings> getSettings();
}
