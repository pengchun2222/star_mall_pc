package com.ququ.star.basic.common.exception;

import com.ququ.star.common.model.IErrorCode;

/**
 * @Author: 彭淳
 * @Date: 2023/12/4 16:31
 * 断言处理类，用于抛出各种API异常
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
