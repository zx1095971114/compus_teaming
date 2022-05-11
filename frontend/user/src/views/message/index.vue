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
          :key="message.mid"
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
  created(){
    this.apis.messages.getAllMessage(sessionStorage.getItem("username")).then((res) => {
      var result = res.data.result;
      // console.log(result);
      this.messages = result;
    })
  },
  methods: {
    readAll() {
      this.apis.messages.setAllMessageStatus(sessionStorage.getItem("username")).then((res) => {
        // console.log(res);
      })
      for (let i = 0; i < this.messages.length; i++) {
        this.messages[i].status = 1;
      }
    },
  },
  data() {
    return {
      messages: [],
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
  color: grey;
  font-family: SentyGoldenBell;
}
</style>