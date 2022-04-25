<template>
  <div id="questionShow">
    <div
      v-if="question.questionType === 1"
      id="singleChoice-wrapper"
      class="wrapper"
    >
      <p class="head" style="background: #8cbda4">
        [{{ question.score }}分] 单选题
      </p>
      <div class="ql-editor" v-html="question.title"></div>
      <el-radio-group v-model="radio">
        <div v-for="(el, idx) in question.items" :key="idx">
          <el-radio :label="el.prefix">
            <div class="radio">
              <span class="ql-editor">{{ el.prefix }}.</span>
              <div class="ql-editor" v-html="el.content"></div>
            </div>
          </el-radio>
        </div>
      </el-radio-group>
    </div>
    <div
      v-else-if="question.questionType === 2"
      id="multipleChoice"
      class="wrapper"
    >
      <p class="head" style="background: #e9ad69">
        [{{ question.score }}分] 多选题
      </p>
      <div class="ql-editor" v-html="question.title"></div>
      <el-checkbox-group v-model="checkList">
        <div v-for="(el, idx) in question.items" :key="idx">
          <el-checkbox :label="el.prefix">
            <div class="checkbox" ql-container ql-show>
              <span class="ql-editor">{{ el.prefix }}.</span>
              <div class="ql-editor" v-html="el.content"></div>
            </div>
          </el-checkbox>
        </div>
      </el-checkbox-group>
    </div>
    <div v-else-if="question.questionType === 3" id="trueFalse" class="wrapper">
      <p class="head" style="background: #6a89cc">
        [{{ question.score }}分] 判断题
      </p>
      <div class="ql-editor" v-html="question.title"></div>
      <el-radio-group v-model="radio">
        <div v-for="(el, idx) in question.items" :key="idx">
          <el-radio :label="el.prefix">
            <div class="radio" ql-container ql-show>
              <span class="ql-editor">{{ el.prefix }}.</span>
              <div class="ql-editor" v-html="el.content"></div>
            </div>
          </el-radio>
        </div>
      </el-radio-group>
    </div>
    <div
      v-else-if="question.questionType === 4"
      id="shortAnswer"
      class="wrapper"
    >
      <p class="head" style="background: #cf6a87">
        [{{ question.score }}分] 简答题
      </p>
      <div class="ql-editor" v-html="question.title"></div>
      <el-input
        type="textarea"
        :min="3"
        :max="6"
        placeholder="请输入答案"
        v-model="ans"
      >
      </el-input>
    </div>
    <div v-else-if="question.questionType === 5" id="sort" class="wrapper">
      <p class="head" style="background: #596275">
        [{{ question.score }}分] 排序题
      </p>
      <draggable v-model="arr">
        <transition-group>
          <div
            class="answerBox"
            v-for="element in arr"
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
</template>


<script>
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import draggable from "vuedraggable";
export default {
  name: "questionShow",
  props: {
    question: Object,
  },
  components: {
    draggable,
  },
  data() {
    return {
      radio: "",
      checkList: [],
      ans: "",
      arr: this.question.items,
    };
  },
};
</script>

<style scoped>
#questionShow {
  width: 100%;
  min-height: 200px;
}
.wrapper {
  width: 100%;
  display: flex;
  flex-direction: column;
}
.head {
  margin-top: 0;
  padding: 8px;
  color: #fff;
  font-size: 16px;
  line-height: 34px;
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
.checkbox {
  display: flex;
}
.checkbox p {
  margin-block-start: 0 !important;
  margin-block-end: 0 !important;
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
