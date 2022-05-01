<template>
  <div id="singleMessage" class="animate__animated animate__fadeInUp">
    <el-collapse accordion>
      <el-collapse-item @click.native="readed()">
        <template slot="title">
          <div class="title">
            {{ mes.title }}
          </div>
          <div :class="[mes.state === 1 ? 'state green' : 'state red']">
            {{ mes.state === 1 ? "已读" : "未读" }}
          </div>
          <div class="date">{{ mes.date }}</div>
        </template>
        <div class="content">
          <div>{{ mes.msg }}</div>
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>
<script>
export default {
  name: "singleMessage",
  props: {
    message: Object,
  },
  data() {
    return {
      mes: this.message,
    };
  },
  created() {
    this.initDate();
  },
  methods: {
    readed() {
      var that = this;
      setTimeout(() => {
        that.mes.state = 1;
      }, 300);
    },
    initDate() {
      var date = this.mes.date;
      date = date.replace(new RegExp("-", "gm"), "/");
      var dateTimeStamp = new Date(date).getTime();
      let minute = 1000 * 60; //把分，时，天，周，半个月，一个月用毫秒表示
      let hour = minute * 60;
      let day = hour * 24;
      let week = day * 7;
      // let halfamonth = day * 15;
      let month = day * 30;

      let now = new Date().getTime(); //获取当前时间毫秒
      let diffValue = now - dateTimeStamp; //时间差

      if (diffValue < 0) {
        return;
      }

      let minC = diffValue / minute; //计算时间差的分，时，天，周，月
      let hourC = diffValue / hour;
      let dayC = diffValue / day;
      let weekC = diffValue / week;
      let monthC = diffValue / month;
      let result;
      if (monthC >= 1) {
        result = "" + parseInt(monthC) + " months ago";
      } else if (weekC >= 1) {
        result = "" + parseInt(weekC) + " weeks ago";
      } else if (dayC >= 1) {
        result = "" + parseInt(dayC) + " days ago";
      } else if (hourC >= 1) {
        result = "" + parseInt(hourC) + " hours ago";
      } else if (minC >= 1) {
        result = "" + parseInt(minC) + " minutes ago";
      } else {
        result = " Just Now";
      }
      this.mes.date = result;
    },
  },
};
</script>
<style scoped>
#singleMessage {
  width: 90%;
  overflow: hidden;
  margin-bottom: 10px;
  border-radius: 12px;
}
.title {
  width: 90%;
}
.state {
  margin-left: 80px;
  width: 6%;
  height: 30px;
  border-radius: 15px;
  line-height: 30px;
  text-align: center;
  font-weight: 800;
}
.date {
  display: inline-block;
  width: 20%;
  margin-left: 40px;
  color: rgba(52, 61, 73, 0.6);
}
.red {
  transition: 0.3s;
  background-color: rgba(250, 187, 187, 0.781);
  border-color: rgb(247, 104, 104);
  color: rgb(252, 99, 99);
}
.green {
  transition: 0.3s;
  background-color: rgb(214, 250, 214);
  border-color: rgb(145, 245, 145);
  color: rgb(78, 194, 116);
}
.content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-right: 20px;
}
</style>