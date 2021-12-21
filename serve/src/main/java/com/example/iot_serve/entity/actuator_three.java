package com.example.iot_serve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.security.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class actuator_three {
    @TableId(type = IdType.AUTO)
    Integer id;
    int value;
    Timestamp time;

    public actuator_three(int value) {
        this.value = value;
    }
}
