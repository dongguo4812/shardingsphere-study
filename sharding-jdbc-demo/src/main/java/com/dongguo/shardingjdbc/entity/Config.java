package com.dongguo.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_config")
@Data
public class Config {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String config;
}
