package com.example.n01.service;

import com.example.n01.mapper.UserMapper;
import com.example.n01.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceTest {

    @BeforeAll
    public static void initMocks() {
        MockitoAnnotations.initMocks(UserServiceTest.class);
    }


    @Autowired
    private UserService userService;

//    @Autowired
//    private UserMapper userMapper;

    @Mock
    private UserMapper userMapper;

    @Test
    public void mockTest() {
        User user = new User();
        user.setName("xiaohong");
        user.setAge(28);
        user.setPassword("123456");
        Mockito.when(userService.findUserByName("xiaowang")).thenReturn(user);

        User mockUser = userService.findUserByName("xiaowang");
        System.out.println(mockUser);
    }

    @Test
    public void insertUserTest() {
        User user = new User();
        user.setName("xiaowang");
        user.setAge(28);
        user.setPassword("123456");
        userMapper.insert(user);
    }

    @Test
    public void findUserByName() {
        System.out.println(userMapper.findUserByName("xiaowang"));
    }
}
