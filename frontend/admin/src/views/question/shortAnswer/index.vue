<template>
  <div id="shortAnswer">
    <el-form
      :model="question"
      :rules="rules"
      ref="question"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="题干：" prop="title" required>
        <RichEditor
          :id="0"
          :cont="question.title"
          @updateContent="updateContent"
        ></RichEditor>
      </el-form-item>

      <el-form-item label="答案：" prop="answer" required>
        <RichEditor
          :id="1"
          :cont="question.answer"
          @updateContent="updateContent"
        ></RichEditor>
      </el-form-item>

      <el-form-item label="解析：" prop="analyze" required>
        <RichEditor
          :id="2"
          :cont="question.analyze"
          @updateContent="updateContent"
        ></RichEditor>
      </el-form-item>

      <el-form-item label="分数：" prop="score" required>
        <el-input-number
          :min="0"
          :step="0.5"
          v-model="question.score"
        ></el-input-number>
      </el-form-item>

      <el-form-item label="难度：" prop="difficult" required>
        <el-rate
          v-model="question.difficult"
          class="question-item-rate"
        ></el-rate>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitQuestion">提交</el-button>
        <el-button @click="resetQuestion">重置</el-button>
        <el-button type="success" @click="questionVisible = true"
          >预览题目</el-button
        >
      </el-form-item>
    </el-form>
    <el-dialog
      :visible.sync="questionVisible"
      style="width: 100%; height: 100%"
    >
      <QuestionShow :question="question" />
    </el-dialog>
  </div>
</template>

<script>
import RichEditor from "@/components/RichEditor/index.vue";
import QuestionShow from "@/components/QuestionShow/index.vue";
export default {
  name: "trueFalse",
  components: {
    RichEditor,
    QuestionShow,
  },
  data() {
    return {
      question: {
        id: null,
        questionType: 4,
        difficult: 0,
        title: "",
        answer: "",
        analyze: "",
        score: "",
      },
      formLoading: false,
      rules: {
        title: [{ required: true, message: "请输入题干", trigger: "blur" }],
        answer: [{ required: true, message: "请输入答案", trigger: "blur" }],
        analyze: [{ required: true, message: "请输入解析", trigger: "blur" }],
      },
      questionVisible: false,
    };
  },
  methods: {
    updateContent({ id, content }) {
      if (id === 0) {
        this.question.title = content;
      } else if (id === 1) {
        this.question.answer = content;
      } else if (id === 2) {
        this.question.analyze = content;
      }
    },
    submitQuestion() {
      let that = this;
      this.$refs.question.validate((valid) => {
        if (valid) {
        } else {
          return false;
        }
      });
    },
    resetQuestion() {
      let lastId = this.question.id;
      this.$refs.question.resetFields();
      this.question = {
        id: null,
        questionType: 3,
        difficult: 0,
        title: "",
        answer: "",
        analyze: "",
        score: "",
      };
    },
    showQuestion() {},
  },
};
</script>

<style scoped>
#shortAnswer {
  width: 100%;
  min-height: 50vh;
  box-sizing: border-box;
  padding: 20px;
}
</style>