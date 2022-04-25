<template>
  <div id="user">
    <!-- 查询框 -->
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="账号：">
        <el-input v-model="queryParam.username"></el-input>
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
      <el-table-column prop="username" label="账号"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column label="操作" align="center">
        <template>
          <el-button size="mini" type="success">邀请加入班级</el-button>
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
  components: {
    Pagination,
  },
  name: "user",
  data() {
    return {
      queryParam: {
        username: "",
        name: "",
        email: "",
        pageIndex: 1,
        pageSize: 10,
      },
      listLoading: true,
      userData: [],
      pages:1,
      total: 0,
    };
  },
  created() {
    this.listLoading = true;
    this.userData = [
      {
        id: 1,
        username: "301901",
        name: "泰达米尔",
        sex: "男",
        email: "1234@dasd",
      },
      {
        id: 2,
        username: "301902",
        name: "沃里克",
        sex: "男",
        email: "12345@dasd",
      },
      {
        id: 3,
        username: "301903",
        name: "艾希",
        sex: "女",
        email: "123456@dasd",
      },
      {
        id: 4,
        username: "301904",
        name: "卡萨丁",
        sex: "不详",
        email: "1234567@dasd",
      },
      {
        id: 5,
        username: "301905",
        name: "亚索",
        sex: "男",
        email: "12345678@dasd",
      },
      {
        id: 6,
        username: "301906",
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
    search() {
      this.listLoading = true;
      this.userData = [
        {
          id: 1,
          username: "301901",
          name: "泰达米尔",
          sex: "男",
          email: "1234@dasd",
        },
        {
          id: 2,
          username: "301902",
          name: "沃里克",
          sex: "男",
          email: "12345@dasd",
        },
        {
          id: 3,
          username: "301903",
          name: "艾希",
          sex: "女",
          email: "123456@dasd",
        },
        {
          id: 4,
          username: "301904",
          name: "卡萨丁",
          sex: "不详",
          email: "1234567@dasd",
        },
        {
          id: 5,
          username: "301905",
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
#user {
  width: 100%;
  min-height: 50vh;
  box-sizing: border-box;
  padding: 20px;
}
</style>