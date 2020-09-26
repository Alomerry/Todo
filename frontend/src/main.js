import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import axios from 'axios'

Vue.config.productionTip = false;
axios.defaults.withCredentials = true;
Vue.prototype.$axios = axios;
axios.defaults.baseURL = '/api';

Vue.use(Antd);
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
