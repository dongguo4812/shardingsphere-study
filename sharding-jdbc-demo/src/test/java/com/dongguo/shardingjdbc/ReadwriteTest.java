package com.dongguo.shardingjdbc;

import com.dongguo.shardingjdbc.entity.User;
import com.dongguo.shardingjdbc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class ReadwriteTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 写入数据的测试
     */
    @Test
    public void testInsert(){

        User user = new User();
        user.setUname("张三丰");
        userMapper.insert(user);
    }

    @Test
    public void testSelect(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void testReadwrite(){
        User user = new User();
        user.setUname("张三");
        userMapper.insert(user);
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
    @Transactional
    @Test
    public void testTransactional(){
        User user = new User();
        user.setUname("张三");
        userMapper.insert(user);
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testLoadBalancer(){
        for (long i = 1L; i <= 3L; i++) {
            User user = userMapper.selectById(i);
            System.out.println(user);
        }
    }
}
