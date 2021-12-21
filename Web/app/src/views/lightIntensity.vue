<template>
  <div>
    <div style="text-align: center">
      <div>
        <div style="float: left;margin-left: 50px">最大值：{{big}}</div>
        <div style="float: left;margin-left: 100px">最小值：{{small}}</div>
      </div>
      <div id="giao" style="width: 1000px;height: 400px;margin-top: 20px;margin-left: auto;margin-right: auto"></div>
      <div>
        <div style="float: left; width: 100px;margin-left: 50px">阈值：<el-input v-model="yuzhi" placeholder="请输入内容"></el-input></div>
        <div style="float: left;margin-left: 100px">
          <div id="tu" ><img src="../../public/deng.png" style="width: 100px;height: 100px" alt="" ></div>
          <div id="tu2" style="display: none"><img src="../../public/deng2.png" style="width: 100px;height: 100px" alt="" ></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import store from "@/store";

export default {
name: "lightIntensity",
  data(){
    return{
      dom:null,
      big:null,
      small:null,
      yuzhi:null,
      option:{
        tooltip:{
          tigger:'axis'
        },
        xAxis:{
          type:'category',
          boundaryGap:false,
          name:"时间",
          // data: dataX.slice(0,10),
          axisLable:{
            interval:0,
            rotate:60
          },
          axisTick:{  //隔几个空格显示刻度
            show:true
          },
        },
        yAxis: {
          show: true,
          axisLine:{
            show:true
          },
          name:'光照',
          min:0,
          max:80
          // type: 'value'
        },
        // 声明多个 bar 系列，默认情况下，每个系列会自动对应到 dataset 的每一列。
        series: [{
          type: 'line',
          itemStyle : { normal: {label : {show: true}}},
          markLine:{
            data : [
              {type : 'average', name: '平均值',label: {
                  show:true,
                  fontStyle:'oblique',
                }}
            ]
          }
        }]
      }
    }
  },
  mounted() {
    this.set_option()
  },
  methods:{
    set_option(){
      this.dom=this.$echarts.init(document.getElementById("giao"))
      this.dom.setOption(this.option)
    }
  },
  computed:{
    isFollow(){
      return store.state.guang
    }
  },
  watch:{
    isFollow(newVal,oldVal){
      this.dom.setOption({
        xAxis: {
          data:store.getters.get_time_x
        },
        series: [{
          data:store.getters.get_guang_x
        }]
      })
      this.big=store.getters.get_guang_m[1]
      this.small=store.getters.get_guang_m[0]
      if(store.getters.get_bool[2]==false){
        // console.log("超过了！")
        document.getElementById('tu2').style.display=''
        document.getElementById('tu').style.display='none'
      }else {
        // console.log("没事了")
        document.getElementById('tu').style.display=''
        document.getElementById('tu2').style.display='none'
      }
    },
    yuzhi(newVal,oldVal){
      store.commit('set_guang_zhi',newVal)

    }
  }
}
</script>

<style scoped>

</style>
