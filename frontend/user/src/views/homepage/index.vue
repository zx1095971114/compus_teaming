<template>
  <div id="homepage">
    <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible"
      width="30%"
      center
    >
      <span>如果要退出,当前未保存的操作将无法保留,您确定要登出账号吗❓</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="logout">确 定</el-button>
      </span>
    </el-dialog>
    <div class="sidebar animate__animated animate__fadeInLeft">
      <div class="logo_content">
        <div class="logo">
          <i class="bx bx-message-edit"></i>
          <div class="logo_name">北洋有约</div>
        </div>
        <i class="bx bx-menu" id="btn"></i>
      </div>
      <ul class="nav_list">
        <li>
          <i class="bx bx-search"></i>
          <input type="text" placeholder="Search..." />
          <span class="tooltip">搜索</span>
        </li>
        <li @click="transmit(1)">
          <a href="#">
            <i class="bx bx-grid-alt"></i>
            <span class="links_name">论坛主页</span>
          </a>
          <span class="tooltip">主页</span>
        </li>
        <li @click="transmit(2)">
          <a href="#">
            <i class="bx bx-user"></i>
            <span class="links_name">个人账号</span>
          </a>
          <span class="tooltip">账号</span>
        </li>
        <li @click="transmit(3)">
          <a href="#">
            <i class="bx bx-chat"></i>
            <span class="links_name">我的消息</span>
          </a>
          <span class="tooltip">消息</span>
        </li>
        <li @click="transmit(4)">
          <a href="#">
            <i class="bx bx-pie-chart-alt-2"></i>
            <span class="links_name">团队管理</span>
          </a>
          <span class="tooltip">团队</span>
        </li>
        <li @click="transmit(5)">
          <a href="#">
            <i class="bx bx-pen"></i>
            <span class="links_name">组对招募</span>
          </a>
          <span class="tooltip">组队</span>
        </li>
        <li @click="transmit(6)">
          <a href="#">
            <i class='bx bx-history'></i>
            <span class="links_name">个人日志</span>
          </a>
          <span class="tooltip">日志</span>
        </li>
        <li @click="transmit(7)">
          <a href="#">
            <i class="bx bx-comment-dots"></i>
            <span class="links_name">团队会议</span>
          </a>
          <span class="tooltip">会议</span>
        </li>
        <li @click="transmit(8)">
          <a href="#">
            <i class="bx bx-cog"></i>
            <span class="links_name">系统设置</span>
          </a>
          <span class="tooltip">设置</span>
        </li>
      </ul>
      <div class="profile_content">
        <div class="profile">
          <div class="profile_details">
            <img
              src="https://s3.bmp.ovh/imgs/2022/01/a714525bf61d4a6a.png"
              alt
            />
            <div class="name_job">
              <div class="name">Langwenchong</div>
              <div class="job">Student</div>
            </div>
          </div>
          <i
            class="bx bx-log-out"
            id="log_out"
            @click="centerDialogVisible = true"
          ></i>
        </div>
      </div>
    </div>
    <div id="r-main">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "homepage",
  data() {
    return {
      centerDialogVisible: false,
    };
  },
  mounted() {
    let btn = document.querySelector("#btn");
    let sidebar = document.querySelector(".sidebar");
    let searchBtn = document.querySelector(".bx-search");
    let profile = document.querySelector(".profile_details");

    btn.onclick = function () {
      sidebar.classList.toggle("active");
      // profile.classList.toggle("active");
      if (btn.classList.contains("bx-menu")) {
        btn.classList.replace("bx-menu", "bx-menu-alt-right");
      } else {
        btn.classList.replace("bx-menu-alt-right", "bx-menu");
      }
    };
    searchBtn.onclick = function () {
      sidebar.classList.toggle("active");
    };
    var date = new Date();
    var s = "日一二三四五六";
    const h = this.$createElement;
    this.$notify({
      title: "登陆成功✔️",
      message: h(
        "b",
        { style: "color: #333" },
        "您好Langwenchong同学，欢迎登陆「北洋有约」校内组队系统，现在是北京时间" +
          date.getHours() +
          ":" +
          date.getMinutes() +
          " 星期" +
          s[date.getDay()] +
          "。"
      ),
    });
  },
  methods: {
    logout() {
      this.$router.push({ name: "sign" });
    },
    transmit(i) {
      if (i === 1) {
        this.$router.push({ name: "homepage" });
      } else if (i === 2) {
        this.$router.push({ name: "myaccount" });
      } else if (i === 3) {
        this.$router.push({ name: "message" });
      } else if (i === 5) {
        this.$router.push({ name: "recruitEdit" });
      } else if (i === 6) {
        this.$router.push({ name: "history" });
      } else if (i === 7) {
        this.$router.push({ name: "session" });
      }
    },
  },
};
</script>

