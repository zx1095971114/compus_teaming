<template>
  <div id="message">
    <div class="container">
      <div class="tools">
        <p class="mesList">
          <i class="fad fa-comment-alt-smile"></i>&nbsp;我的消息
        </p>
        <p class="check" @click="readAll">
          <i class="fad fa-comment-alt-check"></i>全部已读
        </p>
      </div>

      <transition-group appear v-if="messages.length > 0" class="wrapper">
        <single-message
          v-for="message in messages"
          :key="message.id"
          :message="message"
        >
        </single-message>
      </transition-group>

      <div class="nomore-messages" v-if="messages.length <= 0">
        <h1><i class="fas fa-engine-warning"></i> 您暂时没有接收到任何消息</h1>
      </div>
    </div>
  </div>
</template>



<script>
import singleMessage from "./singleMessage.vue";
export default {
  name: "message",
  components: {
    singleMessage,
  },
  methods: {
    readAll() {
      for (let i = 0; i < this.messages.length; i++) {
        this.messages[i].state = 1;
      }
    },
  },
  data() {
    return {
      messages: [
        {
          id: "1",
          title: "消息1",
          date: "2022-01-11 17:56:25",
          msg: "文翀老师邀请你加入数据库2班",
          state: 0,
          type: "class",
        },
        {
          id: "2",
          title: "消息2",
          date: "2021-02-3 17:56:25",
          msg: "国涛老师邀请你加入国学素养选修班",
          state: 1,
          type: "class",
        },
        {
          id: "3",
          title: "消息3",
          date: "2022-02-10 18:19:25",
          msg: "黄硕老师邀请你加入计算机组成原理2班",
          state: 0,
          type: "class",
        },
        {
          id: "4",
          title: "消息4",
          date: "2022-02-11 16:56:25",
          msg: "您的第四次高等数学单元测验已经批改完成",
          state: 0,
          type: "test",
        },
        {
          id: "5",
          title: "消息5",
          date: "2022-02-11 18:20:25",
          msg: "阿巴",
          state: 0,
          type: "other",
        },
        {
          id: "6",
          title: "消息6",
          date: "2022-02-11 18:22:25",
          msg: "阿巴",
          type: "class",
          state: 0,
          type: "other",
        },
        {
          id: "7",
          title: "消息7",
          date: "2022-02-11 16:56:25",
          msg: "阿巴",
          state: 0,
          type: "other",
        },
        {
          id: "8",
          title: "消息8",
          date: "2022-01-11 17:56:25",
          msg: "阿巴",
          state: 0,
          type: "other",
        },
        {
          id: "9",
          title: "消息9",
          date: "2022-01-11 17:56:25",
          msg: "阿巴",
          state: 0,
          type: "other",
        },
      ],
    };
  },
};
</script>


<style scoped>
#message {
  width: 100%;
  min-height: 100vh;
  background: rgba(247, 248, 250, 1);
  overflow: hidden;
  display: flex;
  justify-content: center;
}
.container {
  margin-top: 10px;
  width: 100%;
  min-height: calc(100vh - 10px);
  border-radius: 4px;
  /* box-shadow: 0 0 1px rgba(0, 0, 0, 0.3); */
  overflow: hidden;
}
.wrapper {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.mesList {
  margin-left: 20px;
  margin-top: 20px;
  margin-bottom: 25px;
  font-size: 1.2rem;
  font-family: "SentyGoldenBell";
}
.check {
  background: rgba(0, 10, 32, 0.05);
  padding: 0px 10px;
  border-radius: 8px;
  transition: 0.3s;
  font-size: 1.2rem;
  font-family: "SentyGoldenBell";
}
.check .fa-comment-alt-check {
  top: 2px;
  margin-right: 8px;
}
.check:hover {
  opacity: 0.8;
}
.tools {
  margin: 0 auto;
  width: 90%;
  display: flex;
  justify-content: space-between;
}
.nomore-messages {
  width: 100%;
  height: calc(100% - 80px);
  display: flex;
  justify-content: center;
  align-items: center;
  /* background: pink; */
  color: grey;
  font-family: SentyGoldenBell;
}
</style>