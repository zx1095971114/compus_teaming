package com.example.Vteam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class History {
    @Id
    @Column(name = "hid")
    private String hid;

    @Column(name = "username")
    private String username;

    @Column(name = "htitle")
    private String htitle;

    @Column(name = "history")
    private String history;

    @Column(name = "htime")
    private String htime;


    public History() {
    }

    public History(String hid, String username, String htitle, String history, String htime) {
        this.hid = hid;
        this.username = username;
        this.htitle = htitle;
        this.history = history;
        this.htime = htime;

    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHtitle() {
        return htitle;
    }

    public void setHtitle(String htitle) {
        this.htitle = htitle;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getHtime() {
        return htime;
    }

    public void setHtime(String htime) {
        this.htime = htime;
    }
}
