<template>
  <div id="create">
    <el-form :model="exam" :rules="rules" ref="exam" label-width="auto">
      <el-form-item label="考试学科：" prop="subject" required>
        <el-input placeholder="请输入考试学科" v-model="exam.subject" clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="试卷名称：" prop="title" required>
        <el-input placeholder="请输入试卷名称" v-model="exam.title" clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="注意事项：" prop="tip">
        <el-input
          type="textarea"
          placeholder="请输入考试注意事项"
          :autosize="{ minRows: 3, maxRows: 6 }"
          v-model="exam.tip"
          clearable
        >
        </el-input>
      </el-form-item>
      <el-form-item label="考试班级：" prop="examClass" required>
        <el-checkbox-group v-model="exam.examClass">
          <el-checkbox v-for="item in myClass" :label="item.id" :key="item.id">
            {{ item.name }}
          </el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="考试时间：" prop="dateMap" required>
        <el-date-picker
          v-model="exam.dateMap"
          type="datetimerange"
          value-format="yyyy-MM-dd hh:mm:ss"
          :picker-options="pickerOptions"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          align="right"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="考试时长：" prop="duration" required="">
        <el-input-number
          v-model="exam.duration"
          :min="1"
          :max="1000"
        ></el-input-number
        ><span>&nbsp;分钟</span>
      </el-form-item>
      <el-form-item
        :key="idx"
        :label="`模块${idx + 1}`"
        required
        v-for="(mod, idx) in exam.modules"
      >
        <el-input v-model="mod.title" placeholder="请输入模块名称"> </el-input>
        <el-button
          type="warning"
          class="link-left"
          icon="el-icon-circle-plus-outline"
          size="small"
          style="margin-top: 10px"
          @click="addQuestion(idx)"
          >添加题目</el-button
        >
        <el-button
          type="danger"
          class="link-left"
          icon="el-icon-delete"
          size="medium"
          style="margin-top: 10px"
          @click="deleteModule(idx)"
          >删除模块</el-button
        >
        <el-card v-if="mod.questionList.length > 0" style="margin-top: 20px">
          <el-form-item
            :key="questionIdx"
            style="margin-bottom: 15px"
            :label="`${questionIdx + 1}、`"
            v-for="(question, questionIdx) in mod.questionList"
          >
            <el-row>
              <el-col :span="20">
                <QuestionShow :question="question"></QuestionShow>
              </el-col>
              <el-col :span="4">
                <el-button
                  style="margin-left: 40px"
                  type="danger"
                  icon="el-icon-delete"
                  circle
                  @click="deleteQuestion(idx, questionIdx)"
                ></el-button>
              </el-col>
            </el-row>
          </el-form-item>
        </el-card>
      </el-form-item>
      <el-form-item>
        <el-button type="primary">提交</el-button>
        <el-button>重置</el-button>
        <el-button type="success" @click="addModule">添加模块</el-button>
      </el-form-item>
    </el-form>
    <el-dialog
      :visible.sync="questionPage.questionVisible"
      style="width: 100%; height: 100%"
    >
      <QuestionShow :question="previewQuestionItem" />
    </el-dialog>
    <el-dialog :visible.sync="questionPage.showQuestionList" width="75%">
      <el-form :model="questionPage.queryParam" ref="queryForm" :inline="true">
        <el-form-item label="题型：">
          <el-select v-model="questionPage.queryParam.questionType" clearable>
            <el-option
              v-for="(item, i) in questionTypeEnum"
              :key="i"
              :value="item.key"
              :label="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="难度：">
          <el-input
            v-model="questionPage.queryParam.difficulty"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="分数：">
          <el-input
            v-model="questionPage.queryParam.score"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="上传教师：">
          <el-input v-model="questionPage.queryParam.name" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">查询</el-button>
        </el-form-item>
      </el-form>
      <el-table
        v-loading="questionPage.listLoading"
        ref="questionList"
        :data="questionPage.questionList"
        @selection-change="handleSelectionChange"
        border
        fit
        highlight-current-row
        style="width: 100%"
      >
        <el-table-column type="selection" width="35"></el-table-column>
        <el-table-column prop="id" label="ID" width="60px"></el-table-column>
        <el-table-column
          prop="questionType"
          lebel="题型"
          :formatter="questionTypeFormatter"
          width="70px"
        ></el-table-column>
        <el-table-column
          prop="score"
          label="分数"
          show-overflow-tooltip
        ></el-table-column>
        <el-table-column prop="difficult" label="难度"></el-table-column>
        <el-table-column prop="username" label="教师账号"></el-table-column>
        <el-table-column prop="name" label="教师昵称"></el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="{ row }">
            <el-button size="mini" @click="previewQuestion(row)"
              >查看</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <pagination
        style="margin-top: 20px"
        v-show="questionPage.total > 0"
        :total="questionPage.total"
        :page.sync="questionPage.queryParam.pageIndex"
        :limit.sync="questionPage.queryParam.pageSize"
        @pagination="search"
      />

      <span slot="footer" class="dialog-footer">
        <el-button @click="questionPage.showQuestionList = false"
          >取 消</el-button
        >
        <el-button type="primary" @click="confirmQuestionSelect"
          >确定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>


