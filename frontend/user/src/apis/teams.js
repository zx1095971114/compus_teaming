import axios from 'axios'

export const getTeamList = (username) => {
  let fd = new FormData();
  fd.append("username", username);
  return axios.post("/team/api/getUserTeamInfo", fd);
}
