package com.example.Vteam.service.Interface;

import java.util.List;

public interface MessageService {
    List getAllMessage(String username);
    int setSingleMessageStatus(String mid);
}
