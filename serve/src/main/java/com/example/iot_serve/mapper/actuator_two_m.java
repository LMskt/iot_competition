package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.actuator_one;
import com.example.iot_serve.entity.actuator_two;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface actuator_two_m extends BaseMapper<actuator_two> {
    @Select("SELECT * FROM actuator_two ORDER BY id DESC LIMIT 1")
     actuator_two a2get_last();
}
