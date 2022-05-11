import axios from 'axios'

export const getAllMessage = (username) => {
    let fd = new FormData();
    fd.append("username",username);
    return axios.post('/message/api/getAllMessage',fd);
}

export const setSingleMessageStatus = (mid) => {
    let fd = new FormData();
    fd.append("mid",mid);
    return axios.post('/message/api/setSingleMessageStatus',fd);
}

export const setAllMessageStatus = (username) => {
    let fd = new FormData();
    fd.append("username",username);
    return axios.post('/message/api/setAllMessageStatus',fd);
}