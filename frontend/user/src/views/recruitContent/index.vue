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
        <p class="item">
          招募时间范围： 至 
        </p>
        <p class="item">考试学科：{{ exam.subject }}</p>
        <p class="item">考试时长：{{ exam.duration }}</p>
      </div>
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
        <h2>●官方系统提示：</h2>
        <ol>
          <li>
            为了保证页面加载显示正常,请优先选择使用chrome、edge、firefrox浏览器进行考试
          </li>
          <li>
            为了防止作弊，在考试前以及考试中会进行人脸识别认证，系统将会为您提供5min的时间进行验证，验证失败本次考试将会暂停，请确保您的浏览器可以正常使用摄像头进行人脸识别同时在考试途中请不要离开座位或者刷新页面
          </li>
          <li>
            如果处于高峰期间进入考试后可能需要1-2min时间进行页面加载，请您见谅稍加等待，如长时间仍然没能进入请迅速联系管理员
          </li>
        </ol>
      </div>
      <div class="confirm">
        <confirm></confirm>
      </div>
    </div>
  </div>
</template>


<script>
import FlipCountdown from "vue2-flip-countdown";
export default {
  name: "recruitContent",
  components: { FlipCountdown },
  methods: {
    backup() {
      this.$router.push({ name: "exam" });
    },
  },
  data() {
    return {
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
  /* background: #000; */
  position: relative;
  background: #fff;
  box-shadow: 0px 1px 2px rgb(33 40 53 / 10%), 0px 2px 8px rgba(33 40 53 / 8%);
}
#body {
  width: 100%;
  /* background: pink; */
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
  background: #eee;
  width: 100%;
  /* min-height: 200px; */
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
  background-image: url("../../../static/images/recruitContent/bg.png");
  background-color: rgb(7, 10, 15);
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
}
.main {
  width: 60%;
  height: 80%;
  /* background: pink; */
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
</style>