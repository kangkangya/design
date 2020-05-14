package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.FoodRepository;
import com.panzhihua.travel.dto.req.FoodQueryCondition;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Food;
import com.panzhihua.travel.entity.User;
import com.panzhihua.travel.utils.IDUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class FoodService {
    private final MongoTemplate mongoTemplate;

    private final FoodRepository foodRepository;

    public RetDto save(Food food) {

        return RetDto.success(foodRepository.save(food), 0, 0);
    }

    public void delete(String id) {
        foodRepository.deleteById(id);
    }

    public RetDto findById(String id){
        Food food = new Food();
        food.setId(id);
        return RetDto.success(foodRepository.findOne(Example.of(food)),0,0);
    }

    public RetDto find(FoodQueryCondition foodQueryCondition) {
        Query query = new Query();

        if (!StringUtils.isEmpty(foodQueryCondition.getSearch())) {
            Pattern pattern = Pattern.compile("^.*" + foodQueryCondition.getSearch() + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("title").regex(pattern));
        }

        if (!StringUtils.isEmpty(foodQueryCondition.getSortField())) {
            query.with(Sort.by("asc".equals(foodQueryCondition.getSortKey()) ?
                    Sort.Order.asc(foodQueryCondition.getSortField()) :
                    Sort.Order.desc(foodQueryCondition.getSortField())));
        } else {
            query.with(Sort.by(Sort.Order.desc("timestamp")));
        }

        if (foodQueryCondition.getTimestamp() == null ||
                foodQueryCondition.getTimestamp() <= 0) {
            foodQueryCondition.setTimestamp(0l);
        }

        query.addCriteria(Criteria.where("timestamp").gt(foodQueryCondition.getTimestamp()));

        if (!StringUtils.isEmpty(foodQueryCondition.getFoodCode())) {
            query.addCriteria(Criteria.where("foodCode").in((foodQueryCondition.getFoodCode())));
        }

        if (!StringUtils.isEmpty(foodQueryCondition.getRegionCode())) {
            query.addCriteria(Criteria.where("regionCode").in((foodQueryCondition.getRegionCode())));
        }

        int count = (int) mongoTemplate.count(query, Food.class);

        query.skip((foodQueryCondition.startPage()))
                .limit(foodQueryCondition.getPageSize());

        return RetDto.builder()
                .pageSize(foodQueryCondition.getPageSize())
                .body(mongoTemplate.find(query, Food.class))
                .total(count).build();
    }
}
