package com.example.iot_serve.controller;

import com.example.iot_serve.entity.*;
import com.example.iot_serve.mapper.*;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.pattern.PathPattern;

import javax.annotation.Resource;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/main")
public class Main {

    @Resource
    private rfcard_history_m rfcard_history;
    @Resource
    private rfcard_two_m rf2;

    @Resource
    private rfcard_one_m rf1;

    @Resource
    private users_m userm;

    @Resource
    private flag_one_m flag1;

    @Resource
    private flag_two_m flag2;

    @Resource
    private flag_three_m flag3;

    @Resource
    private flag_four_m flag4;

    @Resource
    private float_one_m entity;

    @Resource
    private float_two_m float2;

    @Resource
    private float_three_m float3;

    @Resource
    private actuator_one_m ac1;

    @Resource
    private actuator_two_m ac2;

    @Resource
    private actuator_three_m ac3;

    @Resource
    private actuator_four_m ac4;


    @GetMapping("/login")
    public String login(@RequestParam(required = true)String username,
                      @RequestParam(required = true)String password){
        users giao=userm.login(username,password);
        if (giao == null){
            return "no";
        }else
            return "ok";
    }


    /**
     * 阈值1
     * @param value
     */
    @GetMapping("/flag1send")
    public void flag1send(@RequestParam(required = true)float value){
        flag_one one=new flag_one(value);
        flag1.insert(one);

    }
    @GetMapping("/flag1get_last")
    public float flag1get(){
        return flag1.flag1get_last().getValue();
    }


    /**
     * 阈值2
     * @param value
     */
    @GetMapping("/flag2send")
    public void flag2send(@RequestParam(required = true)float value){
        flag_two two=new flag_two(value);
        flag2.insert(two);
    }
    @GetMapping("flag2get_last")
    public float flag2get(){return flag2.flag2get_last().getValue();}


    /**
     * 阈值3
     * @param value
     */
    @GetMapping("/flag3send")
    public void flag3send(@RequestParam(required = true)float value){
        flag_three three=new flag_three(value);
        flag3.insert(three);
    }
    @GetMapping("flag3get_last")
    public float flag3get(){return flag3.flag3get_last().getValue();}


    /**
     * 阈值4
     * @param value
     */
    @GetMapping("/flag4send")
    public void flag4send(@RequestParam(required = true)float value){
        flag_four four=new flag_four(value);
        flag4.insert(four);
    }
    @GetMapping("flag4get_last")
    public float flag4get(){return flag4.flag4get_last().getValue();}





    /**
     * 1号float传感器
     * @param value
     */

    @GetMapping("/f1send")
    public void f1send(@RequestParam(required = true)float value){
        float_one one=new float_one(value);
        entity.insert(one);
    }

    @GetMapping("/f1get_bytime")
    public List<float_one> f1get(@RequestParam(required = true)String starttime,@RequestParam(required = true)String endtime){
        List<float_one> giao= entity.f1get_bytime(starttime,endtime);
        return giao;
    }

    @GetMapping("/f1get_last")
    public float_one f1get_last(){
        float_one giao= entity.f1get_last();
        return giao;
    }


    /**
     * 2号float传感器
     * @param value
     */
    @GetMapping("/f2send")
    public void f2send(@RequestParam(required = true)float value){
        float_two one=new float_two(value);
        float2.insert(one);
    }

    @GetMapping("/f2get_bytime")
    public List<float_two> f2get(@RequestParam(required = true)String starttime,@RequestParam(required = true)String endtime){
        List<float_two> giao= float2.f2get_bytime(starttime,endtime);
        return giao;
    }

    @GetMapping("/f2get_last")
    public float_two f2get_last(){
        float_two giao= float2.f2get_last();
        return giao;
    }


    /**
     * 3号float传感器
     * @param value
     */

    @GetMapping("/f3send")
    public void f3send(@RequestParam(required = true)float value){
        float_three one=new float_three(value);
        float3.insert(one);
    }

    @GetMapping("/f3get_bytime")
    public List<float_three> f3get(@RequestParam(required = true)String starttime,@RequestParam(required = true)String endtime){
        List<float_three> giao= float3.f3get_bytime(starttime,endtime);
        return giao;
    }

    @GetMapping("/f3get_last")
    public float_three f3get_last(){
        float_three giao= float3.f3get_last();
        return giao;
    }


    /**
     * 执行器1操作
     */

    @GetMapping("/a1send")
    public void a1send(@RequestParam(required = true)int value){
        actuator_one giao=new actuator_one(value);
        ac1.insert(giao);
    }

    @GetMapping("/a1get_last")
    public int a1get_last(){
        int giao=ac1.a1get_last().getValue();
        return giao;
    }

    /**
     * 执行器2操作
     */

    @GetMapping("/a2send")
    public void a2send(@RequestParam(required = true)int value){
        actuator_two giao=new actuator_two(value);
        ac2.insert(giao);
    }

    @GetMapping("/a2get_last")
    public int a2get_last(){
        int giao=ac2.a2get_last().getValue();
        return giao;
    }

    /**
     * 执行器3操作
     */

    @GetMapping("/a3send")
    public void a3send(@RequestParam(required = true)int value){
        actuator_three giao=new actuator_three(value);
        ac3.insert(giao);
    }

    @GetMapping("/a3get_last")
    public int a3get_last(){
        int giao=ac3.a3get_last().getValue();
        return giao;
    }

    /**
     * 执行器4操作
     */

    @GetMapping("/a4send")
    public void a4send(@RequestParam(required = true)int value){
        actuator_four giao=new actuator_four(value);
        ac4.insert(giao);
    }

    @GetMapping("/a4get_last")
    public int a4get_last(){
        int giao=ac4.a4get_last().getValue();
        return giao;
    }


    /**
     * rfcard1获取状态值
     * @param cardid
     * @return
     */
    @GetMapping("/rfget_gen")
    public int rfget_gen(@RequestParam(required = true) String cardid){
        int en= rf1.rfcard_one_getgeni1(cardid);
        return en;
    }

    @GetMapping("/rfget_last")
    public rfcard_one reget_last(){
        return rf1.rfcard_one_getlast();
    }


    /**
     * 根据人来历史查询借书记录
     * @param starttime
     * @param endtime
     * @param user
     * @return
     */
    @GetMapping("/rfhget_bytime_user")
    public List<rfcard_history>  rfhget_bytime_user(@RequestParam(required = true)String starttime,@RequestParam(required = true)String endtime,@RequestParam(required = true)String user){
        return rfcard_history.rfhget_bytime_user(starttime,endtime,user);
    }


    @GetMapping("/control")
    public void control(@RequestParam(required = true)int feng1,@RequestParam(required = true)int feng2,@RequestParam(required = true)int deng){
        a1send(feng1);
        a2send(feng2);
        a3send(deng);
        a4send(1);
    }


    /**
     * 根据书来历史查询记录
     * @param starttime
     * @param endtime
     * @param book
     * @return
     */
    @GetMapping("rfhget_bytime_book")
    public List<rfcard_history>  rfhget_bytime_book(@RequestParam(required = true)String starttime,@RequestParam(required = true)String endtime,@RequestParam(required = true)String book){
        return rfcard_history.rfhget_bytime_book(starttime,endtime,book);
    }












}
