// 读取所欲模块内部定义的请求方法
import axios from 'axios'
import * as login from './login.js'
import ElementUI from 'element-ui';


axios.defaults.timeout = 30000;

// http request拦截器 添加一个请求拦截器
axios.interceptors.request.use(function (config) {

  config.headers['Content-Type'] = "multipart/form-data"
  // 
  let token = sessionStorage.getItem("token");

  if (token) {
    //将token放到请求头发送给服务器,将tokenkey放在请求头中
    config.headers.token = token;
  }
  return config;
}, function (error) {
  // Do something with request error
  return Promise.reject(error);
});

axios.interceptors.response.use(
  res => {
    return Promise.resolve(res)
  },
  error => {
    ElementUI.Notification({
      title: '警告',
      message: '服务器崩溃了~后台小哥哥正在紧急修复中🛠️！',
      type: 'error'
    });
    return Promise.reject(error)
  }
)

// 暴露给外部以便调用，此时所有的api方法整合到了一起形成一个对象命名为apis
const apis = {
  login
}
export default apis
