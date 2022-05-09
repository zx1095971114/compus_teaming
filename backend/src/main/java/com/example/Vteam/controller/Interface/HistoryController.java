package com.example.Vteam.controller.Interface;

import com.example.Vteam.utils.MyJson;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public interface HistoryController {
    MyJson getAllHistory(HttpServletRequest request,
                      @RequestParam("username") String username);
}
