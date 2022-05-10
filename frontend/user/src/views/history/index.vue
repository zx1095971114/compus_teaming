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
  created(){
    this.apis.history.getallhistory(sessionStorage.getItem("username")).then((res) => {
      
      var result = res.data.result;
      // console.log(result);
      var activities = [];
      for (let i = 0; i < result.length; i++) {
            // 真正数组中的每个对象
            var activity = {};
            // 基本信息初始化
            activity.title = result[i].htitle;
            activity.content = result[i].history;
            activity.timestamp = result[i].htime;
            activity.size = "large";
            activity.type = "primary";

            // 分辨类型
            let matchContent = result[i].htitle;
            if (matchContent.match("登录账号")) {
              activity.icon = "fad fa-sign-in-alt";
              activity.color = "#2ed573";
            } else if (matchContent.match("创建招募")) {
              activity.icon = "fad fa-flag";
              activity.color = "#ff6b81";
            } else if (matchContent.match("查看招募")) {
              activity.icon = "fas fa-edit";
              activity.color = "#0984e3";
            } else if (matchContent.match("加入招募")) {
              activity.icon = "fad fa-handshake";
              activity.color = "#9c88ff";
            } else if (matchContent.match("修改信息")) {
              activity.icon = "fad fa-edit";
              activity.color = "#00a8ff";
            }
            activities.unshift(activity);
          }
          this.activities = activities;
    })
  },
  data() {
    return {
      activities: [
        // {
        //   title: "登录系统",
        //   content: "1登陆了系统",
        //   timestamp: "2022-05-1 14:30",
        //   color: "#2ed573",
        //   size: "large",
        //   icon: "fad fa-sign-in-alt",
        // },
        // {
        //   title: "发起组队招募",
        //   content: "2发起了组队招募",
        //   timestamp: "2022-05-1 14:30",
        //   color: "#ff6b81",
        //   size: "large",
        //   icon: "fad fa-flag",
        // },
        // {
        //   title: "参加组队",
        //   content: "你参加了组队",
        //   timestamp: "2022-05-1 14:30",
        //   color: "#9c88ff",
        //   size: "large",
        //   icon: "fad fa-handshake",
        // },
        // {
        //   title: "查看招募",
        //   content: "你查看了招募",
        //   timestamp: "2022-05-1 14:30",
        //   color: "#ff7f50",
        //   size: "large",
        //   icon: "fad fa-search",
        // },
        // {
        //   title: "linaiani",
        //   content: "张睿登陆了系统",
        //   timestamp: "2022-05-1 14:30",
        //   color: "#2ed573",
        //   size: "large",
        //   icon: "fad fa-sign-in-alt",
        // },
        // {
        //   title: "修改个人信息",
        //   content: "你修改了个人信息",
        //   timestamp: "2022-05-1 14:30",
        //   color: "#00a8ff",
        //   size: "large",
        //   icon: "fad fa-edit",
        // },
      ],
    };
  },
};
</script>


<style scoped>
#history {
  width: 100%;
  min-height: 100vh;
}
</style>