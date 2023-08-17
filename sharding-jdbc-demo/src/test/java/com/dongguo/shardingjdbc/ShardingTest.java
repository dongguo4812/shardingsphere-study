package com.dongguo.shardingjdbc;

import com.dongguo.shardingjdbc.entity.Order;
import com.dongguo.shardingjdbc.entity.User;
import com.dongguo.shardingjdbc.mapper.OrderMapper;
import com.dongguo.shardingjdbc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

/**
 * @author dongguo
 * @date 2023/8/14
 * @description:
 */
@SpringBootTest
public class ShardingTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 垂直分片：插入数据测试
     */
    @Test
    public void testInsertOrderAndUser(){
        User user = new User();
        user.setUname("张三");
        userMapper.insert(user);

        Order order = new Order();
        order.setOrderNo("SP202308140001");
        order.setUserId(user.getId());
        order.setAmount(new BigDecimal(100));
        orderMapper.insert(order);
    }
    /**
     * 垂直分片：查询数据测试
     */
    @Test
    public void testSelectFromOrderAndUser(){
        User user = userMapper.selectById(1L);
        Order order = orderMapper.selectById(1L);
        System.out.println(user);
        System.out.println(order);
    }

    /**
     * 水平分片：插入数据测试
     */
    @Test
    public void testInsertOrder(){
        Order order = new Order();
        order.setOrderNo("SP202308140001");
        order.setUserId(1L);
        order.setAmount(new BigDecimal(100));
        orderMapper.insert(order);
    }

    /**
     * 水平分片：分库插入数据测试
     */
    @Test
    public void testInsertOrderDatabaseStrategy(){
        for (long i = 1; i <= 10; i++) {
            Order order = new Order();
            order.setOrderNo("SP20230814000" + i);
            order.setUserId(i);
            order.setAmount(new BigDecimal(100));
            orderMapper.insert(order);
        }
    }
    /**
     * 水平分片：分表插入数据测试
     */
    @Test
    public void testInsertOrderTableStrategy(){
        for (long i = 1; i <= 10; i++) {
            Order order = new Order();
            order.setId(i);
            order.setOrderNo("SP20230814000" + i);
            order.setUserId(i);
            order.setAmount(new BigDecimal(100));
            orderMapper.insert(order);
        }
    }

    /**
     * 水平分片：分库分表插入数据
     */
    @Test
    public void testInsertOrderDatabaseAndTableStrategy(){
        for (long i = 1; i < 5; i++) {

            Order order = new Order();
            order.setId(i);
            order.setOrderNo("SP20230814000" + i);
            order.setUserId(1L);
            order.setAmount(new BigDecimal(100));
            orderMapper.insert(order);
        }

        for (long i = 5; i < 9; i++) {
            Order order = new Order();
            order.setId(i);
            order.setOrderNo("SP20230814000" + i);
            order.setUserId(2L);
            order.setAmount(new BigDecimal(100));
            orderMapper.insert(order);
        }
    }

    /**
     * 自动分片创建数据库表
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testCreateAutoOrderMod() {
        jdbcTemplate.execute("CREATE TABLE t_order (\n" +
                "  id BIGINT,\n" +
                "  order_no VARCHAR(30),\n" +
                "  user_id BIGINT,\n" +
                "  amount DECIMAL(10,2),\n" +
                "  PRIMARY KEY(id) USING BTREE\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;");
    }

    /**
     * 分表插入数据测试
     */
    @Test
    public void testInsertOrderShardingStrategy(){

        for (long i = 1; i < 5; i++) {

            Order order = new Order();
            order.setOrderNo("SP20230814000" + i);
            order.setUserId(1L);
            order.setAmount(new BigDecimal(100));
            orderMapper.insert(order);
        }

        for (long i = 5; i < 9; i++) {

            Order order = new Order();
            order.setOrderNo("SP20230814000" + i);
            order.setUserId(2L);
            order.setAmount(new BigDecimal(100));
            orderMapper.insert(order);
        }
    }
}
