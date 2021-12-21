package com.example.iot_serve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.models.auth.In;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class rfcard_two {
    @TableId(type = IdType.AUTO)
    Integer id;
    String cardid;
    String gens1;
    String gens2;
    Integer geni1;
    Integer geni2;
    Timestamp time;


}
