package com.example.Vteam.repository;

import com.example.Vteam.embed.SettingsKey;
import com.example.Vteam.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 11:19
 * @Version 1.0
 */
public interface SettingsRepository extends JpaRepository<Settings, SettingsKey> {

}
