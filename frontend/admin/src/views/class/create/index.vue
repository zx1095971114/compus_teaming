<template>
  <div id="create">
    <el-form
      :model="ruleForm"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
      :rules="rules"
    >
      <el-form-item label="班级名称" prop="className">
        <el-input v-model="ruleForm.className"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')"
          >提交</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "create",
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
      ruleForm: {
        className: "",
      },
      rules: {
        className: [{ validator: validateClassName, trigger: "blur" }],
      },
    };
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
  },
};
</script>

<style scoped>
#create {
  width: 100%;
  min-height: 100vh;
  box-sizing: border-box;
  padding: 20px;
  
}
</style>