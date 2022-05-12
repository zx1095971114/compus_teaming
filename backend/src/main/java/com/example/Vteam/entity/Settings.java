package com.example.Vteam.entity;

import com.example.Vteam.embed.SettingsKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 10:57
 * @Version 1.0
 */

@Entity
@Table(name = "settings")
public class Settings {
    @EmbeddedId
    SettingsKey id;

    public Settings() {
    }

    public Settings(SettingsKey id) {
        this.id = id;
    }

    public SettingsKey getId() {
        return id;
    }

    public void setId(SettingsKey id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "id=" + id +
                '}';
    }
}
