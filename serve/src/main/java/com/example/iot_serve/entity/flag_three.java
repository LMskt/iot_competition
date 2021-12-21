package com.example.iot_serve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class flag_three {
    @TableId(type = IdType.AUTO)
    Integer id;
    float value;

    public flag_three(float value) {
        this.value = value;
    }
}
