<template>
  <div id="item" class="animate__animated animate__flipInX">
    <h2>
      <div class="point" v-state="el.type"></div>
      {{ el.title }}
    </h2>
    <p>加入时间：{{ el.time }}</p>
    <div class="tags">
      <div class="tag" v-for="(topicTag, k) in el.tags" :key="k">
        <img
          src="../../../static/images/teams/tag.png"
          height="16px"
          alt="tag"
        />{{ topicTag }}
      </div>
    </div>
    <div class="person">
      <div
        class="avatar"
        v-for="(avatar, i) in el.avatars"
        :key="i"
        v-if="i < 5"
      >
        <img :src="avatar" alt />
      </div>
      <span class="more">+63 Teammates</span>
    </div>
    <strong @click="toZone">前往团队空间</strong>
  </div>
</template>

<script>
export default {
  name: "item",
  props: {
    el: Object,
  },
  methods:{
    toZone(){
      this.$router.push({name: 'teamZone'})
    }
  },
  directives: {
    state: function (el, binding) {
      if (binding.value === "我创建的") {
        el.style.backgroundColor = "#f34b7d";
      } else if (binding.value === "我参与的") {
        el.style.backgroundColor = "#41b883";
      }
    },
  },
};
</script>

<style scoped>
#item {
  width: 100%;
  height: 70px;
  transition: 0.2s;
  display: flex;
  justify-content: space-around;
  padding: 20px;
  box-sizing: border-box;
  align-items: center;
}
#item:hover {
  background: #ecf0f1;
}
#item h2 {
  margin-right: 30px;
  font-family: "SentyGoldenBell";
}
#item p {
  font-size: 14px;
  margin-left: 20px;
  font-family: "hytmr";
  text-align: left;
}
.point {
  width: 10px;
  height: 10px;
  margin-right: 8px;
  border-radius: 50%;
  display: inline-block;
}
#item p {
  color: grey;
}
#item .tag {
  margin-left: 3px;
  color: #333;
  font-size: 12px;
  display: flex;
  align-items: center;
}
#item .tags {
  margin-left: 30px;
  /* width: auto; */
  display: flex;
}
.person {
  margin-left: 30px;
  /* background: red; */
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.person h2 {
  font-family: "SentyGoldenBell";
}
.person .avatar {
  width: 32px;
  height: 32px;
  margin-left: 5px;
  border-radius: 50%;
  border: 1px solid var(--text7);
}
.person .avatar img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
.person span {
  color: #58a6ff;
  font-weight: 400;
}
.more {
  margin-left: 20px;
  font-family: "dancing script";
}
strong {
  font-family: "KaiTi";
  color: #222;
  transition: 0.3s;
}
strong:hover {
  color: #f79f1f;
}
</style>