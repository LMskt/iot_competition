import Vue from 'vue'
import VueRouter from 'vue-router'
import homepage from "@/views/homepage";
import humidity from "@/views/humidity";
import lightIntensity from "@/views/lightIntensity";
import temperature from "@/views/temperature";

Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    redirect:'/homepage'
  },
  {
    path: '/homepage',
    name:'homepage',
    component:homepage,
    children:[
      {
        path: '/humidity',
        name:'humidity',
        component: humidity
      },
      {
        path: '/lightintensity',
        name: 'lightintensity',
        component: lightIntensity
      },
      {
        path: '/temperature',
        name:'temperature',
        component: temperature
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
