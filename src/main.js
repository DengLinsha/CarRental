import Vue from 'vue'
import App from './App.vue'
import router from "./router/index"
import store from "./store";
// 统一接口api文件夹里面的请求过程
import * as API from "@/api";

import "./assets/css/reset.css"
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import message from './utils/message';
Vue.use(ElementUI);
Vue.prototype.$message = message

import '@icon-park/vue/styles/index.css'

import axios from "axios";
axios.defaults.baseURL = "";
Vue.prototype.$http = axios;// 将axios挂载到Vue的原型对象上

Vue.config.productionTip = false


new Vue({
  render: h => h(App),
  router,
  store,
  beforeCreate() {
    Vue.prototype.$bus = this; // 全局事件总线
    Vue.prototype.$API = API;
  },
}).$mount('#app')
