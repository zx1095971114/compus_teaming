<template>
  <div id="account">
    <div class="tip">
      欢迎加入本教考系统,初次创建账户您还需要完善以下个人信息！
    </div>
    <div class="l-wrapper">
      <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-width="auto"
        class="ruleForm"
      >
        <el-form-item prop="name">
          <el-input
            type="text"
            v-model="ruleForm.name"
            autocomplete="off"
            placeholder="请输入用户昵称(最大长度不能超过8位)"
            ><template slot="prepend">用户昵称:</template></el-input
          >
        </el-form-item>
        <el-form-item prop="phoneNumber">
          <el-input
            type="text"
            v-model="ruleForm.phoneNumber"
            autocomplete="off"
            placeholder="请输入手机号(长度为11位)"
            ><template slot="prepend">手机号码: +86</template></el-input
          >
        </el-form-item>
        <el-form-item prop="school">
          <el-input
            type="text"
            v-model="ruleForm.school"
            autocomplete="off"
            placeholder="请输入所就读学校名称"
            ><template slot="prepend">学校名称:</template></el-input
          >
        </el-form-item>
        <el-form-item prop="sex">
          <el-select v-model="ruleForm.sex" placeholder="请选择您的性别">
            <el-option label="男士" value="male"></el-option>
            <el-option label="女士" value="female"></el-option>
            <el-option label="不愿透露" value="none"></el-option>
          </el-select>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
      <div class="control">
        <div class="button" @click="submitForm('ruleForm')">提交信息</div>
        <router-link :to="{ name: 'sign' }" class="link"
          >已有账号？ 返回登录</router-link
        >
      </div>
    </div>
    <div class="r-wrapper">
      <div class="info">
        为了方便进行个人人脸识别认证，请您在这里上传个人半身照，如下示例图所示:
      </div>
      <div class="ref" @mouseover="upload = true" @mouseout="upload = false">
        <img
          v-show="!upload"
          src="../../../static/images/welcome/reference.png"
          class="animate__animated animate__flipInX"
          height="164px"
          width="auto"
        />
        <div class="upload animate__animated animate__flipInX" v-show="upload">
          <el-upload
            class="avatar-uploader"
            action="sb"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            ref="uploader"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
        <ol>
          <li>请不要佩戴口罩遮挡面部饰品</li>
          <li>人像居中且背景图白底最佳</li>
        </ol>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "account",
  data() {
    var validateName = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入用户昵称"));
      } else if (value.trim() === "") {
        callback(new Error("用户昵称至少要包括一位非空符号"));
      } else {
        callback();
      }
    };
    var validatePhoneNumber = (rule, value, callback) => {
      var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
      if (value === "") {
        callback(new Error("请输入手机号码"));
      } else if (value.length != 11 || !myreg.test(value)) {
        callback(new Error("请输入正确的手机号码"));
      } else {
        callback();
      }
    };
    var validateSchool = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入就读学校"));
      } else if (value.trim() === "") {
        callback(new Error("至少要包括一位非空符号"));
      } else {
        callback();
      }
    };
    return {
      imageUrl: "",
      upload: false,
      ruleForm: {
        name: "",
        phoneNumber: "",
        school: "",
        sex: "",
      },
      avatar: null,
      rules: {
        name: [{ validator: validateName, trigger: "blur" }],
        phoneNumber: [{ validator: validatePhoneNumber, trigger: "blur" }],
        school: [{ validator: validateSchool, trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let username = this.$route.params.username;
          let email = this.$route.params.email;
          let password = this.$route.params.password;
          this.$refs.uploader.submit();
          this.apis.welcome
            .register(this.ruleForm, this.avatar, username, email, password)
            .then((res) => {
              // console.log(res);
              if (res.data.status === 200) {
                this.$notify({
                  title: "成功",
                  message: "账号注册成功！",
                  type: "success",
                });
                this.$router.push({ name: "login" });
              } else {
                this.$notify.error({
                  title: "错误",
                  message: "账号注册失败！",
                });
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      this.avatar = file;
      this.imageUrl = URL.createObjectURL(file);
      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
  },
};
</script>

<style scoped>
#account {
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: #fff;
  width: 1050px;
  height: 515px;
  position: absolute;
  border-radius: 5px;
  box-shadow: 5px 10px 20px 0px rgba(175 187 204 / 30%);
  padding: 40px;
  box-sizing: border-box;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.tip {
  position: absolute;
  top: 40px;
  font-size: 1.2rem;
  font-weight: 500;
  font-family: SentyGoldenBell;
  color: #636e72;
}
.l-wrapper {
  width: 65%;
  height: 100%;
  /* background: green; */
  box-sizing: border-box;
  padding: 60px 40px 0 0;
  display: flex;
  /* justify-content: stretch; */
  align-items: center;
  flex-direction: column;
}
.ruleForm {
  width: 100%;
}
.control {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.button {
  display: flex;
  justify-content: center;
  text-align: center;
  align-items: center;
  box-sizing: border-box;
  width: 360px;
  height: 48px;
  background: url("../../../static/images/welcome/button-bg.png") no-repeat 0 0;
  background-size: cover;
  box-shadow: 0 3px 12px 0 rgb(39 125 255 / 30%);
  border-radius: 24px;
  font-size: 18px;
  color: #ffffff;
  outline: none;
  cursor: pointer;
  border: none;
}
.button:hover {
  background-position: 0 -48px;
  background-size: cover;
}
.link {
  color: #40485b;
  transition: 0.5s;
  font-size: 14px;
  text-decoration: none;
}
.link:hover {
  color: #ff7675;
}
.r-wrapper {
  width: 35%;
  height: 100%;
  box-sizing: border-box;
  padding: 0 0 0 40px;
}
.info {
  margin-top: 30px;
  margin-bottom: 20px;
  box-sizing: border-box;
  padding-left: 20px;
  position: relative;
}
.info:before {
  content: "";
  height: 75px;
  width: 110px;
  top: -40px;
  left: -70px;
  position: absolute;
  transform: scale(0.5);
  background-image: url("../../../static/images/welcome/icon_quote.svg");
  background-size: cover;
  background-repeat: no-repeat;
}
.ref {
  overflow: hidden;
  margin: 0 auto;
  display: flex;
  align-items: center;
  flex-direction: column;
  box-sizing: border-box;
  width: 90%;
  padding: 20px 10px;
  border: 1px #333 dashed;
}
ol {
  margin: 0;
  margin-top: 20px;
}
</style>