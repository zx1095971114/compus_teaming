<template>
  <div id="settings">
    <el-card shadow="hover">
      <div slot="header" id="header">
        <span>资料卡</span>
      </div>

      <el-form
        class="animate__animated animate__fadeInUp"
        ref="myInfoForm"
        :model="myInfoForm"
        label-width="100px"
        label-position="right"
        :rules="rules"
      >
        <el-form-item label="用户昵称" class="input" prop="name">
          <el-input v-model="myInfoForm.name"></el-input>
        </el-form-item>

        <el-form-item label="性别" prop="sex">
          <el-select v-model="myInfoForm.sex" placeholder="请选择性别">
            <el-option label="男" value="man"></el-option>
            <el-option label="女" value="woman"></el-option>
            <el-option label="保密" value="secrecy"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="邮箱" prop="eamil">
          <el-input v-model="myInfoForm.eamil" class="input"></el-input>
        </el-form-item>

        <el-form-item label="就读学校" prop="school">
          <el-input v-model="myInfoForm.school" class="input"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('myInfoForm')"
            >更新</el-button
          >
          <el-button @click="resetForm('myInfoForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card shadow="hover">
      <div slot="header" id="header">
        <span>修改密码</span>
      </div>

      <el-form
        class="animate__animated animate__fadeInUp"
        :model="passwordForm"
        ref="passwordForm"
        label-width="100px"
        label-position="right"
        :rules="pwdRules"
      >
        <el-form-item label="旧密码" prop="oldPwd" class="passwordInput">
          <el-input
            type="password"
            v-model="passwordForm.oldPwd"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item label="新密码" prop="newPwd" class="passwordInput">
          <el-input
            type="password"
            v-model="passwordForm.newPwd"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item label="确认密码" prop="checkPwd" class="passwordInput">
          <el-input
            type="password"
            v-model="passwordForm.checkPwd"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitPwdForm('passwordForm')"
            >确认修改</el-button
          >
          <el-button @click="resetForm('passwordForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "settings",
  data() {
    var validateName = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入用户昵称"));
      } else {
        if (value.trim() === "") {
          callback(new Error("用户昵称至少要包括一位非空符号"));
        }
        callback();
      }
    };

    var validateSex = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请选择性别"));
      }
      callback();
    };

    var validateEamil = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入邮箱"));
      }
      callback();
    };

    var validateSchool = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入就读学校"));
      }
      callback();
    };

    var validateOldPwd = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入旧密码"));
      }
      callback();
    };

    var validateNewPwd = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入新密码"));
      } else if (value.length < 8) {
        callback(new Error("密码长度小于8！"));
      }
      callback();
    };

    var validateCheckPwd = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请确认新密码"));
      } else {
        if (value !== this.passwordForm.newPwd) {
          callback(new Error("两次输入密码不一致!"));
        }
      }
      callback();
    };

    return {
      myInfoForm: {
        name: "",
        sex: "",
        eamil: "",
        school: "",
      },

      dialogFormVisible: false,

      passwordForm: {
        oldPwd: "",
        newPwd: "",
        checkPwd: "",
      },

      rules: {
        name: [{ validator: validateName, trigger: "blur" }],
        sex: [{ validator: validateSex, trigger: "blur" }],
        eamil: [{ validator: validateEamil, trigger: "blur" }],
        school: [{ validator: validateSchool, trigger: "blur" }],
      },

      pwdRules: {
        oldPwd: [{ validator: validateOldPwd, trigger: "blur" }],
        newPwd: [{ validator: validateNewPwd, trigger: "blur" }],
        checkPwd: [{ validator: validateCheckPwd, trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.apis.account
            .edituserinfo(
              sessionStorage.getItem("username"),
              this.myInfoForm.name,
              this.myInfoForm.sex,
              this.myInfoForm.eamil,
              this.myInfoForm.school
            )
            .then((res) => {
              if (res.data.status === 200) {
                this.$message({
                  message: "更新成功",
                  type: "success",
                });
              }
            });
        } else {
          this.$message.error("更新失败");
        }
      });
    },

    submitPwdForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.apis.account
            .editpwd(
              sessionStorage.getItem("username"),
              this.passwordForm.oldPwd,
              this.passwordForm.newPwd
            )
            .then((res) => {
              // console.log(res);
              if (res.data.status === 200) {
                this.$message({
                  message: "修改密码成功",
                  type: "success",
                });
              } else if (res.data.status === 403) {
                this.$notify.error({
                  title: "错误",
                  message: "旧密码错误，认证失败！",
                });
              }
            });
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
#settings {
  width: 100%;
  min-height: 100vh;
  min-height: 200px;
  /* background: pink; */
}
#header {
  margin: 0 30px;
  font-weight: 800;
}

.input {
  width: 400px;
}

.passwordInput {
  width: 500px;
}

.el-card {
  margin: 30px 100px;
  border-radius: 8px;
}
</style>