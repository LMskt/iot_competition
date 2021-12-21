import Vue from 'vue'
import Vuex from 'vuex'
import el from "element-ui/src/locale/lang/el";

Vue.use(Vuex)

function gettime(){
  let date=new Date();
  let hh=date.getHours()<10?"0"+date.getHours():date.getHours();
  let mm=date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes();
  let ss=date.getSeconds()<10?"0"+date.getSeconds():date.getSeconds();
  return hh+":"+mm+":"+ss
}

export default new Vuex.Store({
  state: {
    wen:new Array(),
    shi:new Array(),
    guang:new Array(),
    time:new Array(),
    wen_max:0,
    wen_min:10000,
    shi_max:0,
    shi_min:10000,
    guang_max:0,
    guang_min:10000,
    wen_zhi:100,
    shi_zhi:100,
    guang_zhi:100,
    wen_zhi_bool:true,
    shi_zhi_bool:true,
    guang_zhi_bool:true
  },
  mutations: {
    set_value(state,giao){
      state.wen.push(giao.valuew)
      state.guang.push(giao.valueg)
      state.shi.push(giao.values)
      state.time.push(gettime())
      if(giao.valuew>state.wen_zhi){
        state.wen_zhi_bool=false
      }else {
        state.wen_zhi_bool=true
      }

      if(giao.values>state.shi_zhi){
        state.shi_zhi_bool=false
      }else {
        state.shi_zhi_bool=true
      }

      if(giao.valueg>state.guang_zhi){
        state.guang_zhi_bool=false
      }else {
        state.guang_zhi_bool=true
      }

    },
    set_m(state,giao){
      if(state.wen_min>giao.valuew)
        state.wen_min=giao.valuew
      if(state.shi_min>giao.values)
        state.shi_min=giao.values
      if(state.guang_min>giao.valueg)
        state.guang_min=giao.valueg
      if(state.wen_max<giao.valuew)
        state.wen_max=giao.valuew
      if(state.shi_max<giao.values)
        state.shi_max=giao.values
      if(state.guang_max<giao.valueg)
        state.guang_max=giao.valueg
    },
    set_wen_zhi(state,value){
      state.wen_zhi=value
    },
    set_shi_zhi(state,value){
      state.shi_zhi=value
    },
    set_guang_zhi(state,value){
      state.guang_zhi=value
    }
  },
  getters:{
    get_wen(state){
      return state.wen
    },
    get_wen_x(state){
      return state.wen.slice(-12)
    },
    get_shi(state){
      return state.shi
    },
    get_shi_x(state){
      return state.shi.slice(-12)
    },
    get_guang(state){
      return state.guang
    },
    get_guang_x(state){
      return state.guang.slice(-12)
    },
    get_time(state){
      return state.time
    },
    get_time_x(state){
      return state.time.slice(-12)
    },
    get_wen_m(state){
      return [state.wen_min,state.wen_max]
    },
    get_shi_m(state){
      return [state.shi_min,state.shi_max]
    },
    get_guang_m(state){
      return [state.guang_min,state.wen_max]
    },
    get_bool(state){
      return [state.wen_zhi_bool,state.shi_zhi_bool,state.guang_zhi_bool]
    },
    get_boolt(state){
      return [state.wen_zhi,state.shi_zhi,state.guang_zhi]
    }
  },
  actions: {
  },
  modules: {
  }
})
