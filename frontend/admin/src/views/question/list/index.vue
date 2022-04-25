<template>
  <div id="list">
    <!-- 查询框 -->
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="上传教师：">
        <el-input v-model="queryParam.teacherName"></el-input>
      </el-form-item>
      <el-form-item label="难度：">
        <el-input v-model="queryParam.difficult"></el-input>
      </el-form-item>
      <el-form-item label="分数：">
        <el-input v-model="queryParam.score"></el-input>
      </el-form-item>
      <el-form-item label="题型：">
        <el-select
          v-model="queryParam.type"
          placeholder="请选择"
          clearable
          style="width: 90px"
          class="filter-item"
        >
          <el-option
            v-for="item in typeOptions"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
      </el-form-item>
    </el-form>

    <!-- 表单 -->
    <el-table
      :data="questionData"
      v-loading="listLoading"
      border
      fit
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="type" label="题型"></el-table-column>
      <el-table-column prop="questionBody" label="题干"></el-table-column>
      <el-table-column prop="score" label="分数"></el-table-column>
      <el-table-column prop="difficult" label="难度" width="250px">
        <template slot-scope="{ row }">
          <div class="rate-wrapper">
            <el-rate v-model="row.difficult" disabled> </el-rate
            ><span style="color: #ff9900">{{ row.difficult }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="teacherUsername" label="教师"></el-table-column>
      <el-table-column prop="name" label="教师昵称"></el-table-column>
      <el-table-column label="操作" align="center" width="200px">
        <template>
          <el-button size="mini">预览</el-button>
          <el-button size="mini">编辑</el-button>
          <!-- <el-button size="mini" type="danger" class="link-left"
            >删除</el-button
          > -->
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
  name: "list",
  components: {
    Pagination,
  },
  data() {
    return {
      queryParam: {
        teacherName: "",
        score: "",
        type: "",
        difficult: undefined,
        role: 1,
        pageIndex: 1,
        pageSize: 10,
      },
      listLoading: true,
      questionData: [],
      total: 0,
      typeOptions: ["单选题", "多选题", "判断题", "简答题"],
    };
  },
  created() {
    this.listLoading = true;
    this.questionData = [
      {
        id: 1,
        type: "单选题",
        questionBody: "单选题1",
        score: "2",
        difficult: 1,
        teacherUsername: "朗文翀",
        name: "朗文翀",
      },
      {
        id: 2,
        type: "多选题",
        questionBody: "多选题1",
        score: "2",
        difficult: 2,
        teacherUsername: "朗文翀",
        name: "朗文翀",
      },
      {
        id: 3,
        type: "判断题",
        questionBody: "判断题1",
        score: "1",
        difficult: 1,
        teacherUsername: "朗文翀",
        name: "朗文翀",
      },
      {
        id: 4,
        type: "简答题",
        questionBody: "简答题1",
        score: "5",
        difficult: 3.1,
        teacherUsername: "朗文翀",
        name: "朗文翀",
      },
      {
        id: 5,
        type: "单选题",
        questionBody: "单选题2",
        score: "1",
        difficult: 2.5,
        teacherUsername: "朗文翀",
        name: "朗文翀",
      },
      {
        id: 6,
        type: "多选题",
        questionBody: "多选题2",
        score: "2",
        difficult: 3,
        teacherUsername: "朗文翀",
        name: "朗文翀",
      },
    ];
    this.total = 6;
    this.queryParam.pageIndex = 1;
    this.listLoading = false;
  },
  methods: {
    search() {
      this.listLoading = true;
      this.questionData = [
        {
          id: 1,
          type: "单选题",
          questionBody: "单选题1",
          score: "2",
          difficult: 1,
          teacherUsername: "朗文翀",
          name: "朗文翀",
        },
        {
          id: 2,
          type: "多选题",
          questionBody: "多选题1",
          score: "2",
          difficult: 2,
          teacherUsername: "朗文翀",
          name: "朗文翀",
        },
        {
          id: 3,
          type: "判断题",
          questionBody: "判断题1",
          score: "1",
          difficult: 1,
          teacherUsername: "朗文翀",
          name: "朗文翀",
        },
        {
          id: 4,
          type: "简答题",
          questionBody: "简答题1",
          score: "5",
          difficult: 3.1,
          teacherUsername: "朗文翀",
          name: "朗文翀",
        },
        {
          id: 5,
          type: "单选题",
          questionBody: "单选题2",
          score: "1",
          difficult: 2.5,
          teacherUsername: "朗文翀",
          name: "朗文翀",
        },
         {
          id: 6,
          type: "单选题",
          questionBody: "单选题2",
          score: "1",
          difficult: 2.5,
          teacherUsername: "朗文翀",
          name: "朗文翀",
        },
      ];
      this.total = 6;
      this.queryParam.pageIndex = 1;
      this.listLoading = false;
    },
  },
};
</script>

<style scoped>
#list {
  width: 100%;
  min-height: 50vh;
  box-sizing: border-box;
  padding: 20px;
  /* background: red; */
}
.rate-wrapper {
  display: flex;
  align-items: center;
  width: 200px;
}
</style>