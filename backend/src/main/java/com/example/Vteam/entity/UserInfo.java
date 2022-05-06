package com.example.Vteam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Lang wenchong
 * @Date 2022/5/6 10:12
 * @Version 1.0
 */

@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "school")
    private String school;

    @Column(name = "grade")
    private String grade;
    @Column(name = "sex")
    private String sex;

    @Column(name = "avatarPath")
    private String avatarPath;

    @Column(name = "registerTime")
    private String registerTime;

    public UserInfo() {
    }

    public UserInfo(String username, String email, String name, String phoneNumber, String school, String grade, String sex, String avatarPath, String registerTime) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.school = school;
        this.grade = grade;
        this.sex = sex;
        this.avatarPath = avatarPath;
        this.registerTime = registerTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
}
