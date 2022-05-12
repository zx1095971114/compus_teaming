package com.example.Vteam.embed;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 11:03
 * @Version 1.0
 */

@Embeddable
public class SettingsKey implements Serializable {
    private String classes;
    private String tags;

    public SettingsKey() {
    }

    public SettingsKey(String classes, String tags) {
        this.classes = classes;
        this.tags = tags;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "SettingsKey{" +
                "classes='" + classes + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
