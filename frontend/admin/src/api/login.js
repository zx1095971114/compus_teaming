import axios from 'axios'

// 默认接口头部公有的ip地址
// 因此使用本地回环地址127.0.0.1同时默认是80端口
// axios.defaults.baseURL = 'http://1.117.169.85:8081';
axios.defaults.baseURL = '/apis';
// axios.defaults.baseURL = 'http://api.cheeseburgerim.space';


export const login = (username, password) => {
  let fd = new FormData();
  fd.append("username", username);
  fd.append("password", password);
  return axios.post('/user/api/login', fd);
}
