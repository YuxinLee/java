package com.example.demo.repository;

import com.example.demo.model.Deparment;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeparmentRepository deparmentRepository;

    @Autowired
    RoleRepository roleRepository;



    @Test
    public void initUser(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
        deparmentRepository.deleteAll();

        Deparment deparment = new Deparment();
        deparment.setName("开发部");
        deparmentRepository.save(deparment);
        Assert.notNull(deparment.getId());

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.notNull(role.getId());

        User user = new User();
        user.setName("user");
        user.setCreateDate(new Date());
        user.setDeparment(deparment);

        List<Role> roles = roleRepository.findAll();
        Assert.notNull(roles);
        user.setRoles(roles);

        userRepository.save(user);
        Assert.notNull(user.getId());
    }

    @Test
    public void findPage(){
        Pageable pageable = PageRequest.of(0, 10,
                Sort.by(Sort.Direction.ASC,"id"));
        Page<User> page = userRepository.findAll(pageable);
        Assert.notNull(page);
        for(User user : page.getContent() ) {
            System.out.println(user.getName() +"-------"+
                    user.getDeparment().getName() + "-------" +
                    user.getRoles().get(0).getName());
        }
    }

    List<String> list = new ArrayList<String>();
}