<script>
import Pagination from "@/components/Pagination";
import QuestionShow from "@/components/QuestionShow/index.vue";
export default {
  name: "create",
  components: {
    Pagination,
    QuestionShow,
  },
  data() {
    return {
      previewQuestionItem: null,
      modIdx: undefined,
      questionPage: {
        total: 5,
        questionVisible: false,
        listLoading: false,
        showQuestionList: false,
        multipleSlection: [],
        pages: 1,
        questionList: [
          {
            id: 1,
            questionType: 1,
            difficult: 4,

            title: "fsfsf",
            items: [
              { prefix: "A", content: "fsfs" },
              { prefix: "B", content: "fsfsf" },
              { prefix: "C", content: "fsfs" },
              { prefix: "D", content: "fsfsf" },
            ],
            answer: "",

            analyze: "",
            score: "2",
            name: "郎老师",
            username: "langwenchong",
          },
          {
            id: 2,
            questionType: 2,
            difficult: 1,
            title: "hdhh",
            items: [
              { prefix: "A", content: "ewuhehu" },
              { prefix: "B", content: "fs" },
              { prefix: "C", content: "gdsgd" },
              { prefix: "D", content: "ujrj" },
            ],
            answer: [],
            analyze: "",
            score: "4",
            name: "郎老师",
            username: "langwenchong",
          },
          {
            id: 3,
            questionType: 3,
            difficult: 2,

            title: "jrhhrshesehdshdhd",
            items: [
              { prefix: "A", content: "<p>正确</p>" },
              { prefix: "B", content: "<p>错误</p>" },
            ],
            answer: "",

            analyze: "",
            score: "1",
            name: "郎老师",
            username: "langwenchong",
          },
          {
            id: 4,
            questionType: 4,
            difficult: 3,
            title: "fsafsfsgshgs",
            answer: "",
            analyze: "",
            score: "3",
            name: "郎老师",
            username: "langwenchong",
          },
          {
            id: 5,
            questionType: 5,
            difficult: 0,

            title: "hsahshgas",
            items: [
              { prefix: "A", content: "fs" },
              { prefix: "B", content: "fsf" },
              { prefix: "C", content: "hyehs" },
              { prefix: "D", content: "hdshds" },
            ],
            answer: [
              { prefix: "A", content: "" },
              { prefix: "B", content: "" },
              { prefix: "C", content: "" },
              { prefix: "D", content: "" },
            ],

            analyze: "",
            score: "2",
            name: "郎老师",
            username: "langwenchong",
          },
        ],
        queryParam: {
          questionType: null,
          content: "",
          score: undefined,
          difficulty: undefined,
          name: "",
          pageIndex: 1,
          pagesize: 5,
        },
      },
      questionTypeEnum: [
        {
          key: 1,
          value: "单选题",
        },
        {
          key: 2,
          value: "多选题",
        },
        {
          key: 3,
          value: "判断题",
        },
        {
          key: 4,
          value: "简答题",
        },
        {
          key: 5,
          value: "排序题",
        },
      ],
      exam: {
        title: "",
        tip: "",
        examClass: [],
        dateMap: [],
        duration: undefined,
        modules: [],
        subject: "",
      },
      myClass: [
        {
          id: 1,
          name: "软件工程1班",
        },
        {
          id: 2,
          name: "软件工程2班",
        },
        {
          id: 3,
          name: "软件工程3班",
        },
      ],
      rules: {
        title: [{ required: true, message: "请输入试卷名称", trigger: "blur" }],
        subject: [
          { required: true, message: "请输入考试科目", trigger: "blur" },
        ],
        examClass: [
          { required: true, message: "请选择考试班级", trigger: "blur" },
        ],
        dateMap: [
          { required: true, message: "请选择考试时间范围", trigger: "blur" },
        ],
        duration: [
          {
            required: true,
            message: "请输入考试时长",
            trigger: "blur",
          },
        ],
      },
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
    };
  },
  methods: {
    search() {},
    submitForm() {},
    previewQuestion(question) {
      this.previewQuestionItem = question;
      this.questionPage.questionVisible = true;
    },
    handleSelectionChange(val) {
      this.questionPage.multipleSlection = val;
      console.log(this.questionPage.multipleSlection);
    },
    questionTypeFormatter(row, column) {
      if (row.questionType === 1) {
        return `单选题`;
      } else if (row.questionType === 2) {
        return `多选题`;
      } else if (row.questionType === 3) {
        return `判断题`;
      } else if (row.questionType === 4) {
        return `简答题`;
      } else if (row.questionType === 5) {
        return `排序题`;
      }
    },
    addModule() {
      this.exam.modules.push({
        title: "",
        questionList: [],
      });
    },
    addQuestion(idx) {
      this.modIdx = idx;
      this.questionPage.showQuestionList = true;
    },
    confirmQuestionSelect() {
      var that = this;
      this.questionPage.multipleSlection.forEach((q) => {
        that.exam.modules[that.modIdx].questionList.push(q);
      });
      this.$refs.questionList.clearSelection();
      this.questionPage.showQuestionList = false;
    },
    deleteModule(idx) {
      this.exam.modules.splice(idx, 1);
    },
    deleteQuestion(idx, questionIdx) {
      this.exam.modules[idx].questionList.splice(questionIdx, 1);
    },
  },
};
</script>

<style scoped>
#create {
  width: 100%;
  min-height: 50vh;
  box-sizing: border-box;
  padding: 20px;
}
</style>
