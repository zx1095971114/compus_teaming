import axios from 'axios'

export const getallhistory = (username) => {
    let fd = new FormData();
    fd.append("username",username);
    return axios.post('/history/api/getAllHistory',fd);
}