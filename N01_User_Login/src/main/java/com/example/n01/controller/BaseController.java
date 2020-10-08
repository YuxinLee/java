//package com.example.n01.controller;
//
//import com.example.n01.exception.JsonException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.Map;
//
//public class BaseController {
//
//    @ResponseBody
//    @ExceptionHandler
//    public Map<String, Object> baseControllerException(Exception exception) {
//        Map<String,Object> stringObjectMap = null;
//        if (exception instanceof JsonException) {
//            JsonException jsonException = (JsonException) exception;
//            stringObjectMap = jsonException.toMsgMap();
//        } else {
//            JsonException jsonException = new JsonException("9999", "系统繁忙！");
//            stringObjectMap = jsonException.toMsgMap();
//        }
//
//        return stringObjectMap;
//    }
//}
