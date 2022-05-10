import axios from 'axios'


export const login = (username, password) => {
  let fd = new FormData();
  fd.append("username", username);
  fd.append("password", password);
  return axios.post("/user/api/login", fd);
}

export const register = (ruleForm,avator,username,email,password) => {
  let fd = new FormData();
  fd.append("name",ruleForm.name);
  fd.append("phoneNumber",ruleForm.phoneNumber);
  fd.append("school",ruleForm.school);
  fd.append("sex",ruleForm.sex);
  fd.append("avator",avator);
  fd.append("username",username);
  fd.append("email",email);
  fd.append("password",password);
  return axios.post('user/api/register',fd);
}