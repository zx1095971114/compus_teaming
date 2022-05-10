package com.example.Vteam.dao.Impl;

import com.example.Vteam.dao.Interface.SettingsDao;
import com.example.Vteam.entity.Settings;
import com.example.Vteam.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.example.Vteam.entity.Settings;
import org.springframework.stereotype.Component;

/**
 * @projectName: project
 * @package: com.example.Vteam.dao.Impl
 * @className: SettingsDaoImpl
 * @author: Zhou xiang
 * @description: 从Settings中取出所有行
 * @date: 2022/5/9 14:40
 * @version: 1.0
 */

@Component
public class SettingsDaoImpl implements SettingsDao {
    @Autowired
    SettingsRepository settingsRepository;

    public List<Settings> getAllSettings() {
        return settingsRepository.findAllSettings();
    }
}
