package com.panzhihua.travel.dto.req;

import lombok.Data;

@Data
public class QueryCondition {
    private String search;
    private Integer pageNum = 1;
    private Integer pageSize = 15;
    private Long timestamp;
    private String sortField;
    private String sortKey;
    private String refId;

    public int startPage() {
        if (pageNum == 1) {
            return 0;
        }
        return (pageNum - 1) * pageSize;
    }
}
