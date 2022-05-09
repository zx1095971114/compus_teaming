package com.example.Vteam.controller.Impl;

import com.example.Vteam.controller.Interface.HistoryController;
import com.example.Vteam.service.Interface.HistoryService;
import com.example.Vteam.utils.MyJson;
import com.example.Vteam.utils.MyFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.example.Vteam.utils.MyFunction.isLoggedIn;

@RestController
@RequestMapping(value = "/history/api")
public class HistoryControllerImpl implements HistoryController {
    @Autowired
    HistoryService historyService;


    @Override
    @RequestMapping(value = "/getAllHistory")
    public MyJson getAllHistory(HttpServletRequest request,
                                @RequestParam("username") String username){
        MyJson myjson = isLoggedIn(request);
        if(myjson.getStatus() == 403)
            return myjson;
        else{
            List l = historyService.getAllHistory(username);
            myjson.setResult(l);
            return myjson;
        }

    }



}