<style scoped src="../../assets/css/bootstrap.min.css"></style>
<style scoped>
.sidebar > * {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}
#homepage {
  width: 100%;
}
.sidebar {
  position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  width: 78px;
  background: #11101d;
  padding: 6px 14px;
  z-index: 99;
  transition: all 0.5s ease;
}
.sidebar.active {
  width: 240px;
}
.sidebar .logo_content .logo {
  color: #fff;
  display: flex;
  height: 50px;
  width: 100%;
  align-items: center;
  opacity: 0;
  pointer-events: none;
  transition: all 0.5s ease;
}
.sidebar.active .logo_content .logo {
  opacity: 1;
  pointer-events: none;
}
.logo_content .logo i {
  font-size: 28px;
  margin-right: 5px;
}
.logo_content .logo .logo_name {
  font-size: 20px;
  font-weight: 400;
}
.sidebar #btn {
  position: absolute;
  color: #fff;
  top: 6px;
  left: 50%;
  font-size: 22px;
  height: 50px;
  width: 50px;
  text-align: center;
  line-height: 50px;
  transform: translateX(-50%);
}
.sidebar.active #btn {
  left: 90%;
}
.sidebar ul {
  margin-top: 20px;
}
.sidebar ul li {
  position: relative;
  height: 50px;
  width: 100%;
  margin: 0 5px;
  list-style: none;
  line-height: 50px;
  margin: 5px 0;
}
.sidebar ul li .tooltip {
  position: absolute;
  left: 125px;
  top: 0;
  transform: translate(-50%, -50%);
  border-radius: 6px;
  height: 35px;
  width: 120px;
  background: #fff;
  line-height: 35px;
  text-align: center;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
  transition: 0s;
  opacity: 0;
  pointer-events: none;
  display: block;
}
.sidebar.active ul li .tooltip {
  display: none;
}
.sidebar ul li:hover .tooltip {
  transition: all 0.5s ease;
  opacity: 1;
  top: 50%;
}
.sidebar ul li input {
  position: absolute;
  height: 100%;
  width: 100%;
  left: 0;
  top: 0;
  border-radius: 12px;
  outline: none;
  border: none;
  background: #1d1b31;
  padding-left: 50px;
  font-size: 18px;
  color: #fff;
}
.sidebar ul li .bx-search {
  position: absolute;
  z-index: 99;
  color: #fff;
  font-size: 22px;
  transition: all 0.5 ease;
}
.sidebar ul li .bx-search:hover {
  background: #fff;
  color: #1d1b31;
}
.sidebar ul li a {
  color: #fff;
  display: flex;
  align-items: center;
  text-decoration: none;
  border-radius: 12px;
  white-space: nowrap;
  transition: all 0.4s ease;
}
.sidebar ul li a:hover {
  color: #11101d;
  background: #fff;
}
.sidebar ul li i {
  font-size: 18px;
  font-weight: 400;
  height: 50px;
  min-width: 50px;
  border-radius: 12px;
  line-height: 50px;
  text-align: center;
}
.sidebar .links_name {
  font-size: 15px;
  font-weight: 400;
  opacity: 0;
  pointer-events: none;
  transition: all 0.3s ease;
}
.sidebar.active .links_name {
  transition: 0s;
  opacity: 1;
  pointer-events: auto;
}
.sidebar .profile_content {
  position: absolute;
  color: #fff;
  bottom: 0;
  left: 0;
  width: 100%;
}
.sidebar .profile_content .profile {
  position: relative;
  padding: 10px 6px;
  height: 60px;
  background: none;
  transition: all 0.4s ease;
}
.sidebar.active .profile_content .profile {
  background: #1d1b31;
}
.profile_content .profile .profile_details {
  display: flex;
  align-items: center;
  pointer-events: none;
  white-space: nowrap;
  opacity: 0;
  transition: all 0.4s ease;
}
.sidebar.active .profile .profile_details {
  opacity: 1;
  pointer-events: auto;
}
.profile .profile_details img {
  height: 45px;
  width: 45px;
  object-fit: cover;
  border-radius: 12px;
}
.profile .profile_details .name_job {
  margin-left: 10px;
}
.profile .profile_details .name {
  font-size: 15px;
  font-weight: 400;
}
.profile .profile_details .job {
  font-size: 12px;
}
.profile #log_out {
  position: absolute;
  bottom: 5px;
  left: 50%;
  transform: translateX(-50%);
  min-width: 50px;
  line-height: 50px;
  font-size: 20px;
  border-radius: 12px;
  text-align: center;
  transition: all 0.4s ease;
  background: #1d1b31;
  cursor: pointer;
}
.sidebar.active .profile #log_out {
  left: 88%;
}
.sidebar.active .profile #log_out {
  background: none;
}
.home_content {
  position: absolute;
  height: 100%;
  width: calc(100% - 78px);
  left: 78px;
  background: #e4e9f7;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2px);
  transition: all 0.5s ease;
}
.sidebar.active ~ .home_content {
  z-index: 100;
}
.home_content .text {
  font-size: 25px;
  font-weight: 500;
  color: #1d1b31;
  margin: 12px;
}
.sidebar.active ~ .home_content {
  width: calc(100% - 240px);
  left: 240px;
}
#r-main {
  width: calc(100% - 78px);
  margin-left: 78px;
  min-height: 300px;
  /* background: #f4f4f4; */
  /* background:#000; */
}
</style>
