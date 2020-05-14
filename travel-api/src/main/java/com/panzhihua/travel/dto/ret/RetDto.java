package com.panzhihua.travel.dto.ret;

import com.panzhihua.travel.enums.RetEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetDto<T> {
    private int total;
    private int pageSize;
    private int code;
    private String msg;
    private T body;

    public int getTotalPage() {
        if (pageSize == 0) return 0;

        if ((total % pageSize) == 0) {
            return total / pageSize;
        } else {
            return total / pageSize + 1;
        }
    }

    public static RetDto build(int code, Object body, int total, int pageSize, String msg) {
        return RetDto.builder().code(code).total(total).msg(msg).pageSize(pageSize).body(body).build();
    }

    public static RetDto success(Object body, int total, int pageSize) {
        return build(RetEnum.SUCCESS.getCode(), body, total, pageSize, null);
    }

    public static RetDto fail(String msg){
        return build(RetEnum.INTERNAL_ERROR.getCode(), null, 0, 0,msg );

    }

    public static RetDto success() {
        return success(null, 0, 0);
    }

    public static RetDto internalError() {
        return build(RetEnum.INTERNAL_ERROR.getCode(), null, 0, 0, RetEnum.INTERNAL_ERROR.getMsg());
    }

    public static RetDto userExistsError() {
        return build(RetEnum.USER_EXISTS.getCode(), null, 0, 0, RetEnum.USER_EXISTS.getMsg());
    }

    public static RetDto userNotExistsError() {
        return build(RetEnum.USER_NOT_EXISTS.getCode(), null, 0, 0, RetEnum.USER_NOT_EXISTS.getMsg());
    }
}
