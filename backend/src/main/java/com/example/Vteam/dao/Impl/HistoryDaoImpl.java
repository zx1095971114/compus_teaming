package com.example.Vteam.dao.Impl;

import com.example.Vteam.dao.Interface.HistoryDao;
import com.example.Vteam.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryDaoImpl implements HistoryDao {

    @Autowired
    HistoryRepository historyRepository;

    @Override
    public List searchHistory(String username) {
        return historyRepository.findAllHistoryByUsername(username);
    }
}
