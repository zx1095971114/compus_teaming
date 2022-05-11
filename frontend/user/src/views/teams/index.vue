<template>
  <div id="teams">
    <div id="bg">
      <div class="bg-main">
        <img src="../../../static/images/teams/team.png" alt="" />
        <h1 id="title"><span>团队</span> 合作</h1>
        <p class="subtitle">于团队中成长，于合作中进步</p>
      </div>
    </div>
    <div id="container">
      <div id="list">
        <transition-group>
          <item v-for="el in arr" :key="el.tid" :el="el"></item>
        </transition-group>
      </div>
    </div>
  </div>
</template>


<script>
import item from "./item.vue";
export default {
  name: "teams",
  components: {
    item,
  },
  created() {
    this.apis.teams
      .getTeamList(sessionStorage.getItem("username"))
      .then((res) => {
        console.log(res);
        if (res.data.status == 200) {
          this.arr = res.data.result;
        }
      });
  },
  data() {
    return {
      arr: [],
    };
  },
};
</script>

<style scoped>
#teams {
  width: 100%;

  box-sizing: border-box;
  position: relative;

  box-sizing: border-box;
  padding-top: 380px;
}
#bg {
  position: absolute;
  top: 0;
  left: 0;
  border-bottom-left-radius: 8px;
  overflow: hidden;
  width: 100%;
  height: 450px;
  background: linear-gradient(-140deg, #222222 15%, #373737 70%, #3c4859 94%);
  display: flex;
  justify-content: center;
}
#bg #title {
  margin-bottom: 10px;
}
.bg-main {
  margin-top: 70px;
  display: flex;
  flex-direction: column;
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
}
#container {
  margin: 0 auto;
  position: relative;
  z-index: 5;
  width: 95%;
}
h3 {
  margin: 5px;
}
#list {
  margin-left: 3%;
  width: 95%;
  overflow: hidden;
  margin-top: 20px;
  margin-bottom: 40px;

  border-radius: 20px;
  background: #fafafa;
  box-shadow: 0 0 50px 0 rgb(0 0 0 / 15%);
}
</style>