<template>
  <div id="check">
    <div id="l-side">
      <h3>{{ exam.title }}</h3>
      <div class="info">
        <p>提交人：{{ exam.student }}</p>
        <p>提交时间：{{ exam.submitTime }}</p>
        <p>已判题数：6 / {{ exam.total }}题</p>
        <p>试卷分数：{{ exam.currentScore }} / {{ exam.fullScore }}分</p>
      </div>
      <div class="menu">
        <el-tag
          class="tag"
          :type="questionStatus(item.status, item.questionType)"
          v-for="(item, j) in tags"
          :key="j"
          @click="goAnchor(`#question-${item.id}`)"
          >{{ j + 1 }}</el-tag
        >
      </div>
      <div class="tool">
        <el-button type="primary" plain v-if="edit === true"
          >提交批改</el-button
        >
        <el-button plain @click="toList">返回列表</el-button>
      </div>
    </div>
    <div id="r-main">
      <div class="module" v-for="(mod, idx) in exam.modules" :key="idx">
        <div class="header">
          <h3>模块{{ idx + 1 }}：{{ mod.title }}</h3>
        </div>
        <div
          :id="`question-${question.id}`"
          class="card"
          v-for="(question, j) in mod.questionList"
          :key="j"
        >
          <AnswerShow
            :edit="edit"
            :idx="IndexCompute(idx, j)"
            :question="question"
            @updateExam="updateExam"
          ></AnswerShow>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import AnswerShow from "@/components/AnswerShow";
