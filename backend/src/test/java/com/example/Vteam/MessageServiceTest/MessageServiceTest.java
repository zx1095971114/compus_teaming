package com.example.Vteam.MessageServiceTest;

import com.example.Vteam.entity.Message;
import com.example.Vteam.service.Interface.MessageService;
import com.example.Vteam.service.Interface.SettingsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.Vteam.utils.MyFunction.colorfulPrint;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Author Lang wenchong
 * @Date 2022/5/12 12:31
 * @Version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ContextConfiguration(locations = {"classpath:application.yml"})
public class MessageServiceTest {
    @Autowired
    MessageService messageService;
    public String username = "langwenchong";

    @Test
    @Transactional
    public void getAllMessageTest() {
        List<Message> list = messageService.getAllMessage(username);
        for (Message message : list) {
            colorfulPrint(message.toString());

        }
    }

    @Test
    @Transactional
    public void setSingleMessageStatusTest() {
        int suc = messageService.setSingleMessageStatus("0003e75515124a6f9127857fd669b028");
        assertTrue(suc == 1);
    }

    @Test
    @Transactional
    public void setAllMessageStatusTest() {
        int suc = messageService.setAllMessageStatus(username);
        assertTrue(suc == 1);
    }
}
