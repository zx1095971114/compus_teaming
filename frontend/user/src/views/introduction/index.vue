<template>
  <div id="introduction">
    <div class="main">
      <img src="../../../static/images/introduction/icon.png" width="180px" />
      <h2 class="description animate__animated animate__fadeIn">
        「北洋有约」
      </h2>
      <div class="description animate__animated animate__fadeIn">
        「北洋有约」校内组队招募平台是由郎文翀、黄硕、周翔、相里千卉4个tjuer开发的一款面向校园内任务发布、合作组队应用场景的软件！“匿名组队、快速合作”是我们主打的产品理念，在这里您可以深刻的感受到我们平台为您提供的快速招募合作成员的功能便捷性！
      </div>
      <a
        @click="start"
        class="button theme start animate__animated animate__fadeIn"
        href="#"
        >Start Exploring</a
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "introduction",
  methods: {
    start() {
      // this.apis.user.login().then((res) => {
      //   console.log(res);
      // });
      this.$router.push({ name: "sign" });
    },
  },
};
</script>

<style scoped>
#introduction {
  width: 100%;
  height: 100vh;
  background: #313438;
  display: flex;
  justify-content: center;
  align-items: center;
}
.main {
  display: flex;
  flex-direction: column;
  align-items: center;
}
img {
  animation: zoomIn 1s ease;
}
@keyframes zoomIn {
  from {
    filter: blur(8px);
    opacity: 0;
  }
  to {
    filter: blur(0);
    opacity: 1;
  }
}
h2 {
  margin-top: 40px;
  margin-bottom: 0;
  color: #d1d2d2;
  font-family: "Courgette", cursive;
}
.description {
  text-align: center;
  margin-top: 40px;
  width: 470px;
  color: #d1d2d2;
  line-height: 1.7;
  font-family: "hytmr";
}
a {
  margin-top: 40px;
  font-family: "Courgette", cursive;
  text-decoration: none;
}
a.button.theme:hover {
  background: #ff5722;
}
a.button.theme {
  background: #1bcdfc;
  color: var(--site-bg);
}
a.button {
  font-weight: 500;
  line-height: 1;
  padding: 0.75rem 2rem;
  border-radius: 3px;
  transition: background 0.2s ease-out;
  -moz-transition: background 0.2s ease-out;
  -webkit-transition: background 0.2s ease-out;
  -o-transition: background 0.2s ease-out;
  font-size: 0.9375rem;
}
a:hover {
  color: #ff5722;
}
</style>