package com.example.Vteam.entity;

import com.sun.org.apache.xpath.internal.objects.XString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @projectName: project
 * @package: com.example.Vteam.entity
 * @className: VteamInfo
 * @author: Zhou xiang
 * @description: TODO
 * @date: 2022/5/9 11:17
 * @version: 1.0
 */
@Entity
@Table(name="vteam_info")
public class VteamInfo {
    @Id
    @Column(name = "tid")
    private String tid;

    @Column(name = "maxMates")
    private int maxMates;

    @Column(name = "currentMates")
    private int currentMats;

    @Column(name = "teamMates")
    private String teamMates;

    @Column(name = "successTime")
    private String successTime;

    @Column(name = "isSuccess")
    private int isSuccess;

    public VteamInfo(String tid, int maxMates, int currentMats, String teamMates, String successTime, int isSuccess) {
        this.tid = tid;
        this.maxMates = maxMates;
        this.currentMats = currentMats;
        this.teamMates = teamMates;
        this.successTime = successTime;
        this.isSuccess = isSuccess;
    }

    public String getTid() {
        return tid;
    }

    public int getMaxMates() {
        return maxMates;
    }

    public int getCurrentMats() {
        return currentMats;
    }

    public String getTeamMates() {
        return teamMates;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setMaxMates(int maxMates) {
        this.maxMates = maxMates;
    }

    public void setCurrentMats(int currentMats) {
        this.currentMats = currentMats;
    }

    public void setTeamMates(String teamMates) {
        this.teamMates = teamMates;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }
}
