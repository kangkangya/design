package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.HotelLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelLogRepository extends MongoRepository<HotelLog, String> {
}
