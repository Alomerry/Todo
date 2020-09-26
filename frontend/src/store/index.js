import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

let defaultUser = {
  id: null,
  name: null,
  accessToken: null,
};

try {
  if (localStorage.getItem("user")) {
    defaultUser = JSON.parse(localStorage.getItem("user"));
  }
} catch (e) {
  console.log(e)
}
export default new Vuex.Store({
  state: {
    user: defaultUser
  },
  mutations: {
    login(state, user) {
      state.user = user;
      localStorage.setItem("user", JSON.stringify(user));
    },
    logout(state) {
      state.user = {
        id: null,
        name: null,
        accessToken: null,
      };
      localStorage.removeItem("user");
      localStorage.clear();
    }
  },
  actions: {
    login(context, user) {
      context.commit("login", user);
    },
    logout(context) {
      context.commit("logout");
    }
  },
  modules: {}
})
