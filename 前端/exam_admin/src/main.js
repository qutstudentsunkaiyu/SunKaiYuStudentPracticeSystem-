import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './static/css/app.css'
import './static/css/iconfont.css'
import 'font-awesome/css/font-awesome.min.css'
Vue.use(ElementUI)//全局使用Element
Vue.config.productionTip = false

import axios from 'axios';
 
//axios.defaults.timeout = 5000;// 在超时前，所有请求都会等待 5 秒
axios.defaults.headers.post['Content-Type']= 'application/x-www-form-urlencoded;charset=UTF-8';// 配置请求头
axios.defaults.baseURL = 'http://localhost:8082';// 配置接口地址
axios.defaults.withCredentials = false;
 
Vue.prototype.$axios = axios;// 将axios配置成vue的原型


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
