package com.example.iot_serve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class actuator_four {
    @TableId(type = IdType.AUTO)
    Integer id;
    int value;
    Timestamp time;

    public actuator_four(int value) {
        this.value = value;
    }
}
