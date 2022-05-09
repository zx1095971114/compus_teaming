package com.example.Vteam.dao.Interface;

import com.example.Vteam.entity.History;

import java.util.List;

public interface HistoryDao {
    List searchHistory(String username);

    int insertHistory(History history);
}
