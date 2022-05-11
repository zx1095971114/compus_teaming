import axios from 'axios'

export const getRecruitInfo = (username,rid) => {
    let fd = new FormData();
    fd.append("username",username);
    fd.append("rid",rid);
    return axios.post("/recruit/api/getRecruitInfo",fd);
}