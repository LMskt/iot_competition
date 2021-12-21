package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.rfcard_history;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface rfcard_history_m extends BaseMapper<rfcard_history> {
    @Select("SELECT rfcard_two.gens1 AS username, rfcard_one.gens1 AS bookname,rfcard_history.type,rfcard_history.time FROM rfcard_one,rfcard_two,rfcard_history " +
            " WHERE (rfcard_history.TIME BETWEEN #{starttime} AND  #{endtime}) AND rfcard_history.cardid2=#{user} and rfcard_two.cardid=#{user}")
    List<rfcard_history> rfhget_bytime_user(String starttime,String endtime,String user);

    @Select("SELECT rfcard_two.gens1 AS username, rfcard_one.gens1 AS bookname,rfcard_history.type,rfcard_history.time FROM rfcard_one,rfcard_two,rfcard_history " +
            " WHERE (rfcard_history.TIME BETWEEN #{starttime} AND  #{endtime}) AND rfcard_history.cardid1=#{book} AND rfcard_one.cardid=#{book}")
    List<rfcard_history> rfhget_bytime_book(String starttime,String endtime,String book);







}
