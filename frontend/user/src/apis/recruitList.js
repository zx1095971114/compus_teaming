import axios from 'axios'

export const getScreenRecruitInfo = () => {
    return axios.post('/recruit/api/getScreenRecruitInfo');
}

export const watchRecruit = (username,rid) => {
    let fd = new FormData();
    fd.append("username",username);
    fd.append("rid",rid);
    return axios.post('/recruit/api/watchRecruit',fd);
}