package com.example.Vteam.dao.Interface;

import com.example.Vteam.entity.Message;

import java.util.List;

public interface MessageDao {
    List searchMessage(String username);
    int setSingleMessageStatus(String mid);
    int setAllMessageStatus(String username);

    void insertMessage(Message message);
}
