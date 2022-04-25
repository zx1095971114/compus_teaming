<template>
  <div id="analyze">
    <div id="l-side">
      <h3>{{ exam.title }}</h3>
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
      <div id="top-analyze">
        <div class="header">
          <h3>试卷分析总览</h3>
        </div>
        <div class="count-wrapper">
          <el-row :gutter="40" class="panel-group">
            <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-people">
                  <svg-icon icon-class="star" class-name="card-panel-icon" />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">最高分</div>
                  <count-to
                    :start-val="0"
                    :end-val="exam.firstPoint"
                    :duration="2600"
                    class="card-panel-num"
                    v-loading="loading"
                  />
                </div>
              </div>
            </el-col>
            <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-message">
                  <svg-icon icon-class="exam" class-name="card-panel-icon" />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">最低分</div>
                  <count-to
                    :start-val="0"
                    :end-val="exam.lastPoint"
                    :duration="3000"
                    class="card-panel-num"
                    v-loading="loading"
                  />
                </div>
              </div>
            </el-col>
            <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-shopping">
                  <svg-icon icon-class="user" class-name="card-panel-icon" />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">平均分</div>
                  <count-to
                    :start-val="0"
                    :end-val="exam.averagePoint"
                    :duration="3600"
                    class="card-panel-num"
                    v-loading="loading"
                  />
                </div>
              </div>
            </el-col>
            <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-money">
                  <svg-icon icon-class="chart" class-name="card-panel-icon" />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">中位分</div>
                  <count-to
                    :start-val="0"
                    :end-val="exam.centerPoint"
                    :duration="3200"
                    class="card-panel-num"
                    v-loading="loading"
                  />
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
        <div id="histogram"></div>
      </div>
      <div class="module" v-for="(mod, idx) in exam.modules" :key="idx">
        <div class="header">
          <h3>模块{{ idx + 1 }}：{{ mod.title }}</h3>
        </div>
        <histogram></histogram>
        <div
          :id="`question-${question.id}`"
          class="card"
          v-for="(question, j) in mod.questionList"
          :key="j"
        >
          <AnalyzeShow
            :edit="edit"
            :idx="IndexCompute(idx, j)"
            :question="question"
            @updateExam="updateExam"
          ></AnalyzeShow>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import AnalyzeShow from "@/components/AnalyzeShow";
import CountTo from "vue-count-to";
import histogram from "./histogram";
export default {
  name: "analyze",
  components: {
    AnalyzeShow,
    CountTo,
    histogram,
  },
  created() {
    var params = this.$route.params;
    if (params != null) {
      this.edit = params.edit;
    }
  },
  mounted() {
    this.initHistogram();
  },
  data() {
    return {
      edit: true,
      loading: false,
      exam: {
        firstPoint: 95,
        lastPoint: 46,
        averagePoint: 78,
        centerPoint: 75,
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
    initHistogram() {
      var echarts = require("echarts");
      var myChart = echarts.init(document.getElementById("histogram"));
      var option;
      option = {
        xAxis: {
          type: "category",
          data: [65, 70, 75, 80, 85, 90, 95],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [120, 200, 150, 80, 70, 110, 130],
            type: "bar",
            showBackground: true,
            backgroundStyle: {
              color: "rgba(180, 180, 180, 0.2)",
            },
          },
        ],
      };
      option && myChart.setOption(option);
    },
    toList() {
     this.$router.push({name: 'examList'})
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
#analyze {
  width: 100%;
  background: #eff3f7;
  box-sizing: border-box;
  padding: 20px;
  display: flex;
  justify-content: space-between;
}
#top-analyze {
  width: 100%;
}
#histogram {
  width: 100%;
  height: 400px;
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

<style lang="scss" scoped>
#charts {
  width: 100%;
  height: 400px;
  display: flex;
  justify-content: space-between;

  .chart {
    width: 48%;
    height: 100%;
    background: #fff;
    box-sizing: border-box;
    padding: 30px;
  }
}
@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}

.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}

.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, 0.05);
    border-color: rgba(0, 0, 0, 0.05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #feca57;
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #feca57;
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width: 550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}

.echarts-line {
  background: #fff;
  padding: 16px 16px 0;
  margin-bottom: 32px;
}
.count-wrapper {
  width: 95%;
  margin: 0 auto;
}
</style>