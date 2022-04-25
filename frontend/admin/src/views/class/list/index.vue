<template>
  <div id="list">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="班级名：">
        <el-input v-model="queryParam.className"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :data="classData"
      v-loading="listLoading"
      border
      fit
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="className" label="班级名称"></el-table-column>
      <el-table-column prop="password" label="班级口令"></el-table-column>
      <el-table-column prop="num" label="班级人数"></el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="{ row }">
          <router-link
            :to="{ path: '/class/edit', query: { id: row.id } }"
            class="link-left"
          >
            <el-button size="mini">编辑</el-button>
          </router-link>
          <el-button size="mini" type="danger" class="link-left"
            >解散</el-button
          >
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
  components: {
    Pagination,
  },
  name: "list",
  data() {
    return {
      queryParam: {
        className: "",
        pageIndex: 1,
        pageSize: 10,
      },
      listLoading: true,
      classData: [],
      pages:30,
      total: 0,
    };
  },
  created() {
    this.listLoading = true;
    this.classData = [
      {
        id: 1,
        className: "1班",
        password: "1",
        num: 200,
      },
      {
        id: 2,
        className: "2班",
        password: "2",
        num: 200,
      },
      {
        id: 3,
        className: "3班",
        password: "3",
        num: 200,
      },
      {
        id: 4,
        className: "4班",
        password: "4",
        num: 200,
      },
      {
        id: 5,
        className: "5班",
        password: "5",
        num: 200,
      },
      {
        id: 6,
        className: "6班",
        password: "6",
        num: 200,
      },
    ];
    this.total = 6;
    this.queryParam.pageIndex = 1;
    this.listLoading = false;
  },
  methods: {
    search() {
      this.listLoading = true;
      this.classData = [
        {
          id: 1,
          className: "1班",
          password: "1",
          num: 200,
        },
        {
          id: 2,
          className: "2班",
          password: "2",
          num: 200,
        },
        {
          id: 3,
          className: "3班",
          password: "3",
          num: 200,
        },
        {
          id: 4,
          className: "4班",
          password: "4",
          num: 200,
        },
        {
          id: 5,
          className: "5班",
          password: "5",
          num: 200,
        },
        {
        id: 6,
        className: "6班",
        password: "6",
        num: 200,
      },
      ];
      this.total = 6;
      this.queryParam.pageIndex = 2;
      this.listLoading = false;
    },
  },
};
</script>

<style scoped>
#list {
  width: 100%;
  min-height: 100vh;
  box-sizing: border-box;
  padding: 20px;
}
</style>