import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import JsonExcel from "vue-json-excel";
import'element-ui/lib/theme-chalk/index.css';
import * as Echarts from 'echarts'
Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.component('downloadExcel',JsonExcel)
Vue.prototype.$echarts=Echarts
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')


