package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.rfcard_two;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface rfcard_two_m extends BaseMapper<rfcard_two> {
    @Select("SELECT * FROM rfcard_two WHERE cardid=#{cardid}")
    rfcard_two rfcard_two_get(String cardid);

}
