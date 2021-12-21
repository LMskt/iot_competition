package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.actuator_three;
import com.example.iot_serve.entity.actuator_two;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * 多个数值执行器
 */
@Mapper
public interface actuator_three_m extends BaseMapper<actuator_three> {

    @Select("SELECT * FROM actuator_three ORDER BY id DESC LIMIT 1")
     actuator_three a3get_last();
}
