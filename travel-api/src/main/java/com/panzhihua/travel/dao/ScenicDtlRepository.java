package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.ScenicDtl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenicDtlRepository extends MongoRepository<ScenicDtl, String> {
}
