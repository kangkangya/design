package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.Region;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends MongoRepository<Region, String> {
}
