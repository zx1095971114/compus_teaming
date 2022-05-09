package com.example.Vteam.service.Impl;

import com.example.Vteam.dao.Interface.SettingsDao;
import com.example.Vteam.entity.Settings;
import com.example.Vteam.service.Interface.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @projectName: project
 * @package: com.example.Vteam.service.Impl
 * @className: SettingsServiceImpl
 * @author: Zhou xiang
 * @description: 获取所有的class和tag存在Map中
 * @date: 2022/5/9 14:35
 * @version: 1.0
 */
@Service
public class SettingsServiceImpl implements SettingsService {
    @Autowired
    SettingsDao settingsDao;

    @Override
    public List<Settings> getSettings(){
        return settingsDao.getAllSettings();
    }
}
