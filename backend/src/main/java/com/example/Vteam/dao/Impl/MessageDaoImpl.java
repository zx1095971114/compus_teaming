package com.example.Vteam.dao.Impl;

import com.example.Vteam.dao.Interface.MessageDao;
import com.example.Vteam.entity.Message;
import com.example.Vteam.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageDaoImpl implements MessageDao {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public List searchMessage(String username) {
        return messageRepository.findAllMessageByUsername(username);
    }

    @Override
    public int setSingleMessageStatus(String mid) {
        Message message = messageRepository.getById(mid);
        message.setStatus(1);
        messageRepository.save(message);
        return 1;
    }
}
