import axios from 'axios'

export const getuserinfo = (username) => {
    let fd = new FormData();
    fd.append("username",username);
    return axios.post('/user/api/getUserInfo',fd);
}

export const edituserinfo = (username,name,sex,email,school) => {
    let fd = new FormData();
    fd.append("username",username);
    fd.append("name",name);
    fd.append("sex",sex);
    fd.append("email",email);
    fd.append("school",school);
    return axios.post('/user/api/editUserInfo',fd);
}