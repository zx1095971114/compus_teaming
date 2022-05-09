package com.example.Vteam.service.Impl;

import com.example.Vteam.dao.Interface.HistoryDao;
import com.example.Vteam.service.Interface.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryDao historyDao;

    @Override
    public List getAllHistory(String username){
        List l = historyDao.searchHistory(username);
        return l;
    }


}
