import axios from 'axios'

export const getTeamInfo = (tid) => {
    let fd = new FormData();
    fd.append("tid",tid);
    return axios.post("/team/api/getTeamInfo",fd);
}