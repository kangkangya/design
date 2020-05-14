package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin,String> {
}
