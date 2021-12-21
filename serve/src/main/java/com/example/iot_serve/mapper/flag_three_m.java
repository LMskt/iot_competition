package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.flag_three;
import com.example.iot_serve.entity.flag_two;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface flag_three_m extends BaseMapper<flag_three> {
    @Select("SELECT * FROM flag_three ORDER BY id DESC LIMIT 1")
    flag_three flag3get_last();
}

