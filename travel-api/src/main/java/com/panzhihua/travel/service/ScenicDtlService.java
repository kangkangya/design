package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.ScenicDtlRepository;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.ScenicDtl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScenicDtlService {
    private final ScenicDtlRepository scenicDtlRepository;

    public RetDto save(ScenicDtl scenicDtl) {
        return RetDto.success(scenicDtlRepository.save(scenicDtl),0,0);
    }


    public void delete(String id) {
        scenicDtlRepository.deleteById(id);
    }

    public RetDto findById(String id) {
        ScenicDtl dtl = new ScenicDtl();
        dtl.setId(id);
        return RetDto.success(scenicDtlRepository.findOne(Example.of(dtl)).get(),0,0);
    }
}
