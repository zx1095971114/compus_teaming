<template>
  <div id="history" class="animate__animated animate__fadeIn">
    <div class="block">
      <el-timeline>
        <el-timeline-item
          v-for="(activity, index) in activities"
          :key="index"
          :icon="activity.icon"
          :type="activity.type"
          :color="activity.color"
          :size="activity.size"
          :timestamp="activity.timestamp"
          placement="top"
        >
          <el-card>
            <h4>{{ activity.title }}</h4>
            <p>{{ activity.content }}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script>
export default {
  name: "history",
  data() {
    return {
      activities: [
        {
          title: "登录系统",
          content: "1登陆了系统",
          timestamp: "2022-05-1 14:30",
          color: "#2ed573",
          size: "large",
          icon: "fad fa-sign-in-alt",
        },
        {
          title: "发起组队招募",
          content: "2发起了组队招募",
          timestamp: "2022-05-1 14:30",
          color: "#ff6b81",
          size: "large",
          icon: "fad fa-flag",
        },
        {
          title: "参加组队",
          content: "你参加了组队",
          timestamp: "2022-05-1 14:30",
          color: "#9c88ff",
          size: "large",
          icon: "fad fa-handshake",
        },
        {
          title: "查看招募",
          content: "你查看了招募",
          timestamp: "2022-05-1 14:30",
          color: "#ff7f50",
          size: "large",
          icon: "fad fa-search",
        },
        {
          title: "linaiani",
          content: "张睿登陆了系统",
          timestamp: "2022-05-1 14:30",
          color: "#2ed573",
          size: "large",
          icon: "fad fa-sign-in-alt",
        },
        {
          title: "修改个人信息",
          content: "你修改了个人信息",
          timestamp: "2022-05-1 14:30",
          color: "#00a8ff",
          size: "large",
          icon: "fad fa-edit",
        },
      ],
    };
  },
};
</script>


<style scoped>
#history {
  width: 100%;
  min-height: 100vh;
  /* background: pink; */
}
</style>