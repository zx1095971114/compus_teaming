package com.example.Vteam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 11:08
 * @Version 1.0
 */
@Entity
@Table(name = "recruit_info")
public class RecruitInfo {
    @Id
    @Column(name = "rid")
    private String rid;

    @Column(name = "rtitle")
    private String rtitle;

    @Column(name = "description")
    private String description;

    @Column(name = "subscribe")
    private int subscribe;

    @Column(name = "rclass")
    private String rclass;

    @Column(name = "rtags")
    private String rtags;

    @Column(name = "content")
    private String content;

    @Column(name = "creator")
    private String creator;

    @Column(name = "img")
    private String img;

    @Column(name = "startTime")
    private String startTime;

    @Column(name = "endTime")
    private String endTime;

    @Column(name = "tid")
    private String tid;

    @Column(name = "byTeacher")
    private int byTeacher;

    @Column(name = "isDestroy")
    private int isDestroy;

    public RecruitInfo() {
    }

    public RecruitInfo(String rid, String rtitle, String description, int subscribe, String rclass, String rtags, String content, String creator, String img, String startTime, String endTime, String tid, int byTeacher, int isDestroy) {
        this.rid = rid;
        this.rtitle = rtitle;
        this.description = description;
        this.subscribe = subscribe;
        this.rclass = rclass;
        this.rtags = rtags;
        this.content = content;
        this.creator = creator;
        this.img = img;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tid = tid;
        this.byTeacher = byTeacher;
        this.isDestroy = isDestroy;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRtitle() {
        return rtitle;
    }

    public void setRtitle(String rtitle) {
        this.rtitle = rtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public String getRclass() {
        return rclass;
    }

    public void setRclass(String rclass) {
        this.rclass = rclass;
    }

    public String getRtags() {
        return rtags;
    }

    public void setRtags(String rtags) {
        this.rtags = rtags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getByTeacher() {
        return byTeacher;
    }

    public void setByTeacher(int byTeacher) {
        this.byTeacher = byTeacher;
    }

    public int getIsDestroy() {
        return isDestroy;
    }

    public void setIsDestroy(int isDestroy) {
        this.isDestroy = isDestroy;
    }

    @Override
    public String toString() {
        return "RecruitInfo{" +
                "rid='" + rid + '\'' +
                ", rtitle='" + rtitle + '\'' +
                ", description='" + description + '\'' +
                ", subscribe=" + subscribe +
                ", rclass='" + rclass + '\'' +
                ", rtags='" + rtags + '\'' +
                ", content='" + content + '\'' +
                ", creator='" + creator + '\'' +
                ", img='" + img + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", tid='" + tid + '\'' +
                ", byTeacher=" + byTeacher +
                ", isDestroy=" + isDestroy +
                '}';
    }
}
