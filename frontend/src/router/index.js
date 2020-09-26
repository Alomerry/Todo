import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "../store";
import {message} from "ant-design-vue";

Vue.use(VueRouter)

const routes = [
  {
    path: '/todos',
    name: 'Todos',
    component: () => import('../views/Todos')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path != "/login" && store.state.user !== null && store.state.user.accessToken !== null) {
    next(true);
  } else if (to.path == "/login") {
    next(true);
  } else {
    message.info("Please login first")
    next({path: "/login"}, to.path);
  }
})


export default router
