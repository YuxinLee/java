package com.example.n01.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.n01.exception.JsonException;
import com.example.n01.model.User;
import com.example.n01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody JSONObject jsonParam) throws JsonException {
        Map<String, Object> stringObjectMap = new HashMap<>();
        User user = jsonParam.getObject("user", User.class);
        userService.register(user);
        stringObjectMap.put("code", "0000");
        stringObjectMap.put("message", "注册成功");
        return stringObjectMap;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody JSONObject jsonParam) throws JsonException{
        Map<String, Object> stringObjectMap = new HashMap<>();
        User user = jsonParam.getObject("user", User.class);
        userService.login(user);
        stringObjectMap.put("code", "0000");
        stringObjectMap.put("message", "登录成功");
        return stringObjectMap;
    }

//    @PostMapping("/onSelect")
//    public Map<String, Object> onSelect(@RequestBody JSONObject jsonParam){
//        Map<String, Object> stringObjectMap = new HashMap<>();
//        User user = jsonParam.getObject("user", User.class);
//        User user2 = userService.findUserByName(user.getName());
//        stringObjectMap.put("code", "0000");
//        stringObjectMap.put("message", "查询成功");
//        stringObjectMap.put("users", user2);
//        return stringObjectMap;
//    }

    @PostMapping("/onSelect")
    public Map<String, Object> onSelect(@RequestBody JSONObject jsonParam){
        Map<String, Object> stringObjectMap = new HashMap<>();
        User user = jsonParam.getObject("user", User.class);
        List<User> userList = userService.findAll();
        stringObjectMap.put("code", "0000");
        stringObjectMap.put("message", "查询成功");
        stringObjectMap.put("users", userList);
        return stringObjectMap;
    }

}
