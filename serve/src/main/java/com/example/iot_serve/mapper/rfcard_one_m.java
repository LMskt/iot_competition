package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.rfcard_one;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper

public interface rfcard_one_m extends BaseMapper<rfcard_one> {
    @Select("SELECT * FROM rfcard_one WHERE cardid=#{cardid}")
    rfcard_one rfcard_one_get(String cardid);

    //更新书的状态
    @Update("UPDATE rfcard_one SET geni1 =#{geni1} WHERE cardid=#{cardid}")
    void rfcard_one_update(Integer geni1,String cardid);

    //获取状态
    @Select("SELECT geni1 FROM rfcard_one WHERE cardid=#{cardid}")
    Integer rfcard_one_getgeni1(String cardid);


    @Select("SELECT * FROM rfcard_one ORDER BY id DESC LIMIT 1")
    rfcard_one rfcard_one_getlast();




}
