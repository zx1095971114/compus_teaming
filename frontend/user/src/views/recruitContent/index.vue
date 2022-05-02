<template>
  <div id="recruitContent" class="animate__animated animate__fadeIn">
    <div id="head">
      <div class="main">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ name: 'exam' }"
            >招募列表</el-breadcrumb-item
          >
          <el-breadcrumb-item>学习板块</el-breadcrumb-item>
        </el-breadcrumb>
        <h2>
          期末大作业组队<span class="match-status $statusSmCls">招募中</span>
        </h2>
        <p class="item">招募时间范围： 2022-04-05 至 2022-06-07</p>
        <p class="item">招募发起人：郎文翀</p>
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
          <flip-countdown deadline="2023-12-25 00:00:00"></flip-countdown>
        </div>
      </div>
      <div class="tips">
        <h2>●招募内容：</h2>
        <div class="ql-container ql-snow">
          <div class="content ql-editor" v-html="content" />
        </div>
      </div>
      <div class="footer">
        <div class="person">
          <h2>团队成员:</h2>
          <div class="avatar">
            <img
              src="https://s3.bmp.ovh/imgs/2022/01/a714525bf61d4a6a.png"
              alt
            />
          </div>
          <div class="avatar">
            <img
              src="https://s3.bmp.ovh/imgs/2022/01/a714525bf61d4a6a.png"
              alt
            />
          </div>
          <div class="avatar">
            <img
              src="https://s3.bmp.ovh/imgs/2022/01/a714525bf61d4a6a.png"
              alt
            />
          </div>
          <div class="avatar">
            <img
              src="https://s3.bmp.ovh/imgs/2022/01/a714525bf61d4a6a.png"
              alt
            />
          </div>
          <div class="avatar">
            <img
              src="https://tse1-mm.cn.bing.net/th/id/OIP-C.StJ-mvGD2FVWW9WaleP5SQHaHZ?w=224&h=220&c=7&r=0&o=5&dpr=1.25&pid=1.7"
              alt
            />
          </div>
          <span class="more">+63 Teammates</span>
        </div>
        <div class="create" @click="create">
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
export default {
  name: "recruitContent",
  components: { FlipCountdown },
  methods: {
    backup() {
      this.$router.push({ name: "recruitList" });
    },
  },
  data() {
    return {
      content: `
          <h1 class="ql-align-center"><span class="ql-font-serif" style="background-color: rgb(240, 102, 102); color: rgb(255, 255, 255);"> I am snow example! </span></h1><p><br></p><p><span class="ql-font-serif">W Can a man still be brave if he's afraid? That is the only time a man can be brave. </span></p><p><br></p><p><strong class="ql-font-serif ql-size-large">Courage and folly is </strong><strong class="ql-font-serif ql-size-large" style="color: rgb(230, 0, 0);">always</strong><strong class="ql-font-serif ql-size-large"> just a fine line.</strong></p><p><br></p><p><u class="ql-font-serif">There is only one God, and his name is Death. And there is only one thing we say to Death: "Not today."</u></p><p><br></p><p><em class="ql-font-serif">Fear cuts deeper than swords.</em></p><p><br></p><pre class="ql-syntax" spellcheck="false"><span class="hljs-keyword">const</span> a = <span class="hljs-number">10</span>;
         
        `,
      exam: {
        id: 0,
        title: "精英班选拔考试",
        startTime: "2022-02-12 09:30",
        endTime: "2022-02-21 09:30",
        subject: "高等数学(2A)",
        duration: "3h",
      },
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