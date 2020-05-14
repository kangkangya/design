package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.TestA;
import com.panzhihua.travel.utils.IDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAControllerTest {
    @Autowired
    private TestController testController;

    @Test
    public void testSave() {
        mockData();
    }

    private void mockData() {
        TestA test = new TestA();
        test.setId(IDUtil.id());
        test.setId(IDUtil.id());
        test.setCode("9999");
        test.setName("成都");
        testController.save(test);

        TestA test1 = new TestA();
        test1.setId(IDUtil.id());
        test1.setId(IDUtil.id());
        test1.setCode("8888");
        test1.setName("遂宁");
        testController.save(test1);
    }
}
