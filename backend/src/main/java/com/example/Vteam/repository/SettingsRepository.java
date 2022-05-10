package com.example.Vteam.repository;

import com.example.Vteam.embed.SettingsKey;
import com.example.Vteam.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 11:19
 * @Version 1.0
 */
public interface SettingsRepository extends JpaRepository<Settings, SettingsKey> {
    @Query("select s from Settings s")
    List<Settings> findAllSettings();
}
