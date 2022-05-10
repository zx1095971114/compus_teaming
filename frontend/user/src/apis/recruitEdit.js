import axios from 'axios'

export const uploadImg = (img) => {
  let fd = new FormData();
  fd.append("img", img);
  return axios.post('/recruit/api/uploadImg', fd);
}

export const createRecruitWithImg = (username, rtitle, description, maxMates, endTime, rclass, content, img, rtags) => {
  let fd = new FormData();
  fd.append("username", username);
  fd.append("rtitle", rtitle);
  fd.append("description", description);
  fd.append("maxMates", maxMates);
  fd.append("endTime", endTime);
  fd.append("rclass", rclass);
  fd.append("content", content);
  fd.append("img", img);
  var tags = rtags[0];
  for (let i = 1; i < rtags.length; i++) {
    tags = tags + "-" + rtags[i]
  }
  fd.append("rtags", tags);
  return axios.post("/recruit/api/createWithImg", fd);
}


export const createRecruitWithoutImg = (username, rtitle, description, maxMates, endTime, rclass, content, rtags) => {
  let fd = new FormData();
  fd.append("username", username);
  fd.append("rtitle", rtitle);
  fd.append("description", description);
  fd.append("maxMates", maxMates);
  fd.append("endTime", endTime);
  fd.append("rclass", rclass);
  fd.append("content", content);
  var tags = rtags[0];
  for (let i = 1; i < rtags.length; i++) {
    tags = tags + "-" + rtags[i]
  }
  fd.append("rtags", tags);
  return axios.post("/recruit/api/createWithoutImg", fd);
}
