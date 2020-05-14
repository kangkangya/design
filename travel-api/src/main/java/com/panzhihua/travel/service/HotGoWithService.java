package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.HotGoWithRepository;
import com.panzhihua.travel.entity.HotGoWith;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HotGoWithService {
    private final HotGoWithRepository hotGoWithRepository;

    public HotGoWith save(HotGoWith hotGoWith) {
        return hotGoWithRepository.save(hotGoWith);
    }

    public void delete(String id) {
        hotGoWithRepository.deleteById(id);
    }

    public List<HotGoWith> findAll() {
        List<HotGoWith> goWithNum = hotGoWithRepository.findAll(Sort.by(Sort.Order.desc("goWithNum")));
        if (goWithNum.size() >= 9) {
            return goWithNum.subList(0, 9);
        }
        return goWithNum;
    }
}
