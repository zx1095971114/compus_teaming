<template>
  <div class="dashboard-container">
    <el-row :gutter="40" class="panel-group">
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel">
          <div class="card-panel-icon-wrapper icon-people">
            <svg-icon icon-class="tree" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">班级总数</div>
            <count-to
              :start-val="0"
              :end-val="classCount"
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
            <svg-icon icon-class="peoples" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">班级总人数</div>
            <count-to
              :start-val="0"
              :end-val="peopleCount"
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
            <svg-icon icon-class="education" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">招募总数</div>
            <count-to
              :start-val="0"
              :end-val="examPaperCount"
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
            <svg-icon icon-class="edit" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">团队总数</div>
            <count-to
              :start-val="0"
              :end-val="doExamCount"
              :duration="3200"
              class="card-panel-num"
              v-loading="loading"
            />
          </div>
        </div>
      </el-col>
    </el-row>
    <div id="charts">
      <div class="chart" id="class"></div>
      <div class="chart" id="exam"></div>
    </div>
  </div>
</template>

<script>
import CountTo from "vue-count-to";
export default {
  name: "Dashboard",
  components: {
    CountTo,
  },
  mounted() {
    this.initClass();
    this.initExam();
  },
  methods: {
    initClass() {
      var echarts = require("echarts");
      var myChart = echarts.init(document.getElementById("class"));
      var option;

      option = {
        title: {
          text: "分布图",
          left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          orient: "vertical",
          left: "left",
        },
        series: [
          {
            name: "人数",
            type: "pie",
            radius: ["40%", "70%"],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: "center",
            },
            emphasis: {
              label: {
                show: true,
                fontSize: "40",
                fontWeight: "bold",
              },
            },
            labelLine: {
              show: false,
            },
            data: [
             { value: 1048, name: "软工一班" },
              { value: 735, name: "人工智能二班" },
              { value: 580, name: "软工三班" },
              { value: 484, name: "网络安全四班" },
              { value: 300, name: "计算机科学技术六班" },
            ],
          },
        ],
      };

      option && myChart.setOption(option);
    },
    initExam() {
      var echarts = require("echarts");
      var myChart = echarts.init(document.getElementById("exam"));
      var option;

      option = {
        title: {
          text: "分布图",
          left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          orient: "vertical",
          left: "left",
        },
        series: [
          {
            name: "xx",
            type: "pie",
            radius: "50%",
            data: [
              { value: 1048, name: "软工一班" },
              { value: 735, name: "人工智能二班" },
              { value: 580, name: "软工三班" },
              { value: 484, name: "网络安全四班" },
              { value: 300, name: "计算机科学技术六班" },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };
      option && myChart.setOption(option);
    },
  },
  data() {
    return {
      loading: false,
      classCount: 7,
      peopleCount: 200,
      examPaperCount: 15,
      doExamCount: 169,
    };
  },
};
</script>

<style lang="scss" scoped>
.dashboard-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}
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
    padding:30px
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
</style>
