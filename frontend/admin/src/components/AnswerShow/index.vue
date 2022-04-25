<template>
  <div id="answerShow">
    <div id="singleChoice" v-if="question.questionType === 1" class="wrap">
      <p class="head" style="background: #8cbda4">{{ idx }}、 单选题</p>
      <div class="ql-editor" v-html="question.title"></div>
      <el-radio-group v-model="question.studentAnswer">
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
        <p>
          结果：<el-tag
            :type="questionStatus(question.status, question.questionType)"
          >
            {{ tagContent(question.status, question.questionType) }}</el-tag
          >
        </p>
        <p>得分：{{ question.studentScore }} / {{ question.score }}分</p>
        <p class="rate">
          难度：<el-rate disabled v-model="question.difficult"></el-rate>
        </p>
        <div class="analyze-box">
          <p>解析：</p>
          <div class="ql-editor" v-html="question.analyze"></div>
        </div>
        <p>正确答案：{{ question.answer }}</p>
      </div>
    </div>
    <div
      id="multipleChoice"
      v-else-if="question.questionType === 2"
      class="wrapper"
    >
      <p class="head" style="background: #e9ad69">{{ idx }}、 多选题</p>
      <div class="ql-editor" v-html="question.title"></div>
      <el-checkbox-group v-model="question.studentAnswer">
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
        <p>
          结果：<el-tag :type="questionStatus(question.status)">
            {{ tagContent(question.status) }}</el-tag
          >
        </p>
        <p>得分：{{ question.studentScore }} / {{ question.score }}分</p>
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
      </div>
    </div>
    <div id="trueFalse" class="wrapper" v-else-if="question.questionType === 3">
      <p class="head" style="background: #6a89cc">{{ idx }}、 判断题</p>
      <div class="ql-editor" v-html="question.title"></div>
      <el-radio-group v-model="question.studentAnswer">
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
        <p>
          结果：<el-tag
            :type="questionStatus(question.status, question.questionType)"
          >
            {{ tagContent(question.status, question.questionType) }}</el-tag
          >
        </p>
        <p>得分：{{ question.studentScore }} / {{ question.score }}分</p>
        <p class="rate">
          难度：<el-rate disabled v-model="question.difficult"></el-rate>
        </p>
        <div class="analyze-box">
          <p>解析：</p>
          <div class="ql-editor" v-html="question.analyze"></div>
        </div>
        <p>正确答案：{{ question.answer }}</p>
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
        <p>
          结果：<el-tag
            :type="questionStatus(question.status, question.questionType)"
          >
            {{ tagContent(question.status, question.questionType) }}</el-tag
          >
        </p>
        <div class="giveScore" v-if="question.status === 0 || edit === true">
          <span>得分：</span>
          <el-input-number
            :min="0"
            :max="question.score"
            :step="0.5"
            v-model="question.studentScore"
          ></el-input-number>
        </div>
        <p v-if="question.status === 2 && edit === false">
          得分：{{ question.studentScore }} / {{ question.score }}分
        </p>
        <p class="rate">
          难度：<el-rate disabled v-model="question.difficult"></el-rate>
        </p>
        <div class="analyze-box">
          <p>解析：</p>
          <div class="ql-editor" v-html="question.analyze"></div>
        </div>
        <p>正确答案：</p>
        <div class="ql-editor" v-html="question.analyze"></div>
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
        <p>
          结果：<el-tag :type="questionStatus(question.status)">
            {{ tagContent(question.status) }}</el-tag
          >
        </p>
        <p>得分：{{ question.studentScore }} / {{ question.score }}分</p>
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
  methods: {
    questionStatus(status, type) {
      if (status === -1) {
        return `danger`;
      } else if (status === 0) {
        return `warning`;
      } else if (status === 1) {
        return `success`;
      } else if (status === 2) {
        return ``;
      }
    },
    tagContent(status, type) {
      if (status === -1) {
        return `错误`;
      } else if (status === 0) {
        return `待批改`;
      } else if (status === 1) {
        return `正确`;
      } else if (status === 2) {
        return `已审批`;
      }
    },
  },
  watch: {
    question: {
      handler(n, o) {
        if (o.questionType === 4) {
          this.$emit("updateExam", n, o);
        }
      },
      deep: true,
    },
  },
};
</script>


<style scoped>
#answerShow {
  width: 100%;
  min-height: 200px;
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