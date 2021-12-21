package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.actuator_four;
import com.example.iot_serve.entity.actuator_two;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface actuator_four_m extends BaseMapper<actuator_four> {
    @Select("SELECT * FROM actuator_four ORDER BY id DESC LIMIT 1")
    actuator_four a4get_last();
}
