import axios from 'axios'

export const uploadImg = (img) => {
  let fd = new FormData();
  fd.append("img", img);
  return axios.post('/recruit/api/uploadImg', fd);
}

export const createRecruit = (username, rtitle, description, maxMates, endTime, rclass, content, img, rtags) => {
  let fd = new FormData();
  fd.append("username", username);
  fd.append("rtitle", rtitle);
  fd.append("description", description);
  fd.append("maxMates", maxMates);
  fd.append("endTime", endTime);
  fd.append("rclass", rclass);
  fd.append("content", content);
  fd.append("img", img);
  for (let i = 0; i < rtags.length; i++) {
    fd.append("rtags", rtags[i])
  }
  return axios.post("/recruit/api/createRecruit", fd);
}
