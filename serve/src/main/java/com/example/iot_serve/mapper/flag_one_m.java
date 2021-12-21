package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.flag_one;
import com.example.iot_serve.entity.float_one;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface flag_one_m extends BaseMapper<flag_one> {
    @Select("SELECT * FROM flag_one ORDER BY id DESC LIMIT 1")
    flag_one flag1get_last();
}
