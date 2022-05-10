import axios from 'axios'

export const getsettings = () => {
    return axios.post('/settings/api/getSettings');
}