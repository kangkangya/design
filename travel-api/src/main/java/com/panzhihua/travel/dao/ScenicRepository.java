package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.Scenic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenicRepository extends MongoRepository<Scenic, String> {
}
