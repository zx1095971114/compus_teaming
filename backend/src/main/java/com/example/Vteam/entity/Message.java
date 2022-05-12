package com.example.Vteam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Lang wenchong
 * @Date 2022/5/9 11:25
 * @Version 1.0
 */
@Entity
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "mid")
    private String mid;

    @Column(name = "status")
    private int status;

    @Column(name = "username")
    private String username;

    @Column(name = "mtitle")
    private String mtitle;

    @Column(name = "message")
    private String message;

    @Column(name = "mtime")
    private String mtime;

    public Message() {
    }

    public Message(String mid, int status, String username, String mtitle, String message, String mtime) {
        this.mid = mid;
        this.status = status;
        this.username = username;
        this.mtitle = mtitle;
        this.message = message;
        this.mtime = mtime;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mid='" + mid + '\'' +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", mtitle='" + mtitle + '\'' +
                ", message='" + message + '\'' +
                ", mtime='" + mtime + '\'' +
                '}';
    }
}
