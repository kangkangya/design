package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.ScenicRepository;
import com.panzhihua.travel.dto.req.QueryCondition;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Scenic;
import com.panzhihua.travel.utils.IDUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ScenicService {
    private final MongoTemplate mongoTemplate;

    private final ScenicRepository scenicRepository;

    public RetDto save(Scenic scenic) {
        return RetDto.success(scenicRepository.save(scenic),0,0);
    }

    public void delete(String id) {
        scenicRepository.deleteById(id);
    }

    public RetDto find(QueryCondition queryCondition) {
        Query query = new Query();

        if (!StringUtils.isEmpty(queryCondition.getSearch())) {
            Pattern pattern = Pattern.compile("^.*" + queryCondition.getSearch()
                    + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("title").regex(pattern));
        }

        if (!StringUtils.isEmpty(queryCondition.getSortField())) {
            query.with(Sort.by("asc".equals(queryCondition.getSortKey()) ?
                    Sort.Order.asc(queryCondition.getSortField()) :
                    Sort.Order.desc(queryCondition.getSortField())));
        } else {//默认按照时间排序，如果表中无时间则查不到数据
            query.with(Sort.by(Sort.Order.desc("timestamp")));
        }

        if (queryCondition.getTimestamp() == null ||
                queryCondition.getTimestamp() <= 0) {
            queryCondition.setTimestamp(0l);
        }

        query.addCriteria(Criteria.where("timestamp").gt(queryCondition.getTimestamp()));

        int count = (int) mongoTemplate.count(query, Scenic.class);

        query.skip((queryCondition.startPage()))
                .limit(queryCondition.getPageSize());

        return RetDto.builder()
                .pageSize(queryCondition.getPageSize())
                .body(mongoTemplate.find(query, Scenic.class))
                .total(count).build();

    }
}
