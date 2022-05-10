import axios from 'axios'

export const getScreenRecruitInfo = () => {
    return axios.post('/recruit/api/getScreenRecruitInfo');
}