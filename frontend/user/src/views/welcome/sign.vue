<template>
  <div id="sign">
    <div ref="mainbox" class="mainbox">
      <!-- 注册 -->
      <div class="signup animate__animated animate__fadeIn" v-show="!isLogin">
        <h1>注册账号</h1>
        <form action="" autocomplete="off">
          <input type="text" placeholder="账号" v-model="username" />
          <input type="email" placeholder="电子邮箱" v-model="email" />
          <input type="password" placeholder="密码" v-model="password" />
          <input
            type="password"
            placeholder="确认密码"
            v-model="confirmPassword"
          />
          <div class="button submit" @click="next">Create Account</div>
        </form>
      </div>
      <!-- 登录 -->
      <div class="signin animate__animated animate__fadeIn" v-show="isLogin">
        <h1>登录系统</h1>
        <form class="more-padding" action="" autocomplete="off">
          <input type="text" placeholder="账号" v-model="username" />
          <input type="password" placeholder="密码" v-model="password" />
          <div class="checkbox">
            <input type="checkbox" id="remember" v-model="rememberme" /><label
              for="remember"
              >Remember Me</label
            >
          </div>
          <div class="button submit" @click="submitLogin">Login</div>
        </form>
      </div>
    </div>
    <!-- 背景盒子 -->
    <div class="leftbox">
      <h2 class="title"><span>A12赛道题目</span><br />教考分离系统</h2>
      <p class="desc">天津大学<span>高级CV攻城狮团队</span></p>
      <img
        class="picture smaller"
        src="../../../static/images/welcome/logo.jpg"
        alt=""
      />
      <p class="account">已拥有账号？</p>
      <div class="button" id="signin" @click="login">Login</div>
    </div>

    <div class="rightbox">
      <h2 class="title"><span>A12赛道题目</span><br />教考分离系统</h2>
      <p class="desc">天津大学<span>高级CV攻城狮团队</span></p>
      <img
        class="picture"
        src="../../../static/images/welcome/logo.jpg"
        alt=""
      />
      <p class="account">还未拥有账号?</p>
      <div class="button" id="signup" @click="register">Sign Up</div>
    </div>
  </div>
</template>



<script>
export default {
  name: "sign",
  data() {
    return {
      mainbox: null,
      isLogin: true,
      rememberme: false,
      username: "",
      password: "",
      confirmPassword: "",
      email: "",
    };
  },
  mounted() {
    this.mainbox = this.$refs.mainbox;
    if (
      localStorage.getItem("username") != null &&
      localStorage.getItem("password") != null
    ) {
      this.username = localStorage.getItem("username");
      this.password = localStorage.getItem("password");
      this.rememberme = true;
    }
  },
  methods: {
    submitLogin() {
      this.apis.welcome.login(this.username, this.password).then((res) => {
        if (res.data.status === 200) {
          if (this.rememberme) {
            localStorage.setItem("username", this.username);
            localStorage.setItem("password", this.password);
            sessionStorage.setItem("username",this.username);
          } else {
            localStorage.removeItem("username");
            localStorage.removeItem("password");
            sessionStorage.setItem("username",this.username);
          }
          this.$router.push({name: 'homepage'})
        }
      });
    },
    clearInput() {
      this.username = "";
      this.password = "";
      this.confirmPassword = "";
      this.email = "";
    },
    login() {
      this.mainbox.style.transform = "translateX(0%)";
      this.clearInput();
      this.isLogin = true;
    },
    register() {
      this.mainbox.style.transform = "translateX(80%)";
      this.clearInput();
      this.isLogin = false;
    },
    next() {
      this.$router.push({
        name: "account",
        params: {
          username: this.username,
          email: this.email,
          password: this.password,
        },
      });
    },
  },
};
</script>


<style scoped>
#sign {
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: #fff;
  width: 650px;
  height: 415px;
  position: absolute;
  border-radius: 5px;
  box-shadow: 0px 0px 20px 0px rgba(175 187 204 / 20%);
}

.mainbox {
  position: absolute;
  top: -10%;
  left: 5%;
  background: #ff6b6b;
  width: 320px;
  height: 500px;
  border-radius: 5px;
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
  transition: all 0.5s ease-in-out;
  z-index: 2;
}

.leftbox,
.rightbox {
  position: absolute;
  width: 50%;
  transition: 1s all ease;
}
.leftbox {
  left: -2%;
}
.rightbox {
  right: -2%;
}
/* 字体、按钮、图片样式 */
h1 {
  font-family: "Open Sans", sans-serif;
  text-align: center;
  margin-top: 95px;
  text-transform: uppercase;
  color: #fff;
  font-size: 2em;
  letter-spacing: 8px;
}
.title {
  font-family: "Lora", serif;
  color: #8e9aaf;
  font-size: 1.8em;
  line-height: 1.1em;
  letter-spacing: 3px;
  text-align: center;
  font-weight: 300;
  margin-top: 20%;
}
.desc {
  margin-top: -8px;
}
.account {
  margin-top: 50%;
  font-size: 10px;
}
p {
  font-family: "Open Sans", sans-serif;
  font-size: 0.7em;
  letter-spacing: 2px;
  color: #8e9aaf;
  text-align: center;
}
span {
  color: #ea8685;
}
.picture {
  position: absolute;
  width: 130px;
  height: 130px;
  top: 45%;
  left: 30%;
  opacity: 0.8;
  border-radius: 1rem;
}
.smaller {
  width: 130px;
  height: 130px;
  top: 48%;
  left: 30%;
  opacity: 0.9;
}

.button:hover {
  background: #ff6b6b;
  color: #fff;
  transition: background-color 0.2s ease-out;
}
.button {
  margin-top: 3%;
  background: #fff;
  color: #706e6e;
  border: solid 1px #ff6b6b;
  padding: 12px;
  font-family: "Open Sans", sans-serif;
  text-transform: uppercase;
  letter-spacing: 3px;
  font-size: 11px;
  border-radius: 10px;
  margin: auto;
  outline: none;
  display: block;
}
/* 表单样式 */
form {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 7px;
}
.more-padding {
  padding-top: 35px;
}
.more-padding input {
  padding: 12px;
}
.more-padding .submit {
  margin-top: 45px;
}
.submit {
  margin-top: 25px;
  padding: 12px;
  border-color: #ff6b6b;
}
.submit:hover {
  /* background: #cbc0d3; */
  border-color: #fff;
}
input {
  transition: 0.3s;
  background-color: #ff6b6b;
  width: 65%;
  color: rgb(240, 239, 239);
  border: none;
  border-bottom: 1px solid rgba(246, 246, 246, 0.7);
  padding: 9px;
  font-weight: 100;
}
input::placeholder {
  color: #fff;
  letter-spacing: 2px;
  font-size: 1em;
  font-weight: 100;
}
input:focus {
  color: #fff;
  outline: none;
  border-bottom: 2px solid #fff;
  font-size: 1em;
  transition: 0.2s all ease;
}
input:focus::placeholder {
  opacity: 0;
}
label {
  font-family: "Open Sans", sans-serif;
  color: #fff;
  font-size: 0.8em;
  letter-spacing: 1px;
}
.checkbox {
  position: relative;
  display: inline;
  white-space: nowrap;
  left: -52px;
  top: 25px;
}
input[type="checkbox"] {
  width: 15px;
  background: #ff6b6b;
}
.signup,
.signin {
  transition: all 0.5s ease;
}
#signup,
#signin {
  width: 30% !important;
  text-align: center;
}
</style>