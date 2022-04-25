<template>
  <div id="list">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="试卷名称：">
        <el-input v-model="queryParam.examTitle" clearable></el-input>
      </el-form-item>
      <el-form-item label="考试班级：">
        <el-input v-model="queryParam.examClass" clearable></el-input>
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
      <el-table-column prop="id" label="Id" width="90px" />
      <el-table-column prop="testName" label="名称" show-overflow-tooltip />
      <el-table-column prop="class" label="班级" />
      <el-table-column label="操作" align="center" width="290px">
        <template slot-scope="{ row }">
          <el-button size="mini" @click="showQuestion(row)">编辑</el-button>
          <el-button size="mini" @click="editQuestion(row)">查看</el-button>
           <el-button size="mini" @click="analyze(row)">分析</el-button>
          <!-- <el-button
            size="mini"
            type="danger"
            @click="deleteQuestion(row)"
            class="link-left"
            >删除</el-button
          > -->
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
  name: "list",
  components: { Pagination },
  data() {
    return {
      queryParam: {
        id: null,
        examTitle: "",
        examClass: "",
        pageIndex: 1,
        pageSize: 10,
      },

     
      listLoading: false,
      tableData: [
        {
          id: 1,
          testName: "第一次月考",
          questionBody: "单选题1",
          class: "高等数学一班",
        },
        {
          id: 2,
          testName: "第二次月考",
          questionBody: "多选题1",
          class: "高等数学一班",
        },
        {
          id: 3,
          testName: "第三次月考",
          questionBody: "判断题1",
          class: "高等数学一班",
        },
        {
          id: 4,
          testName: "期末考试",
          questionBody: "简答题1",
          class: "高等数学一班",
        },
        {
          id: 5,
          testName: "期末考试补考",
          questionBody: "单选题2",
          class: "高等数学一班",
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
    analyze(row){
      this.$router.push({name: 'analyze'});
    }
  },

 
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
