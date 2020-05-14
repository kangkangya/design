package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.RegionRepository;
import com.panzhihua.travel.entity.Region;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    public Region save(Region region) {
        return regionRepository.save(region);
    }

    public void delete(String id) {
        regionRepository.deleteById(id);
    }

    public List<Region> list() {
        return regionRepository.findAll();
    }
}
