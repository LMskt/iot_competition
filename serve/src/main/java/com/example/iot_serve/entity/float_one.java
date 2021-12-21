package com.example.iot_serve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class float_one {
    @TableId(type = IdType.AUTO)
    Integer id;
    float value;
    Timestamp time;

    public float_one(float value) {
        this.value = value;
    }
}
