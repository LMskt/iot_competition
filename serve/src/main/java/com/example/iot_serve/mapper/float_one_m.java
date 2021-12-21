package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.float_one;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface float_one_m extends BaseMapper<float_one> {
    @Select("SELECT * FROM float_one WHERE TIME BETWEEN #{starttime} AND #{endtime}")
    List<float_one> f1get_bytime(String starttime,String endtime);

    @Select("SELECT * FROM float_one ORDER BY id DESC LIMIT 1")
    float_one f1get_last();

}
