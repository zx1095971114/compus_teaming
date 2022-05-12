package com.example.Vteam.RecruitServiceTest;

import com.example.Vteam.entity.RecruitInfo;
import com.example.Vteam.service.Interface.RecruitService;
import com.example.Vteam.utils.MyFunction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Map;

import static com.example.Vteam.utils.MyFunction.colorfulPrint;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Author Lang wenchong
 * @Date 2022/5/12 11:53
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ContextConfiguration(locations = {"classpath:application.yml"})
public class RecruitServiceTest {
    @Autowired
    RecruitService recruitService;
    public String username = "langwenchong";

    @Test
    @Transactional
    public void createRecruitWithoutImgTest() {
        int suc = recruitService.createRecruitWithoutImg(username, "123456", "66666", 5, MyFunction.getTime(), "生活板块", "<p>faff</p>", "三国杀");
        assertTrue(suc == 1);
    }

    @Test
    @Transactional
    public void joinRecruitTest() {
        int suc = recruitService.joinRecruit("b9fc5cae22b243dc8f4451b14d57530a", username);
        assertTrue(suc == -1);
    }

    @Test
    @Transactional
    public void getMyCreatedRecruit() {
        List<RecruitInfo> list = recruitService.getMyCreatedRecruit(username);
        for (RecruitInfo recruitInfo : list) {
            colorfulPrint(recruitInfo.toString());
        }
    }

    @Test
    @Transactional
    public void getRecruitInfoTest() {
        RecruitInfo recruitInfo = recruitService.getRecruitInfo("cee5a3407f9943468adcb3d1db5491f8");
        colorfulPrint(recruitInfo.toString());
    }

    @Test
    @Transactional
    public void getScreenRecruitInfo() {
        List<Map<String, Object>> list = recruitService.getScreenRecruitInfo();
        for (Map<String, Object> stringObjectMap : list) {
            for (String s : stringObjectMap.keySet()) {
                colorfulPrint(stringObjectMap.get(s).toString());
            }

        }
    }
}
