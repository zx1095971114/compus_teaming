<template>
  <div id="teamZone">
    <div id="bg">
      <div class="bg-main">
        <img src="../../../static/images/teamZone/bg1.png" alt="" />
        <h1 id="title"><span>互助</span> 齐心</h1>
        <p class="subtitle">让我们相互帮助，齐心协力完成任务</p>
        <img src="../../../static/images/teamZone/bg2.png" alt="" />
      </div>
    </div>
    <div class="container">
      <div class="header">
        <strong>Members:</strong>
      </div>
      <div
        class="item animate__animated animate__fadeIn"
        v-for="(item, i) in arr"
        :key="i"
      >
        <div class="person">
          <div class="avatar">
            <img :src="item.avatarPath" alt />
          </div>
          <span class="more">{{ item.name }}</span>
        </div>
        <div class="other-info">
          <p>
            <b><i class="fad fa-venus-mars"></i></b>{{ item.sex }}
          </p>
          <p>
            <b><i class="bx bxs-graduation"></i></b>{{ item.grade }}
          </p>
          <p>
            <b><i class="fad fa-mobile"></i></b>{{ item.phoneNumber }}
          </p>
          <p>
            <b><i class="fad fa-house"></i></b>{{ item.school }}
          </p>
          <p>
            <b><i class="fad fa-envelope"></i></b>{{ item.email }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "teamZone",
  created() {
    this.apis.team.getTeamInfo(sessionStorage.getItem("tid")).then((res) => {
      var result = res.data.result;
      this.arr = result;
      // console.log(result);
      for(var i=0;i<this.arr.length;i++){
        this.arr[i].avatarPath = "http://localhost:8088/images/" + this.arr[i].avatarPath;
      }
    })
  },
  data() {
    return {
      arr: [],
    };
  },
};
</script>

<style scoped>
#teamZone {
  width: 100%;
  min-height: 100vh;
  box-sizing: border-box;
  padding-top: 60px;
}
#bg {
  position: absolute;
  top: 0;
  left: 0;
  border-bottom-left-radius: 8px;
  overflow: hidden;
  width: 100%;
  height: 250px;

  display: flex;
  justify-content: center;
}
#bg #title {
  margin-bottom: 10px;
}
.bg-main {
  margin-top: 20px;
  display: flex;

  align-items: center;
  width: 80%;
  min-height: 200px;
}
.bg-main h1 {
  font-size: 36px;
  color: #b3b3b3;
  font-weight: 100;
  font-family: hytmr;
}
.bg-main span {
  color: #fff;
  font-weight: 500;
}
.subtitle {
  margin-top: 8px;
  color: grey;
  font-size: 18px;
  letter-spacing: 2px;
  margin-right: 120px;
}
.container {
  box-shadow: 0 0 50px 0 rgb(0 0 0 / 15%);
  border-radius: 20px;
  overflow: hidden;
  top: 110px;
  background: #fff;
  position: relative;
  z-index: 5;
  border: 1px solid #d0d7de;
  border-radius: 8px;
  width: 90%;
  margin: 0px auto;
}
.header {
  height: 60px;
  width: 100%;
  background: #f6f8fa;
  box-sizing: border-box;
  padding: 0px 30px;
  align-items: center;
  display: flex;
}
.header strong {
  font-family: "SentyGoldenBell";
}
.item {
  border-top: 1px solid #d0d7de;
  height: 90px;

  display: flex;
  align-items: center;
  justify-content: space-between;
}
.person {
  margin-left: 30px;
  min-width: 200px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.person .avatar {
  width: 62px;
  height: 62px;
  margin-left: 5px;
  border-radius: 50%;
  border: 1px solid var(--text7);
}
.person .avatar img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
.person span {
  font-size: 18px;
  font-family: "SimHei";
  margin-left: 8px;
  color: #58a6ff;
  font-weight: 700;
}
.item p {
  color: grey;
  font-size: 16px;
  font-weight: 400;
  display: flex;
  align-items: center;
}
.item b {
  font-weight: 700;
  color: #222;
}
.other-info {
  width: 60%;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.other-info p {
  min-width: 80px;
  
}
</style>