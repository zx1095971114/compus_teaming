package com.example.Vteam.service.Impl;

import com.example.Vteam.dao.Interface.MessageDao;
import com.example.Vteam.service.Interface.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDao messageDao;

    @Override
    public List getAllMessage(String username) {
        List l = messageDao.searchMessage(username);
        return l;
    }

    @Override
    public int setSingleMessageStatus(String mid) {
        int suc = messageDao.setSingleMessageStatus(mid);
        return suc;
    }

    @Override
    public int setAllMessageStatus(String username) {
        int suc = messageDao.setAllMessageStatus(username);
        return suc;

    }
}
