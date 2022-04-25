<template>
  <div id="screenShot">
    <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible"
      width="40%"
      center
    >
      <span>{{ dialogMessage }}</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="cheat">确 定</el-button>
      </span>
    </el-dialog>
    <div id="menu">
      <h3>考试全程抓拍图片监控</h3>
      <el-button type="danger" @click="centerDialogVisible = true"
        >判定作弊</el-button
      >
    </div>
    <div class="info-gallery">
      <div class="wrapper">
        <div class="card" v-for="sc in screenShots" :key="sc.id">
          <div class="headimg">
            <img src="http://172.28.45.56/static/images/defaultError.png" />
          </div>
          <div class="content">
            <div class="top-c">
              <p class="title">{{ sc.username }}</p>
              <p class="teacher">{{ sc.name }}</p>
            </div>
            <div class="bottom-c">
              <p class="snum">{{ sc.shotTime }}课时</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "screenShot",
  methods: {
    cheat() {
      this.centerDialogVisible = false;
    },
  },
  data() {
    return {
      centerDialogVisible: false,
      dialogMessage: `被判定为作弊的试卷将会记为0分，您确定要将此试卷判定为作弊吗❓`,
      screenShots: [
        {
          id: 1,
          username: "langwenchong",
          name: "langwenchong",
          shotTime: "2021-02-02",
        },
        {
          id: 2,
          username: "langwenchong",
          name: "langwenchong",
          shotTime: "2021-02-02",
        },
        {
          id: 3,
          username: "langwenchong",
          name: "langwenchong",
          shotTime: "2021-02-02",
        },
        {
          id: 4,
          username: "langwenchong",
          name: "langwenchong",
          shotTime: "2021-02-02",
        },
      ],
    };
  },
};
</script>

<style scoped>
#screenShot {
  width: 100%;
  min-height: 88vh;
  /* background: pink; */
  box-sizing: border-box;
  padding: 20px;
}
#menu {
  width: 100%;
  height: 48px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.info-gallery {
  margin-top: 40px;
  width: 100%;
  /* height: 400px; */
  margin-bottom: 40px;
}

.info-gallery .wrapper {
  width: 100%;
  /* min-height: 100vh; */
  display: flex;
  flex-wrap: wrap;
  /* background:pink; */
}
.info-gallery .wrapper .card {
  margin-right: 20px;
  width: 260px;
  background: #fff;
  height: 320px;
  border-radius: 8px;
  overflow: hidden;
  transition: 0.2s;
  cursor: pointer;
  background: var(--background2);
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.12), 0 0 3px rgba(0, 0, 0, 0.02);
}
.info-gallery .wrapper .card:hover {
  box-shadow: 0 4px 16px 0 rgb(0 0 0 / 12%);
}
.info-gallery .wrapper .card .headimg {
  width: 100%;
  height: 65%;
  overflow: hidden;
}
.info-gallery .wrapper .card:hover .headimg img {
  transform: scale(1.1);
  filter: brightness(70%);
}
.info-gallery .wrapper .card .headimg img {
  transition: 1s;
  width: 100%;
  height: 100%;
}
.info-gallery .wrapper .card .content {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 100%;
  height: 35%;
  /* background:pink; */
  padding: 10px 12px;
  box-sizing: border-box;
}
.info-gallery .wrapper .card .content .top-c {
  width: 100%;
  height: 40%;
  /* background:#475669; */
}
.info-gallery .wrapper .card .content .top-c .title {
  font-size: 14px;
  font-weight: 700;
  font-family: "PingFangSC-Medium", "PingFangSC";
}
.info-gallery .wrapper .card .content .top-c .teacher {
  margin-top: 3px;
  font-size: 12px;
  color: var(--text2);
}
.info-gallery .wrapper .card .content .bottom-c {
  width: 100%;
  height: 40%;
  /* background:pink; */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.info-gallery .wrapper .card .content .bottom-c .snum {
  font-size: 12px;
  color: var(--text3);
}
.info-gallery .wrapper .card .content .bottom-c .info {
  width: 100%;
  border-radius: 8px;
  box-sizing: border-box;
  background: var(--background3);
  height: calc(100% - 10px);
  color: var(--text3);
  font-size: 14px;
  padding: 10px 12px;
  /* display: -webkit-box; */
  word-wrap: break-word;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
}
</style>