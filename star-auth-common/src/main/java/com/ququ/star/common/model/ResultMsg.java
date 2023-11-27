package com.ququ.star.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @Author: 彭淳
 * @Date: 2023/11/21 11:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ResultMsg {
    private Integer code;

    private String msg;

    private Object data;

    public static ResultMsg ok(String msg) {
        return new ResultMsg(200, msg, null);
    }

    public static ResultMsg error(String msg) {
        return new ResultMsg(500, msg, null);
    }

    public static ResultMsg ok(Object data) {
        return new ResultMsg(200, "操作成功", data);
    }
}
