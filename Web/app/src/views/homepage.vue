<template>
<div>
  <el-container>
    <el-header>
      <el-menu
          default-active="this.$route.path" router
          class="el-menu-demo"
          mode="horizontal">
        <el-menu-item index="/temperature">温度</el-menu-item>
        <el-menu-item index="/humidity">湿度</el-menu-item>
        <el-menu-item index="/lightintensity">光照</el-menu-item>
<!--        <el-button @click="woco">导出历史</el-button>-->
      </el-menu>
    </el-header>
    <el-container>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
  <download-excel
      class = "export-excel-wrapper"
      :fetch = "woco"
      :fields = "json_fields"
      name = "历史数据.xls">
    <!-- 上面可以自定义自己的样式，还可以引用其他组件button -->
    <el-button type="primary" size="small" id="tidaibutton">导出EXCEL</el-button>
  </download-excel>
</div>
</template>

<script>
import axios from "axios";
import store from "@/store";
export default {
  name: "homepage",
  data(){
    return{
      json_fields: {
        "时间":"time",
        "温度":"wen",
        "湿度":"shi",
        "光照":"guang"
      },
      json_meta: [
        [
          {
            " key ": " charset ",
            " value ": " utf- 8 "
          }
        ]
      ]
    }
  },
  methods:{
    gethttps:function(){
      axios({
        url:'https://iot.csust-iotlab.com/api/',
        method:'get'
      }).then(function (res){
        store.commit('set_value',{valuew:res.data.data.temperature,values:res.data.data.humidity,valueg:res.data.data.lightIntensity})
        store.commit('set_m',{valuew:res.data.data.temperature,values:res.data.data.humidity,valueg:res.data.data.lightIntensity})
      }).catch(function (err){
        console.log(err)
      })

    },
     woco(){
      let giao=[]
       console.log(store.getters.get_wen.length)
      for(var i=0;i<store.getters.get_wen.length;i++){
        giao.push({
          time:store.getters.get_time[i],
          wen:store.getters.get_wen[i],
          shi:store.getters.get_shi[i],
          guang:store.getters.get_guang[i]
        })
      }
       return giao

    },
    expor(){
      let exportBook={
        time:store.getters.get_time,
        shi:store.getters.get_shi,
        wen:store.getters.get_wen,
        guang:store.getters.get_guang
      }
      getBookExport(exportBook).then(res=>{
        if(res.data.errcode === 0){
          this.exportList = res.data.data
          this.export2Excel()
        } else if (res.data.errcode === 40190){
          this.$Message.info('数据太多无法导出，请联系客服！')
        }
      })
    },
    export2Excel(){
      require.ensure([],()=>{
        const {export_json_to_excel}=require('../excel/Export2Excel');
        const tHeader=['时间','湿度','温度','光强'];
        const filterVal =['time','shi','wen','guang'];
        const list=this.exportList;
        const data=this.formatJson(filterVal,list);
        export_json_to_excel(tHeader,data,'历史数据');
      })
    },
    formatJson(filterVal,jsonData){
      return jsonData.map(j => v[j])
    }
  },
  created() {
    setInterval(this.gethttps,5000)
    // this.gethttps()
  }
}
</script>

<style scoped>

</style>
