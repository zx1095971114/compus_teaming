<template>
  <div id="analyzeShow">
    <div id="singleChoice" v-if="question.questionType === 1" class="wrap">
      <p class="head" style="background: #8cbda4">{{ idx }}、 单选题</p>
      <div class="ql-editor" v-html="question.title"></div>
      <el-radio-group v-model="question.answer">
        <div v-for="(el, idx) in question.items" :key="idx">
          <el-radio :label="el.prefix">
            <div class="radio">
              <span class="ql-editor">{{ el.prefix }}.</span>
              <div class="ql-editor" v-html="el.content"></div>
            </div>
          </el-radio>
        </div>
      </el-radio-group>
      <div class="info">
        <p class="rate">
          难度：<el-rate disabled v-model="question.difficult"></el-rate>
        </p>
        <div class="analyze-box">
          <p>解析：</p>
          <div class="ql-editor" v-html="question.analyze"></div>
        </div>
        <p>正确答案：{{ question.answer }}</p>
        <p>选项分析：</p>
        <div id="chart" ref="chart"></div>
      </div>
    </div>
    <div
      id="multipleChoice"
      v-else-if="question.questionType === 2"
      class="wrapper"
    >
      <p class="head" style="background: #e9ad69">{{ idx }}、 多选题</p>
      <div class="ql-editor" v-html="question.title"></div>
      <el-checkbox-group v-model="question.answer">
        <div v-for="(el, idx) in question.items" :key="idx">
          <el-checkbox :label="el.prefix">
            <div class="radio">
              <span class="ql-editor">{{ el.prefix }}.</span>
              <div class="ql-editor" v-html="el.content"></div>
            </div>
          </el-checkbox>
        </div>
      </el-checkbox-group>
      <div class="info">
        <p class="rate">
          难度：<el-rate disabled v-model="question.difficult"></el-rate>
        </p>
        <div class="analyze-box">
          <p>解析：</p>
          <div class="ql-editor" v-html="question.analyze"></div>
        </div>
        <div class="answer">
          正确答案：
          <div v-for="(el, idx) in question.answer" :key="idx">
            {{ el }}
          </div>
        </div>
        <p>选项分析：</p>
        <div id="chart" ref="chart"></div>
      </div>
    </div>
    <div id="trueFalse" class="wrapper" v-else-if="question.questionType === 3">
      <p class="head" style="background: #6a89cc">{{ idx }}、 判断题</p>
      <div class="ql-editor" v-html="question.title"></div>
      <el-radio-group v-model="question.answer">
        <div v-for="(el, idx) in question.items" :key="idx">
          <el-radio :label="el.prefix">
            <div class="radio">
              <span class="ql-editor">{{ el.prefix }}.</span>
              <div class="ql-editor" v-html="el.content"></div>
            </div>
          </el-radio>
        </div>
      </el-radio-group>
      <div class="info">
        <p class="rate">
          难度：<el-rate disabled v-model="question.difficult"></el-rate>
        </p>
        <div class="analyze-box">
          <p>解析：</p>
          <div class="ql-editor" v-html="question.analyze"></div>
        </div>
        <p>正确答案：{{ question.answer }}</p>
        <p>选项分析：</p>
        <div id="chart" ref="chart"></div>
      </div>
    </div>
    <div
      id="shortAnswer"
      v-else-if="question.questionType === 4"
      class="wrapper"
    >
      <p class="head" style="background: #cf6a87">{{ idx }}、 简答题</p>
      <div class="ql-editor" v-html="question.title"></div>
      <div class="note">考生答案：</div>
      <div class="ql-editor" v-html="question.studentAnswer"></div>
      <div class="info">
        <p class="rate">
          难度：<el-rate disabled v-model="question.difficult"></el-rate>
        </p>
        <div class="analyze-box">
          <p>解析：</p>
          <div class="ql-editor" v-html="question.analyze"></div>
        </div>
        <p>正确答案：</p>
        <div class="ql-editor" v-html="question.analyze"></div>
        <p>选项分析：</p>
        <div id="chart" ref="chart"></div>
      </div>
    </div>
    <div id="sort" v-else-if="question.questionType === 5" class="wrap">
      <p class="head" style="background: #596275">{{ idx }}、排序题</p>
      <draggable v-model="question.studentAnswer">
        <transition-group>
          <div
            class="answerBox"
            v-for="element in question.studentAnswer"
            :key="element.prefix"
          >
            <div class="ql-container ql-snow">
              <span>{{ element.prefix }}</span>
              <div class="content ql-editor" v-html="element.content" />
            </div>
          </div>
        </transition-group>
      </draggable>
      <div class="info">
        <p class="rate">
          难度：<el-rate disabled v-model="question.difficult"></el-rate>
        </p>
        <div class="analyze-box">
          <p>解析：</p>
          <div class="ql-editor" v-html="question.analyze"></div>
        </div>
        <p>正确答案：</p>
        <draggable v-model="question.answer">
          <transition-group>
            <div
              class="answerBox"
              v-for="element in question.answer"
              :key="element.prefix"
            >
              <div class="ql-container ql-snow">
                <span>{{ element.prefix }}</span>
                <div class="content ql-editor" v-html="element.content" />
              </div>
            </div>
          </transition-group>
        </draggable>
        <p>选项分析：</p>
        <div id="chart" ref="chart"></div>
      </div>
    </div>
  </div>
</template>

