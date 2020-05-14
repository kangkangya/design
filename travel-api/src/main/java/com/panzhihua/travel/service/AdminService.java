package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.AdminRepository;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Admin;
import org.springframework.data.domain.Example;

import java.util.Optional;

public class AdminService {

    private AdminRepository adminRepository;

    public RetDto login(String username, String password){
        Optional<Admin> one = adminRepository.findOne(
                Example.of(Admin.builder()
                        .username(username).password(password).build()));

        if (one.isPresent()){
            return RetDto.success(one.get(),0,0);
        }

        return RetDto.userNotExistsError();
    }
}
