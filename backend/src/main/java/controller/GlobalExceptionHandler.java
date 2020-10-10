package controller;

import core.Result;
import core.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Result errorHandler(Exception e) throws Exception {
        return new Result().setCode(ResultCode.BAD_REQUEST).setMessage(e.getMessage());
    }
}
