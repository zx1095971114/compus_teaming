package com.example.Vteam.controller.Impl;

import com.example.Vteam.controller.Interface.MessageController;
import com.example.Vteam.service.Interface.MessageService;
import com.example.Vteam.utils.MyJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.Vteam.utils.MyFunction.isLoggedIn;

@RestController
@RequestMapping(value = "/message/api")
public class MessageControllerImpl implements MessageController {

    @Autowired
    MessageService messageService;

    @Override
    @RequestMapping(value = "/getAllMessage")
    public MyJson getAllMessage(HttpServletRequest request, String username) {
        MyJson myjson = isLoggedIn(request);
        if(myjson.getStatus() == 403)
            return myjson;
        else{
            List l = messageService.getAllMessage(username);
            myjson.setResult(l);
            return myjson;
        }
    }

    @Override
    @RequestMapping(value = "/setSingleMessageStatus")
    public MyJson setSingleMessageStatus(HttpServletRequest request, String mid) {
        MyJson myjson = isLoggedIn(request);
        if(myjson.getStatus() == 403)
            return myjson;
        else{
            int suc = messageService.setSingleMessageStatus(mid);
            if(suc == 1){
                myjson.setStatus(200);
                myjson.setMessage("修改成功！");
            }else{
                myjson.setMessage("修改失败！");
            }
            return myjson;
        }
    }
}
