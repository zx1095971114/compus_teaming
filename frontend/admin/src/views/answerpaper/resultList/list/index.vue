<template>
  <div id="list">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="试卷名称：">
        <el-input v-model="queryParam.examTitle" clearable></el-input>
      </el-form-item>
      <el-form-item label="用户名称：">
        <el-input v-model="queryParam.name" clearable></el-input>
      </el-form-item>
      <el-form-item label="用户账号：">
        <el-input v-model="queryParam.username" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table
      v-loading="listLoading"
      :data="tableData"
      border
      fit
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column prop="id" label="试卷Id" width="90px" />
      <el-table-column prop="testName" label="试卷名称" show-overflow-tooltip />
      <el-table-column prop="name" label="用户名称" />
      <el-table-column prop="username" label="用户账号" />
      <el-table-column prop="score" label="得分" width="80px" />
      <el-table-column prop="rate" label="题目正确率" width="100px" />
      <el-table-column prop="cost" label="耗时" width="160px" />
      <el-table-column prop="time" label="提交时间" width="160px" />
      <el-table-column label="操作" align="center" width="160px">
        <template slot-scope="{ row }">
          <el-button size="mini" @click="screenShot(row)">监控</el-button>
          <el-button size="mini" @click="showQuestion(row)">查看</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParam.pageIndex"
      :limit.sync="queryParam.pageSize"
      @pagination="search"
    />
  </div>
</template>

<script>
import Pagination from "@/components/Pagination";

export default {
  name: "examCorrect",
  components: { Pagination },
  data() {
    return {
      queryParam: {
        id: null,
        examTitle: "",
        username: "",
        name: "",
        pageIndex: 1,
        pageSize: 10,
      },

      listLoading: false,
      tableData: [
        {
          id: 1,
          testName: "第一次高数月考",
          name: "langwenchong",
          username: "3019244520",
          score: "2/15",
          rate: "1/6",
          cost: "10秒",
          time: "2020-2-22 19:09:32",
        },
        {
          id: 132,
          testName: "操作系统期末考试",
          name: "langwenchong",
          username: "3019244520",
          score: "88/100",
          rate: "26/30",
          cost: "1小时20分10秒",
          time: "2020-2-23 19:23:32",
        },
        {
          id: 2,
          testName: "第二次高数月考",
          name: "langwenchong",
          username: "3019244520",
          score: "15/15",
          rate: "6/6",
          cost: "20分10秒",
          time: "2020-3-12 19:09:32",
        },
        {
          id: 3,
          testName: "第三次高数月考",
          name: "langwenchong",
          username: "3019244520",
          score: "12/15",
          rate: "5/6",
          cost: "10分10秒",
          time: "2020-3-22 19:09:32",
        },
        {
          id: 32,
          testName: "第一次高数月考",
          name: "Guotao",
          username: "3019244999",
          score: "0/15",
          rate: "0/6",
          cost: "1秒",
          time: "2020-2-22 19:09:32",
        },
      ],

      total: 10,

      questionShow: {
        qType: 0,
        dialog: false,
        question: null,
        loading: false,
      },
    };
  },
  //   created() {
  //     this.initSubject();
  //     this.search();
  //   },
  methods: {
    screenShot(row) {
      this.$router.push({ name: "screenShot" });
    },
    submitForm() {
      //   this.queryParam.pageIndex = 1;
      //   this.search();
    },

    search() {
      //   this.listLoading = true;
      //   questionApi.pageList(this.queryParam).then((data) => {
      //     const re = data.response;
      //     this.tableData = re.list;
      //     this.total = re.total;
      //     this.queryParam.pageIndex = re.pageNum;
      //     this.listLoading = false;
      //   });
    },

    // levelChange() {
    //   this.queryParam.subjectId = null;
    //   this.subjectFilter = this.subjects.filter(
    //     (data) => data.level === this.queryParam.level
    //   );
    // },
    // addQuestion() {
    //   this.$router.push("/exam/question/edit/singleChoice");
    // },

    showQuestion(row) {
      this.$router.push({ name: "check", params: { edit: false } });
      //   let _this = this;
      //   this.questionShow.dialog = true;
      //   this.questionShow.loading = true;
      //   questionApi.select(row.id).then((re) => {
      //     _this.questionShow.qType = re.response.questionType;
      //     _this.questionShow.question = re.response;
      //     _this.questionShow.loading = false;
      //   });
    },

    editQuestion(row) {
      //   let url = this.enumFormat(this.editUrlEnum, row.questionType);
      //   this.$router.push({ path: url, query: { id: row.id } });
    },

    deleteQuestion(row) {
      //   let _this = this;
      //   questionApi.deleteQuestion(row.id).then((re) => {
      //     if (re.code === 1) {
      //       _this.search();
      //       _this.$message.success(re.message);
      //     } else {
      //       _this.$message.error(re.message);
      //     }
      //   });
    },

    // questionTypeFormatter(row, column, cellValue, index) {
    //   return this.enumFormat(this.questionType, cellValue);
    // },
    // subjectFormatter(row, column, cellValue, index) {
    //   return this.subjectEnumFormat(cellValue);
    // },
    // ...mapActions("exam", { initSubject: "initSubject" }),
  },

  //   computed: {
  //     ...mapGetters("enumItem", ["enumFormat"]),
  //     ...mapState("enumItem", {
  //       questionType: (state) => state.exam.question.typeEnum,
  //       editUrlEnum: (state) => state.exam.question.editUrlEnum,
  //       levelEnum: (state) => state.user.levelEnum,
  //     }),
  //     ...mapGetters("exam", ["subjectEnumFormat"]),
  //     ...mapState("exam", { subjects: (state) => state.subjects }),
  //   },
};
</script>

<style scoped>
#list {
  width: 100%;
  min-height: 50vh;
  box-sizing: border-box;
  padding: 20px;
}
</style>
