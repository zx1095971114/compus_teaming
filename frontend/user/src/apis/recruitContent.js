import axios from 'axios'
import {
  Form
} from 'element-ui';

export const getRecruitInfo = (username, rid) => {
  let fd = new FormData();
  fd.append("username", username);
  fd.append("rid", rid);
  return axios.post("/recruit/api/getRecruitInfo", fd);
}

export const joinRecruit = (username, rid) => {
  let fd = new FormData();
  fd.append("username", username);
  fd.append("rid", rid);
  return axios.post("/recruit/api/joinRecruit", fd);
}
