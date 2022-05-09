package com.example.Vteam.dao.Interface;

import java.util.List;

public interface MessageDao {
    List searchMessage(String username);
    int setSingleMessageStatus(String mid);
}
