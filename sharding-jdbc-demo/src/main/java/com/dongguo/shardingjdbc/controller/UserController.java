package com.dongguo.shardingjdbc.controller;

import com.dongguo.shardingjdbc.entity.User;
import com.dongguo.shardingjdbc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dongguo
 * @date 2023/8/9
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 测试负载均衡策略
     */
    @Transactional
    @GetMapping("/insertUser")
    public void selectAll(){
        User user = new User();
        user.setUname("张三");
        userMapper.insert(user);

        List<User> users = userMapper.selectList(null);
    }
}
