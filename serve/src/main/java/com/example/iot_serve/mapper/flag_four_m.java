package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.flag_four;
import com.example.iot_serve.entity.flag_three;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface flag_four_m extends BaseMapper<flag_four> {
    @Select("SELECT * FROM flag_four ORDER BY id DESC LIMIT 1")
    flag_four flag4get_last();
}
