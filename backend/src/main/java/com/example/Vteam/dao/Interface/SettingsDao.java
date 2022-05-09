package com.example.Vteam.dao.Interface;

import com.example.Vteam.entity.Settings;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @projectName: project
 * @package: com.example.Vteam.dao.Interface
 * @className: SettingsDao
 * @author: Zhou xiang
 * @description: 从Settings中取出所有行
 * @date: 2022/5/9 14:10
 * @version: 1.0
 */

@Component
public interface SettingsDao {
    List<Settings> getAllSettings();
}
