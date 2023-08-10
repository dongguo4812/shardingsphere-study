package com.dongguo.shardingjdbc.controller;

import com.dongguo.shardingjdbc.entity.User;
import com.dongguo.shardingjdbc.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "用戶管理")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 测试负载均衡策略
     */
    @ApiOperation("获取用户列表,测试负载均衡策略")
    @GetMapping("/selectAll")
    public void selectAll(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
