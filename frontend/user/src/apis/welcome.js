import axios from 'axios'


export const login = (username, password) => {
  let fd = new FormData();
  fd.append("username", username);
  fd.append("password", password);
  return axios.post("/user/api/login", fd);
}
