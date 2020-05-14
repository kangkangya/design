package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.HotelDtlRepository;
import com.panzhihua.travel.entity.HotelDtl;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HotelDtlService {
    private final MongoTemplate mongoTemplate;

    private final HotelDtlRepository hotelDtlRepository;

    public HotelDtl save(HotelDtl hotelDtl) {
        return hotelDtlRepository.save(hotelDtl);
    }

    public void delete(String id) {
        hotelDtlRepository.deleteById(id);
    }
}
