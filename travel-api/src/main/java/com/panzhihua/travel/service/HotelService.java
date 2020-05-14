package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.HotelRepository;
import com.panzhihua.travel.dto.req.QueryCondition;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Hotel;
import com.panzhihua.travel.utils.IDUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class HotelService {
    private final MongoTemplate mongoTemplate;

    private final HotelRepository hotelRepository;

    public RetDto save(Hotel hotel) {

        hotel.setStartTime(hotel.getStartTime().substring(11,16));
        hotel.setEndTime(hotel.getEndTime().substring(11,16));
        hotel.setBuildTime(hotel.getBuildTime().substring(0,4));
        return RetDto.success(hotelRepository.save(hotel),0,0);
    }

    public void delete(String id) {
        hotelRepository.deleteById(id);
    }

    public RetDto findById(String id){
        Hotel hotel = new Hotel();
        hotel.setId(id);
        return RetDto.success(hotelRepository.findOne(Example.of(hotel)),0,0);
    }

    public RetDto find(QueryCondition queryCondition) {
        Query query = new Query();

        if (!StringUtils.isEmpty(queryCondition.getSearch())) {
            Pattern pattern = Pattern.compile("^.*"
                    + queryCondition.getSearch() + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("title").regex(pattern));
        }

        if (!StringUtils.isEmpty(queryCondition.getSortField())) {
            query.with(Sort.by("asc".equals(queryCondition.getSortKey()) ?
                    Sort.Order.asc(queryCondition.getSortField()) :
                    Sort.Order.desc(queryCondition.getSortField())));
        } else {
            query.with(Sort.by(Sort.Order.desc("timestamp")));
        }

        if (queryCondition.getTimestamp() == null ||
                queryCondition.getTimestamp() <= 0) {
            queryCondition.setTimestamp(0l);
        }

        query.addCriteria(Criteria.where("timestamp").gt(queryCondition.getTimestamp()));
        int count = (int) mongoTemplate.count(query, Hotel.class);

        query.skip((queryCondition.startPage()))
                .limit(queryCondition.getPageSize());

        return RetDto.builder()
                .pageSize(queryCondition.getPageSize())
                .body(mongoTemplate.find(query, Hotel.class))
                .total(count).build();
    }
}
