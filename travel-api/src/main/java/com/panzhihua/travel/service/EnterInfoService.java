package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.EnterInfoRepository;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.EnterInfo;
import com.panzhihua.travel.utils.IDUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EnterInfoService {
    private final EnterInfoRepository enterInfoRepository;

    public RetDto save(EnterInfo enterInfo){
        enterInfo.setId(IDUtil.id());
        enterInfo.setTimeStamp(System.currentTimeMillis());

        return RetDto.success(enterInfoRepository.save(enterInfo),0,0);
    }


    public RetDto findByUserId(String userId,String goWithId){
        EnterInfo enterInfo = new EnterInfo();
        enterInfo.setUserId(userId);
        enterInfo.setGoWithId(goWithId);
        Optional one = enterInfoRepository.findOne(Example.of(enterInfo));
        if (one.isPresent()){
            return RetDto.success(one.get(),0,0);
        }else {
            return RetDto.fail("未报名");
        }
    }

    public void delete(String id){
        enterInfoRepository.deleteById(id);
    }
}
