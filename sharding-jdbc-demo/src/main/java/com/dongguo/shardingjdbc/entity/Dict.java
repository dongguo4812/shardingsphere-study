package com.dongguo.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_dict")
@Data
public class Dict {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String dictType;
}