<script>
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import draggable from "vuedraggable";
export default {
  name: "answerShow",
  components: {
    draggable,
  },
  props: {
    question: Object,
    idx: Number,
    edit: Boolean,
  },
  data() {
    return {
      radio: "",
    };
  },
  mounted() {
    this.initChart();
  },
  methods: {
    initChart() {
      var echarts = require("echarts");
      var myChart = echarts.init(this.$refs.chart);
      var option;
      if (this.question.questionType === 1) {
        //   单选题
        option = {
          tooltip: {
            trigger: "item",
          },
          legend: {
            top: "5%",
            left: "center",
          },
          series: [
            {
              name: "Access From",
              type: "pie",
              radius: "50%",
              data: [
                { value: 1048, name: "A" },
                { value: 735, name: "B" },
                { value: 580, name: "C" },
                { value: 484, name: "D" },
                { value: 300, name: "E" },
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
      } else if (this.question.questionType === 2) {
        option = {
          tooltip: {
            trigger: "item",
          },
          legend: {
            top: "5%",
            left: "center",
          },
          series: [
            {
              name: "选项人数",
              type: "pie",
              radius: [12, 100],
              center: ["50%", "50%"],
              roseType: "area",
              itemStyle: {
                borderRadius: 8,
              },
              data: [
                { value: 40, name: "A" },
                { value: 38, name: "B" },
                { value: 32, name: "C" },
                { value: 30, name: "D" },
                { value: 28, name: "E" },
                { value: 26, name: "F" },
              ],
            },
          ],
        };
      } else if (this.question.questionType === 3) {
        option = {
          tooltip: {
            trigger: "item",
          },
          legend: {
            top: "5%",
            left: "center",
          },
          series: [
            {
              type: "pie",
              radius: ["40%", "70%"],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: "#fff",
                borderWidth: 2,
              },
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
                { value: 1048, name: "否" },
                { value: 735, name: "是" },
              ],
              color: ["#eb4d4b", "#6ab04c"],
            },
          ],
        };
      } else if (this.question.questionType === 4) {
        option = {
          title: {
            left: "center",
            top: 20,
            textStyle: {
              color: "#000",
            },
          },
          tooltip: {
            trigger: "item",
          },

          series: [
            {
              name: "得分人数",
              type: "pie",
              radius: "75%",
              center: ["50%", "50%"],
              data: [
                { value: 335, name: "1分" },
                { value: 310, name: "2分" },
                { value: 274, name: "3分" },
                { value: 235, name: "4分" },
                { value: 400, name: "5分" },
              ].sort(function (a, b) {
                return a.value - b.value;
              }),
              roseType: "radius",
              label: {
                color: "#333",
              },
              labelLine: {
                lineStyle: {
                  color: "#333",
                },
                smooth: 0.2,
                length: 10,
                length2: 20,
              },
              itemStyle: {
                shadowBlur: 10,
                shadowColor: "rgba(0, 0, 0, 0.1)",
              },
              animationType: "scale",
              animationEasing: "elasticOut",
              animationDelay: function (idx) {
                return Math.random() * 200;
              },
            },
          ],
        };
      } else {
        option = {
          legend: {},
          tooltip: {},
          dataset: {
            dimensions: ["product", "A", "B", "C", "D"],
            source: [
              {
                product: "第一位",
                A: 43.3,
                B: 85.8,
                C: 93.7,
                D: 90.5,
              },
              {
                product: "第二位",
                A: 83.1,
                B: 73.4,
                C: 55.1,
                D: 90.5,
              },
              {
                product: "第三位",
                A: 86.4,
                B: 65.2,
                C: 82.5,
                D: 90.5,
              },
              {
                product: "第四位",
                A: 72.4,
                B: 53.9,
                C: 39.1,
                D: 90.5,
              },
            ],
          },
          xAxis: { type: "category" },
          yAxis: {},
          // Declare several bar series, each will be mapped
          // to a column of dataset.source by default.
          series: [
            { type: "bar" },
            { type: "bar" },
            { type: "bar" },
            { type: "bar" },
          ],
        };
      }
      option && myChart.setOption(option);
    },
  },
};
</script>


<style scoped>
#analyzeShow {
  width: 100%;
  min-height: 200px;
}
#chart {
  border: 1px solid #333;
  width: 100%;
  min-height: 300px;
}
.wrapper {
  width: 100%;
  display: flex;
  flex-direction: column;
}
.analyze-box {
  width: 100%;
  margin-bottom: 1em;
  display: flex;
}
.analyze-box p {
  margin: 0;
}
.rate {
  display: flex;
}
.head {
  margin-top: 0;
  padding: 8px;
  color: #fff;
}
.ql-container.ql-snow {
  border: none !important;
}
.ql-editor {
  padding: 0 !important;
  margin-right: 10px;
}
.el-radio {
  display: flex;
  margin-top: 10px;
}
.radio {
  display: flex;
}
.radio p {
  margin-block-start: 0 !important;
  margin-block-end: 0 !important;
}
.el-checkbox {
  display: flex;
  margin-top: 10px;
}
.answer {
  display: flex;
}
.note {
  margin: 10px 0;
}
.answerBox {
  cursor: pointer;
  min-height: 40px;
  margin-bottom: 15px;
  background: #ffff;
  border: 1px solid #dcdfe6;
  padding: 5px 10px;
  border-radius: 4px;
}
.answerBox:hover {
  background: #f5f7fa;
}
</style>