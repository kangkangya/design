package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.TestRepository;
import com.panzhihua.travel.entity.TestA;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public TestA save(TestA test) {
        return testRepository.save(test);
    }

    public void delete1(String id) {
        testRepository.deleteById(id);
    }

    public List<TestA> list() {
        return testRepository.findAll();
    }
}
