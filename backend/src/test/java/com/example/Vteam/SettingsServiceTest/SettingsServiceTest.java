package com.example.Vteam.SettingsServiceTest;

import com.example.Vteam.entity.Settings;
import com.example.Vteam.service.Interface.SettingsService;
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
 * @Date 2022/5/12 12:26
 * @Version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ContextConfiguration(locations = {"classpath:application.yml"})
public class SettingsServiceTest {
    @Autowired
    SettingsService settingsService;
    public String username = "langwenchong";

    @Test
    @Transactional
    public void getSettingsTest() {
        List<Settings> list = settingsService.getSettings();
        for (Settings settings : list) {
            colorfulPrint(settings.getId().toString());
        }
    }
}