export default {
  name: "check",
  components: {
    AnswerShow,
  },
  created() {
    var params = this.$route.params;
    if (params != null) {
      this.edit = params.edit;
    }
  },
  data() {
    return {
      edit: true,
      exam: {
        currentScore: 80,
        fullScore: 150,
        total: 30,
        submitTime: "2022-02-09 22:40",
        student: "langwenchong",
        title: "第一次试验检测",
        tip: "",
        examClass: [],
        dateMap: [],
        duration: 1,
        modules: [
          {
            title: "选择题",
            questionList: [
              {
                id: 1,
                questionType: 1,
                difficult: 3,
                // -1表示标题
                title: "fsfsafsafsfsa",
                items: [
                  { prefix: "A", content: "ggg" },
                  { prefix: "B", content: "gggggg" },
                  { prefix: "C", content: "aaa" },
                  { prefix: "D", content: "cccc" },
                ],
                status: 1, //-1表示错误，0表示待批改，1表示正确
                answer: "A",
                studentAnswer: "C",
                // -2表示解析
                analyze: "fsagsagsagsag",
                score: 1,
                studentScore: 1,
              },
              {
                id: 2,
                questionType: 1,
                difficult: 2,
                title: "fsfsfssd",
                items: [
                  { prefix: "A", content: "fafsa" },
                  { prefix: "B", content: "gsagsgs" },
                  { prefix: "C", content: "faf" },
                  { prefix: "D", content: "fasfs" },
                ],
                status: -1,
                answer: "A",
                analyze: "fafsfs",
                studentAnswer: "C",
                score: 2,
                studentScore: 0,
              },
              {
                id: 3,
                questionType: 1,
                difficult: 3,
                title: "fsfsfss",
                items: [
                  { prefix: "A", content: "fsafs" },
                  { prefix: "B", content: "fsaf" },
                  { prefix: "C", content: "fsafs" },
                  { prefix: "D", content: "fsaf" },
                ],
                status: 1,
                answer: "D",
                studentAnswer: "C",
                analyze: "",
                score: 2,
                studentScore: 2,
              },
            ],
          },
          {
            title: "多选题",
            questionList: [
              {
                id: 4,
                questionType: 2,
                difficult: 3,
                title: "fsfsafsafsfsa",
                items: [
                  { prefix: "A", content: "ggg" },
                  { prefix: "B", content: "gggggg" },
                  { prefix: "C", content: "aaa" },
                  { prefix: "D", content: "cccc" },
                ],
                status: 1,
                answer: ["A", "C", "D"],
                studentAnswer: ["A", "C", "D"],
                analyze: "fsagsagsagsag",
                score: 1,
                studentScore: 1,
              },
              {
                id: 5,
                questionType: 2,
                difficult: 2,
                title: "fsfsfssd",
                items: [
                  { prefix: "A", content: "fafsa" },
                  { prefix: "B", content: "gsagsgs" },
                  { prefix: "C", content: "faf" },
                  { prefix: "D", content: "fasfs" },
                ],
                status: -1,
                answer: "A",
                answer: ["A", "C", "B"],
                studentAnswer: ["A", "C"],
                score: 2,
                studentScore: 0,
              },
              {
                id: 6,
                questionType: 2,
                difficult: 3,
                title: "fsfsfss",
                items: [
                  { prefix: "A", content: "fsafs" },
                  { prefix: "B", content: "fsaf" },
                  { prefix: "C", content: "fsafs" },
                  { prefix: "D", content: "fsaf" },
                ],
                status: 1,
                answer: ["A", "C"],
                studentAnswer: ["A", "C"],
                analyze: "SASASASA",
                score: 2,
                studentScore: 2,
              },
            ],
          },
          {
            title: "判断题",
            questionList: [
              {
                id: 7,
                questionType: 3,
                difficult: 3,
                title: "fsfsafsafsfsa",
                items: [
                  { prefix: "A", content: "正确" },
                  { prefix: "B", content: "错误" },
                ],
                status: 1,
                answer: "A",
                studentAnswer: "A",
                analyze: "fsagsagsagsag",
                score: 1,
                studentScore: 1,
              },
              {
                id: 8,
                questionType: 3,
                difficult: 2,
                title: "fsfsfssd",
                items: [
                  { prefix: "A", content: "正确" },
                  { prefix: "B", content: "错误" },
                ],
                status: -1,
                answer: "A",
                analyze: "fafsfs",
                studentAnswer: "B",
                score: 2,
                studentScore: 0,
              },
              {
                id: 9,
                questionType: 3,
                difficult: 3,
                title: "fsfsfss",
                items: [
                  { prefix: "A", content: "正确" },
                  { prefix: "B", content: "错误" },
                ],
                status: -1,
                answer: "A",
                studentAnswer: "B",
                analyze: "",
                score: 2,
                studentScore: 0,
              },
            ],
          },
          {
            title: "简答题",
            questionList: [
              {
                id: 10,
                questionType: 4,
                difficult: 3,
                title: "fsfsfss",
                status: 0,
                answer: "答案1",
                studentAnswer: "sddfsssssssssssssssssss",
                analyze: "SASASASA",
                score: 2,
                studentScore: undefined,
              },
              {
                id: 11,
                questionType: 4,
                difficult: 3,
                title: "fsfsfss",
                status: 0,
                answer: "123123",
                studentAnswer: "rtyui",
                analyze: "SASASASA",
                score: 2,
                studentScore: undefined,
              },
              {
                id: 12,
                questionType: 4,
                difficult: 3,
                title: "fsfsfss",
                status: 0,
                answer: "456",
                studentAnswer: "oiuttd",
                analyze: "SASASASA",
                score: 2,
                studentScore: undefined,
              },
            ],
          },
          {
            title: "排序题",
            questionList: [
              {
                id: 13,
                questionType: 5,
                difficult: 3,
                title: "fsfsfss",
                items: [
                  { prefix: "A", content: "<pre>A</pre>" },
                  { prefix: "B", content: "<p>B</p>" },
                  { prefix: "C", content: "<pre>C</pre>" },
                  { prefix: "D", content: "<p>D</p>" },
                ],
                status: 1,
                answer: [
                  { prefix: "A", content: "<pre>A</pre>" },
                  { prefix: "B", content: "<p>B</p>" },
                  { prefix: "D", content: "<p>D</p>" },
                  { prefix: "C", content: "<pre>C</pre>" },
                ],
                studentAnswer: [
                  { prefix: "A", content: "<pre>A</pre>" },
                  { prefix: "B", content: "<p>B</p>" },
                  { prefix: "C", content: "<pre>C</pre>" },
                  { prefix: "D", content: "<p>D</p>" },
                ],
                analyze: "SASASASA",
                score: 2,
                studentScore: 2,
              },
            ],
          },
        ],
      },
    };
  },
  computed: {
    tags() {
      var tags = [];
      var modules = this.exam.modules;
      for (let i = 0; i < modules.length; i++) {
        var questionList = modules[i].questionList;
        for (let j = 0; j < questionList.length; j++) {
          tags.push(questionList[j]);
        }
      }
      return tags;
    },
  },
  methods: {
    toList() {
      if (this.edit) {
        this.$router.push({ name: "examCorrect" });
      } else {
        this.$router.push({ name: "resultList" });
      }
    },
    questionStatus(status, type) {
      if (status === -1) {
        return `danger`;
      } else if (status === 0) {
        return `warning`;
      } else if (status === 1 && type != 4) {
        return `success`;
      } else if (status === 1 && type === 1) {
        return ``;
      }
    },
    goAnchor(id) {
      this.$el.querySelector(id).scrollIntoView({
        behavior: "instant",
        block: "center",
        inline: "nearest",
      });
    },
    updateExam(n, o) {
      n.status = 2; //对于简答题只能是0和2表示等待审批和已审批完
      var modules = this.exam.modules;
      for (let i = 0; i < modules.length; i++) {
        var questionList = modules[i].questionList;
        var idx = questionList.indexOf(o);
        if (idx !== -1) {
          questionList[idx] = n;
          return;
        }
      }
    },
    IndexCompute(idx, j) {
      var ans = 0;
      for (let i = 0; i < idx; i++) {
        ans += this.exam.modules[i].questionList.length;
      }
      return ans + j + 1;
    },
  },
};
</script>

<style scoped>
#check {
  width: 100%;
  background: #eff3f7;
  box-sizing: border-box;
  padding: 20px;
  display: flex;
  justify-content: space-between;
}
#l-side {
  position: fixed;
  top: 105px;
  background: #fff;
  width: 300px;
  height: 100%;
  box-sizing: border-box;
  padding: 15px;
}
.info {
  border-bottom: 1px solid #e6e6e6;
}
.menu {
  box-sizing: border-box;
  padding: 10px 0px;
  display: flex;
  flex-wrap: wrap;
  width: 100%;
}
.tag {
  margin-bottom: 5px;
  margin-left: 5px;
  cursor: pointer;
}
.tool {
  display: flex;
  padding-top: 8px;
  border-top: 1px solid #e6e6e6;
}
.header {
  display: flex;
  align-items: center;
  height: 64px;
  box-sizing: border-box;
  padding: 10px;
  background: #fafafa;
  border-top: 1px solid #dedede;
  border-bottom: 1px solid #dedede;
}
.card {
  width: 100%;
  box-sizing: border-box;
  padding: 20px;
}
#r-main {
  width: calc(100% - 320px);
  margin-left: 320px;
  background: #fff;
}
</style>