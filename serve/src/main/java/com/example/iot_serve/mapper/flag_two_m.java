package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.flag_one;
import com.example.iot_serve.entity.flag_two;
import com.example.iot_serve.entity.float_one;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface flag_two_m extends BaseMapper<flag_two> {
    @Select("SELECT * FROM flag_two ORDER BY id DESC LIMIT 1")
    flag_two flag2get_last();
}
