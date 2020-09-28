import axios from 'axios' //使用ajax
import store from '../store/index'

axios.defaults.withCredentials = true;
axios.defaults.xsrfHeaderName = 'X-CSRFToken';
axios.defaults.xsrfCookieName = 'csrftoken';

export default {
  updateTodo(id, map) {
    let params = {};
    map.forEach(function (value, key) {
      console.log(key, value);
      if (key == "status")
        params[key] = {
          value: value,
        };
      else
        params[key] = value;
    })
    console.log(params);
    return axios.put("/todo/" + id, params, {
      headers: {
        "AccessToken": store.state.user.accessToken
      },
    })
  },
  DoneTodo(id) {
    return axios.post("/done/" + id, null, {
      headers: {
        "AccessToken": store.state.user.accessToken
      },
    })
  },

  login(name, password) {
    let params = new URLSearchParams();
    params.append('name', name);
    params.append('password', password);
    return axios.post('/login', params)
  },
  register(name, password, repeatPassword) {
    let params = new URLSearchParams();
    params.append('name', name);
    params.append('password', password);
    params.append('repeatPassword', repeatPassword);
    return axios.post('/register', params)
  },

  getTodos(page, pageSize, sortBy, isAscend, map) {
    let data = {};
    data.page = page ?? 1;
    data.pageSize = pageSize ?? 10;
    data.sortBy = sortBy ?? "createdAt";
    data.isAscend = {
      value: isAscend ?? false
    }
    data.startAt = 0;
    data.endAt = 0;
    if (map != null)
      map.forEach(function (value, key) {
        console.log(key, value);
        if (key == "status")
          data["status"] = {
            value: value,
          }
        else
          data[key] = value;
      })
    return axios.get('/todos', {
      headers: {
        "AccessToken": store.state.user.accessToken,
        "Content-Type": "application/json"
      },
      params: data,
    })
  },

  createTodo(color, content) {
    let params = new URLSearchParams();
    params.append('color', color);
    params.append('content', content);
    return axios.post('/todos', params, {
      headers: {
        "AccessToken": store.state.user.accessToken
      },
    })
  },

  deleteTodo(id) {
    return axios.delete('/todo/' + id, {
      headers: {
        "AccessToken": store.state.user.accessToken
      },
    })
  },
}
