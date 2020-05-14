package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.GoWithRepository;
import com.panzhihua.travel.dao.UserRepository;
import com.panzhihua.travel.dto.req.GoWithQueryCondition;
import com.panzhihua.travel.dto.ret.GoWithDto;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Follow;
import com.panzhihua.travel.entity.GoWith;
import com.panzhihua.travel.entity.User;
import com.panzhihua.travel.utils.DateUtil;
import com.panzhihua.travel.utils.IDUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class GoWithService {
    private final MongoTemplate mongoTemplate;

    private final GoWithRepository goWithRepository;

    private final UserRepository userRepository;

    public RetDto hotGoWithList(){
        //返回的字段
        ProjectionOperation projectionOperation  = Aggregation.project("destination","count");

        Sort sort = Sort.by(Sort.Order.desc("count"));
        SortOperation sortOperation = Aggregation.sort(sort);
        GroupOperation groupOperation = Aggregation.group("destination").count().as("count");

        Aggregation aggregation = Aggregation.newAggregation(projectionOperation,groupOperation,sortOperation);

        AggregationResults aggregationResults = mongoTemplate.aggregate(aggregation,"goWith",GoWithDto.class);
        List<GoWith> results = aggregationResults.getMappedResults();
        return RetDto.success(results,0,0);
    }

    public RetDto findById(String id){
        GoWith goWith = new GoWith();
        goWith.setId(id);

        Optional<GoWith> optional = goWithRepository.findById(id);

        if (optional.isPresent()){
            GoWithDto goWithDto = new GoWithDto();
            BeanUtils.copyProperties(optional.get(),goWithDto);
            Optional<User> one = userRepository.findById(optional.get().getRefId());
            goWithDto.setUser(one.isPresent() ? one.get() : null);

            return RetDto.success(goWithDto,0,0);
        }else {
            return RetDto.internalError();
        }
    }

    public RetDto save(GoWith goWith) throws Exception {
        goWith.setId(IDUtil.id());
        goWith.setTimestamp(System.currentTimeMillis());
        String beginDate = goWith.getBeginDate().substring(0,10);
        String endDate = goWith.getEndDate().substring(0,10);

        goWith.setBeginDate(beginDate);
        goWith.setEndDate(endDate);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date begin = format.parse(beginDate);

        goWith.setTravelTime(begin.getTime());

        return RetDto.success(goWithRepository.save(goWith),0,0);
    }

    public void delete(String id) {
        goWithRepository.deleteById(id);
    }

    public RetDto list(GoWithQueryCondition queryCondition) {
        Query query = new Query();

        if (!StringUtils.isEmpty(queryCondition.getHotGoWithId())) {
            query.addCriteria(Criteria.where("hotGoWithId").in((queryCondition.getHotGoWithId())));
        }

        if (!StringUtils.isEmpty(queryCondition.getTravelTime())) {
            Long[] longs = calcTravelTime(queryCondition.getTravelTime());
            query.addCriteria(Criteria.where("travelTime").gte(longs[0]).lte(longs[1]));
        }

        if (!StringUtils.isEmpty(queryCondition.getDestination())) {
            Pattern pattern = Pattern.compile("^.*" + queryCondition.getDestination() + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("target").regex(pattern));
        }

        if (!StringUtils.isEmpty(queryCondition.getSearch())) {
            /**
             * 1 热门结伴
             * 2 最新发表
             * 3 立即出发
             */
            if ("1".equals(queryCondition.getSearch())) {
                query.with(Sort.by(Sort.Order.desc("attentionNum")));
            } else if ("2".equals(queryCondition.getSearch())) {
                query.with(Sort.by(Sort.Order.desc("timestamp")));
            } else {
                query.addCriteria(Criteria.where("travelTime")
                        .gte(DateUtil.getZeroToday())
                        .lte(DateUtil.getZeroTime(DateUtil.addDays(3))));
            }
        } else {
            query.with(Sort.by(Sort.Order.desc("timestamp")));
        }

        int count = (int) mongoTemplate.count(query, GoWith.class);

        query.skip((queryCondition.startPage()))
                .limit(queryCondition.getPageSize());

        List<GoWithDto> list = new ArrayList<>();
        List<GoWith> goWiths = mongoTemplate.find(query, GoWith.class);

        if (!CollectionUtils.isEmpty(goWiths)) {
            goWiths.forEach(goWith -> {
                GoWithDto goWithDto = new GoWithDto();
                BeanUtils.copyProperties(goWith, goWithDto);
                goWithDto.setTravelDay(goWithDto.calTravelDay());
                Optional<User> one = userRepository.findById(goWith.getRefId());
                goWithDto.setUser(one.isPresent() ? one.get() : null);
                list.add(goWithDto);
            });
        }

        return RetDto.builder()
                .pageSize(queryCondition.getPageSize())
                .body(list)
                .total(count).build();
    }

    public RetDto update(GoWith goWith){
        Optional one = goWithRepository.findById(goWith.getId());
        if (one.isPresent()){
            GoWith result = (GoWith) one.get();
            result.setAttentionNum(goWith.getAttentionNum());
            result.setApplicantNum(goWith.getApplicantNum());
            return RetDto.success(goWithRepository.save(goWith),0,0);
        }else {
            return RetDto.fail("未知错误");
        }
    }

    public RetDto findByUserId(String refId){
        Query query = new Query();
        query.addCriteria(Criteria.where("refId").is(refId));
        query.with(Sort.by(Sort.Order.desc("timestamp")));
        return RetDto.success(mongoTemplate.find(query,GoWith.class),0,0);

    }

    /**
     * {code: '1', name: '今天'},
     * {code: '2', name: '1个月内'},
     * {code: '3', name: '1-3个月内'},
     * {code: '4', name: '3个月以上'}
     */
    private Long[] calcTravelTime(String travelTime) {
        Long[] times = new Long[2];

        if ("1".equals(travelTime)) {
            times[0] = DateUtil.getZeroToday();
            times[1] = DateUtil.getZeroTime(DateUtil.addDays(1));
        } else if ("2".equals(travelTime)) {
            times[0] = DateUtil.getZeroToday();
            times[1] = DateUtil.getZeroTime(DateUtil.addMonths(1));
        } else if ("3".equals(travelTime)) {
            times[0] = DateUtil.getZeroTime(DateUtil.addMonths(1));
            times[1] = DateUtil.getZeroTime(DateUtil.addMonths(3));
        } else {
            times[0] = DateUtil.getZeroTime(DateUtil.addMonths(3));
            times[1] = Long.MAX_VALUE;
        }

        return times;
    }
}
