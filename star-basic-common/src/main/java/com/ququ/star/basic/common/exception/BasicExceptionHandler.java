package com.ququ.star.basic.common.exception;

import com.ququ.star.common.model.ResultMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @Author: 彭淳
 * @Date: 2023/11/24 14:07
 */
@Slf4j
@ControllerAdvice
public class BasicExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultMsg errorHandler(Exception exception) {
        return ResultMsg.error(exception.getMessage());
    }
}
