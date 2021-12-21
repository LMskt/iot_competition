package com.example.iot_serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iot_serve.entity.users;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface users_m extends BaseMapper<users> {

    @Select("SELECT * FROM users WHERE username = #{username} AND password =#{password}")
    users login(String username,String password);

}
