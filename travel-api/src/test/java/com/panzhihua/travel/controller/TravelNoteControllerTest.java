package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.TravelNote;
import com.panzhihua.travel.utils.IDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelNoteControllerTest {
    @Autowired
    private TravelNoteController travelNoteController;

    @Test
    public void testSave() {
        IntStream.range(0, 20).forEach(i -> mockData(i));
    }

    private void mockData(int i) {
        TravelNote travelNote = new TravelNote();
        travelNote.setId(IDUtil.id());
        travelNote.setTitle("travel note " + i);
        travelNote.setSummary("travel note summary " + i);
        travelNote.setRegionCode(buildRegionCode());
        travelNote.setPicUrl("spoit-" + i + ".jpeg");
        travelNote.setBrowseNum(i);
        travelNote.setTimestamp(System.currentTimeMillis());
        travelNoteController.save(travelNote);
    }

    private String buildRegionCode() {
        String[] codes = {"00000", "10000", "20000", "30000", "40000"};
        return codes[new Random().nextInt(codes.length)];
    }
}
