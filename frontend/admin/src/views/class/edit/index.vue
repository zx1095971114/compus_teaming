<template>
  <div id="edit">
    <el-form
      :model="ruleForm"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
      :rules="rules"
    >
      <el-form-item label="修改班级名称" prop="className">
        <el-input v-model="ruleForm.className"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')"
          >提交</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 查询框 -->
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="账号：">
        <el-input v-model="queryParam.userName"></el-input>
      </el-form-item>
      <el-form-item label="用户昵称：">
        <el-input v-model="queryParam.name"></el-input>
      </el-form-item>
      <el-form-item label="邮箱：">
        <el-input v-model="queryParam.email"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
      </el-form-item>
    </el-form>
    <!-- table -->
    <el-table
      :data="userData"
      v-loading="listLoading"
      border
      fit
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="userName" label="账号"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column label="操作" align="center">
        <template>
          <el-button size="mini" type="danger">移出</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 页码下标 -->
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
  name: "edit",
  components: {
    Pagination,
  },
  data() {
    var validateClassName = (rule, value, callback) => {
      if (value === ``) {
        callback("班级名称不能为空");
      } else if (value.trim() === ``) {
        callback(`班级名称至少要包含一个非空字符`);
      } else {
        callback();
      }
    };
    return {
      id: 0,
      ruleForm: {
        className: "",
      },
      rules: {
        className: [{ validator: validateClassName, trigger: "blur" }],
      },
      queryParam: {
        userName: "",
        name: "",
        email: "",
        role: 1,
        pageIndex: 1,
        pageSize: 10,
      },
      listLoading: true,
      userData: [],
      total: 0,
    };
  },
  created() {
    this.listLoading = true;
    this.userData = [
      {
        id: 1,
        userName: "301901",
        name: "泰达米尔",
        sex: "男",
        email: "1234@dasd",
      },
      {
        id: 2,
        userName: "301902",
        name: "沃里克",
        sex: "男",
        email: "12345@dasd",
      },
      {
        id: 3,
        userName: "301903",
        name: "艾希",
        sex: "女",
        email: "123456@dasd",
      },
      {
        id: 4,
        userName: "301904",
        name: "卡萨丁",
        sex: "不详",
        email: "1234567@dasd",
      },
      {
        id: 5,
        userName: "301905",
        name: "亚索",
        sex: "男",
        email: "12345678@dasd",
      },
      {
        id: 6,
        userName: "301906",
        name: "金克斯",
        sex: "女",
        email: "123456789@dasd",
      },
    ];
    this.total = 6;
    this.queryParam.pageIndex = 1;
    this.listLoading = false;
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert("submit!");
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    search() {
      this.listLoading = true;
      this.userData = [
        {
          id: 1,
          userName: "301901",
          name: "泰达米尔",
          sex: "男",
          email: "1234@dasd",
        },
        {
          id: 2,
          userName: "301902",
          name: "沃里克",
          sex: "男",
          email: "12345@dasd",
        },
        {
          id: 3,
          userName: "301903",
          name: "艾希",
          sex: "女",
          email: "123456@dasd",
        },
        {
          id: 4,
          userName: "301904",
          name: "卡萨丁",
          sex: "不详",
          email: "1234567@dasd",
        },
        {
          id: 5,
          userName: "301905",
          name: "亚索",
          sex: "男",
          email: "12345678@dasd",
        },
      ];
      this.total = 5;
      this.queryParam.pageIndex = 1;
      this.listLoading = false;
    },
  },
};
</script>

<style scoped>
#edit {
  width: 100%;
  min-height: 100vh;
  box-sizing: border-box;
  padding: 20px;
}
</style>