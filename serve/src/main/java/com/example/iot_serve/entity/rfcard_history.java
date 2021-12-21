package com.example.iot_serve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class rfcard_history {
    @TableId(type = IdType.AUTO)
    Integer id;
    String cardid1;
    String cardid2;
    Integer type;
    Timestamp time;

    public rfcard_history(String cardid1, String cardid2, Integer type) {
        this.cardid1 = cardid1;
        this.cardid2 = cardid2;
        this.type = type;
    }
}
