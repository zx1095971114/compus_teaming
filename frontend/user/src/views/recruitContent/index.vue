<template>
  <div id="recruitContent" class="animate__animated animate__fadeIn">
    <Vcode
      sliderText="拖动滑块完成试卷提交"
      :show="isShow"
      @success="success"
      @close="close"
    ></Vcode>
    <div id="head">
      <div class="main">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ name: 'recruitList' }"
            >招募列表</el-breadcrumb-item
          >
          <el-breadcrumb-item>{{ recruitcontent.rclass }}</el-breadcrumb-item>
        </el-breadcrumb>
        <h2>
          {{ recruitcontent.rtitle }}
          <span class="match-status $statusSmCls">招募中</span>
        </h2>
        <p class="item">
          招募时间范围： {{ recruitcontent.startTime }} 至
          {{ recruitcontent.endTime }}
        </p>
        <p class="item">招募发起人：{{ recruitcontent.creator }}</p>
        <p class="item"></p>
      </div>
      <h2>欢迎加入团队，与我们一同合作完成任务！</h2>
      <img
        height="90%"
        src="../../../static/images/recruitContent/team.png"
        alt=""
      />
    </div>
    <div id="body">
      <div class="menu">
        <h2 class="red" @click="backup">
          <i class="fad fa-arrow-circle-left"></i> 返回列表
        </h2>
        <div id="clock">
          <h2>距离停止招募还有:</h2>
          <flip-countdown :deadline="recruitcontent.endTime"></flip-countdown>
        </div>
      </div>
      <div class="tips">
        <h2>●招募内容：</h2>
        <div class="ql-container ql-snow">
          <div class="content ql-editor" v-html="recruitcontent.content" />
        </div>
      </div>
      <div class="footer">
        <div class="person">
          <h2>团队成员:</h2>
          <div
            class="avatar"
            v-for="(showAvatar, index) in showAvatars"
            :key="index"
          >
            <img :src="showAvatar" alt />
          </div>

          <span class="more">{{ totalnum }} Teammates</span>
        </div>
        <div v-if="!recruitcontent.flag" class="create" @click="isShow = true">
          <strong>+&nbsp;&nbsp;加入团队</strong>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import FlipCountdown from "vue2-flip-countdown";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import Vcode from "vue-puzzle-vcode";
export default {
  name: "recruitContent",
  components: { FlipCountdown, Vcode },
  created() {
    this.apis.recruitContent
      .getRecruitInfo(
        sessionStorage.getItem("username"),
        sessionStorage.getItem("rid")
      )
      .then((res) => {
        // console.log(res.data.result);
        var result = res.data.result;
        this.recruitcontent = result;
        var i;
        var myavatar = [];
        for (i = 0; i < result.avatorPath.length; i++) {
          let avatar =
            "https://vteamresources.coolchong.cn/" + result.avatorPath[i];
          myavatar[i] = avatar;
        }

        var j = myavatar.length;
        if (j <= 5) {
          this.showAvatars = myavatar;
        } else {
          for (var k = 0; k < 5; k++) {
            this.showAvatars[k] = myavatar[k];
          }
        }

        this.totalnum = j;
        // console.log(this.recruitcontent);

        this.recruitcontent.avatorPath = myavatar;
      });
  },
  methods: {
    close() {
      this.isShow = false;
    },
    success(msg) {
      this.isShow = false;
      this.apis.recruitContent
        .joinRecruit(
          sessionStorage.getItem("username"),
          sessionStorage.getItem("rid")
        )
        .then((res) => {
          // console.log(res);
          if (res.data.status == 200) {
            this.recruitcontent.flag = 1;
            this.$notify({
              title: "成功",
              message: "您已成功加入该招募团队！",
              type: "success",
            });
          } else {
            this.$notify({
              title: "失败",
              message: "加入失败，该招募已过期或者您已在该招募团队中！",
              type: "warning",
            });
          }
        });
    },
    backup() {
      this.$router.push({ name: "recruitList" });
    },
  },
  data() {
    return {
      isShow: false,
      recruitcontent: {
        avatorPath: [],
        content: "",
        creator: "",
        endTime: "2022-01-01 00:00:00",
        flag: 0,
        rclass: "",
        rtitle: "",
        startTime: "",
      },

      showAvatars: [],
      totalnum: 0,
    };
  },
};
</script>

<style scoped>
#recruitContent {
  width: 100%;
  min-height: 100vh;
  position: relative;
  background: #fff;
  box-shadow: 0px 1px 2px rgb(33 40 53 / 10%), 0px 2px 8px rgba(33 40 53 / 8%);
}
#body {
  width: 100%;
  box-sizing: border-box;
  padding: 10px 20px;
}
.menu {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
}
.menu h2 {
  position: relative;
  margin: 0;
  text-align: center;
  padding: 10px;
  border-radius: 8px;
  font-family: "hytmr";
}
.red {
  background: rgba(204, 51, 51, 0.7);
  color: #fff;
  transition: 0.3s;
  width: 170px;
}
.red:hover {
  background: rgba(204, 51, 51, 0.5);
}
#clock {
  display: flex;
  align-items: center;
}
.tips {
  margin-top: 20px;
  background: #eee;
  width: 100%;
  min-height: 200px;
  border-radius: 4px;
  box-sizing: border-box;
  padding: 15px 20px;
}
.tips h2 {
  margin: 0;
  font-family: SentyGoldenBell;
}
.tips ol {
  margin: 0;
  font-family: "hytmr";
}
#head {
  width: 100%;
  height: 220px;
  background: linear-gradient(-140deg, #1b1a1a 15%, #373737 70%, #2c3440 94%);
  background-color: rgb(7, 10, 15);
  box-sizing: border-box;
  display: flex;
  justify-content: space-around;
  align-items: center;
  color: #fff;
}
.main {
  width: 40%;
  height: 80%;
}
#head h2 {
  font-family: "SentyGoldenBell";
}
.match-status {
  font-family: "hytmr";
  position: relative;
  display: inline-block;
  vertical-align: middle;
  background: #cc3333;
  border-color: #cc3333;
  color: #fff;
  font-size: 12px;
  height: 20px;
  line-height: 20px;
  margin: 0 5px;
  padding: 0 5px;
  text-align: center;
}
.match-status:after {
  border-style: solid;
  border-color: inherit;
  border-width: 10px 8px;
  position: absolute;
  content: "";
  bottom: 0;
  right: -10px;
  border-right-color: transparent;
}
.item {
  margin: 3px;
  font-size: 14px;
}
.person {
  width: 100%;
  height: 90px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.person h2 {
  font-family: "SentyGoldenBell";
}
.person .avatar {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  margin-left: 10px;
  border: 1px solid var(--text7);
}
.person .avatar img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
.person span {
  color: #58a6ff;
  font-weight: 400;
}
.more {
  margin-left: 20px;
  font-family: "Dancing Script";
}
.footer {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.create {
  z-index: 5;
  margin-top: 10px;
  width: 40%;
  border: 1px solid rgba(0, 102, 255, 0.5);
  color: rgb(0, 102, 255);
  border-radius: 4px;
  height: 40px;
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: 0.5s;
}
.create:hover {
  color: #fff;
  background: rgba(0, 102, 255, 0.5);
}
</style>