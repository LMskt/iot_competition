package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.float_one;
import com.example.iot_serve.entity.float_three;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface float_three_m extends BaseMapper<float_three> {
    @Select("SELECT * FROM float_three WHERE TIME BETWEEN #{starttime} AND #{endtime}")
    List<float_three> f3get_bytime(String starttime, String endtime);

    @Select("SELECT * FROM float_three ORDER BY id DESC LIMIT 1")
    float_three f3get_last();
}
