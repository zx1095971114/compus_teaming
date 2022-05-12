package com.example.Vteam.HistoryServiceTest;

import com.example.Vteam.entity.History;
import com.example.Vteam.service.Interface.HistoryService;
import com.example.Vteam.service.Interface.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.Vteam.utils.MyFunction.colorfulPrint;

/**
 * @Author Lang wenchong
 * @Date 2022/5/12 12:37
 * @Version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ContextConfiguration(locations = {"classpath:application.yml"})
public class HistoryServiceTest {

    @Autowired
    HistoryService historyService;
    public String username = "langwenchong";

    @Test
    @Transactional
    public void getAllHistoryTest() {
        List<History> list = historyService.getAllHistory(username);
        for (History history : list) {
            colorfulPrint(history.toString());

        }
    }
}
