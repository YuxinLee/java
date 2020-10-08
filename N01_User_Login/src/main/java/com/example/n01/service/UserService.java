package com.example.n01.service;

import com.example.n01.exception.JsonException;
import com.example.n01.mapper.UserMapper;
import com.example.n01.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void insert(User user) {
        userMapper.insert(user);
    }


    public User findUserByNameAndPassword(String name, String password) {
        return userMapper.findUserByNameAndPassword(name, password);
    }

    /**
     * 用户注册
     * @param user 用户实体
     * @return user 用户实体
     */
    public void register(User user) throws JsonException {
        User existUser = userMapper.findUserByName(user.getName());
        if (existUser != null) {
            throw new JsonException("0001","用户名已存在！");
        } else {
            userMapper.insert(user);
        }

    }

    /**
     * 用户登录
     * @param user 用户实体
     * @return user 用户实体
     */
    public void login(User user) throws JsonException {
        User existUser = userMapper.findUserByNameAndPassword(user.getName(), user.getPassword());
        if (existUser == null) {
            throw new JsonException("0002","用户名或密码不正确！");
        }
    }

    /**
     * 根据姓名查询用户
     * @param name
     * @return
     */
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }
}
