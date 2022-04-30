<template>
  <div id="welcome" class="container animate__animated animate__fadeIn">
    <div class="container animate__animated animate__zoomIn">
      <transition name="fade" mode="out-in">
        <router-view />
      </transition>
    </div>
  </div>
</template>

<script>
export default {
  name: "welcome",
};
</script>

<style scoped>
#welcome {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #f2f4f7;
  position: relative;
}
#welcome::after {
  position: absolute;
  width: 100%;
  height: 100%;
  content: "";
  background: url("https://cdn.jsdelivr.net/gh/Langwenchong/cdn/bg/bg.svg");
  background-size: cover;
}
/* 容器的样式 */
.container {
  margin: auto;
  width: 950px;
  height: 550px;
  position: relative;
  z-index: 5;
}
.fade-enter-active,
.fade-leave-active {
  transition: 0.35s ease-out;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>