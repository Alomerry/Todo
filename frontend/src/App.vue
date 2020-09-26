<template>
  <div id="app">
    <template>
      <a-layout id="components-layout-demo-side" style="min-height: 100vh">
        <a-layout-sider v-model="collapsed" collapsible>
          <div class="logo"/>
          <a-menu theme="dark" :default-selected-keys="[selectedMenuItemKey]" mode="inline" @click="selectMeunItem">
            <a-menu-item key="1">
              <a-icon type="pie-chart"/>
              <span>Login</span>
              <router-link to="/login"></router-link>
            </a-menu-item>
            <a-menu-item key="2">
              <a-icon type="pie-chart"/>
              <span>Todo</span>
              <router-link to="/todos"></router-link>
            </a-menu-item>
          </a-menu>
        </a-layout-sider>
        <a-layout>
          <a-layout-header style="background: #fff; padding: 0;margin-bottom: 30px">
            <template v-if="!isLogin">
              <router-link to="/login" style="float: right;margin-right: 5em">Login</router-link>
            </template>
            <template v-else>
              <span style="float: right;margin-right: 5em">
								<a-dropdown>
                  <a class="ant-dropdown-link" @click="e => e.preventDefault()">
                    {{$store.state.user.name}} <a-icon type="down"/>
                  </a>
                  <a-menu slot="overlay">
                    <a-menu-item>
                      <a href="javascript:;" @click="logout">Logout</a>
                    </a-menu-item>
                  </a-menu>
                </a-dropdown>
							</span>
            </template>
          </a-layout-header>
          <a-layout-content style="margin: 0 16px">
            <div :style="{ padding: '24px 24px', background: '#fff', minHeight: '360px' }">
              <router-view/>
            </div>
          </a-layout-content>
        </a-layout>
      </a-layout>
    </template>
  </div>
</template>
<script>

  export default {
    name: "App",
    data() {
      return {
        collapsed: false,
        selectedMenuItemKey: 1,
      };
    },
    mounted() {
    },
    methods: {
      selectMeunItem(item) {
        this.selectedMenuItemKey = item.key;
      },
      logout() {
        this.$store.dispatch('logout');
        setTimeout(function () {
          window.location = '/login';
        }, 300);
      }
    },
    computed: {
      isLogin() {
        return this.$store.state.user.accessToken;
      }
    },
  }

</script>
<style>
  #components-layout-demo-side .logo {
    height: 32px;
    background: rgba(255, 255, 255, 0.2);
    margin: 16px;
  }

  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }

  #nav {
    padding: 30px;
  }

  #nav a {
    font-weight: bold;
    color: #2c3e50;
  }

  #nav a.router-link-exact-active {
    color: #42b983;
  }
</style>
