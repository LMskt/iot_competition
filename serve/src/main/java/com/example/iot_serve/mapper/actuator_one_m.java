package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.actuator_one;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface actuator_one_m extends BaseMapper<actuator_one> {
    @Select("SELECT * FROM actuator_one ORDER BY id DESC LIMIT 1")
     actuator_one a1get_last();

}
