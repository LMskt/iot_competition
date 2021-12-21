package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.float_one;
import com.example.iot_serve.entity.float_two;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface float_two_m extends BaseMapper<float_two> {
    @Select("SELECT * FROM float_two WHERE TIME BETWEEN #{starttime} AND #{endtime}")
    List<float_two> f2get_bytime(String starttime, String endtime);

    @Select("SELECT * FROM float_two ORDER BY id DESC LIMIT 1")
    float_two f2get_last();
}
