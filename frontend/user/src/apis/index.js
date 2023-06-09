import axios from 'axios'

import ElementUI from 'element-ui';

axios.defaults.baseURL = 'http://localhost:8088';
// 读取api.js内部定义的请求方法
import * as welcome from './welcome.js';
import * as recruitEdit from './recruitEdit.js';
import * as settings from './settings.js';
import * as account from './account.js';
import * as history from './history.js'
import * as recruitList from './recruitList.js';
import * as recruitContent from './recruitContent.js'
import * as messages from './messages.js'
import * as team from './team.js'
import * as teams from './teams.js'


// http request拦截器 添加一个请求拦截器
axios.interceptors.request.use(function (config) {

  config.headers['Content-Type'] = "multipart/form-data";
  // 
  let token = sessionStorage.getItem("token");

  if (token) {
    //将token放到请求头发送给服务器,将tokenkey放在请求头中
    config.headers["token"] = token;
  }
  return config;
}, function (error) {
  // Do something with request error
  return Promise.reject(error);
});

// 拦截器
axios.interceptors.response.use(
  res => {
    return Promise.resolve(res)
  },
  error => {
    ElementUI.Notification({
      title: '警告',
      message: '出错了~后台小哥哥正在紧急维护中🛠️！',
      type: 'error'
    });
    submitError();
    return Promise.reject(error)
  }
)

// 暴露给外部以便调用，此时所有的api方法整合到了一起形成一个对象命名为apis
const apis = {
  welcome,
  recruitEdit,
  settings,
  account,
  history,
  recruitList,
  recruitContent,
  messages,
  team,
  teams,
}
export default apis
