package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.TravelNoteRepository;
import com.panzhihua.travel.dto.req.QueryCondition;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.TravelNote;
import com.panzhihua.travel.utils.IDUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class TravelNoteService {
    private final MongoTemplate mongoTemplate;

    private final TravelNoteRepository travelNoteRepository;

    public RetDto save(TravelNote travelNote) {
        travelNote.setId(IDUtil.id());
        travelNote.setTimestamp(System.currentTimeMillis());
        return RetDto.success(travelNoteRepository.save(travelNote), 0, 0);
    }

    public void delete(String id) {
        travelNoteRepository.deleteById(id);
    }

    public RetDto find(QueryCondition queryCondition) {
        Query query = new Query();

        if (!StringUtils.isEmpty(queryCondition.getSearch())) {
            Pattern pattern = Pattern.compile("^.*" + queryCondition.getSearch() + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("title").regex(pattern));
        }

        if (!StringUtils.isEmpty(queryCondition.getRefId())) {
            query.addCriteria(Criteria.where("refId").in(queryCondition.getRefId()));
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

        int count = (int) mongoTemplate.count(query, TravelNote.class);

        query.skip((queryCondition.startPage()))
                .limit(queryCondition.getPageSize());
        List<TravelNote> list = mongoTemplate.find(query,TravelNote.class);

        return RetDto.builder()
                .pageSize(queryCondition.getPageSize())
                .body(mongoTemplate.find(query, TravelNote.class))
                .total(count).build();
    }

    public RetDto findByUserId(String refId){
        Query query = new Query();
        query.addCriteria(Criteria.where("refId").is(refId));
        query.with(Sort.by(Sort.Order.desc("timestamp")));
        return RetDto.success(mongoTemplate.find(query, TravelNote.class),0,0);

    }
}
