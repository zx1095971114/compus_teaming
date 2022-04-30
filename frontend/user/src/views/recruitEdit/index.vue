<template>
  <div id="recruitEdit">
    <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible"
      width="30%"
      center
    >
      <span
        >确定要退出吗？您还未保存/发布修改信息，此时退出之前的操作将不会被保存！</span
      >
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="backup">确 定</el-button>
      </span>
    </el-dialog>
    <div id="l-main" class="animate__animated animate__fadeIn">
      <el-upload
        style="display: none"
        class="quill-picture-uploader"
        action="a"
        :before-upload="bfUpload"
        with-credentials
      >
      </el-upload>
      <quill-editor
        class="editor"
        v-model="content"
        ref="myQuillEditor"
        :options="editorOption"
        @change="onEditorChange($event)"
      />
      <!-- <div class="output code" style="display: none">
        <code class="hljs" v-html="contentCode"></code>
      </div> -->
    </div>
    <div id="r-nav" class="animate__animated animate__fadeInRight">
      <div class="backup" @click="centerDialogVisible = true">
        <strong
          ><i class="fas fa-arrow-alt-left"></i>&nbsp;&nbsp;退出编辑</strong
        >
      </div>
      <div class="box">
        <strong>话题标题(必填)</strong>
        <el-input
          type="textarea"
          :autosize="{ minRows: 1, maxRows: 4 }"
          placeholder="请输入话题标题(大约在20左右)"
          v-model="title"
        >
        </el-input>
      </div>
      <div class="box">
        <strong>话题简介(必填)</strong>
        <el-input
          type="textarea"
          :autosize="{ minRows: 6, maxRows: 8 }"
          placeholder="请输入话题简介内容(大约在100字左右)"
          v-model="description"
        >
        </el-input>
      </div>
      <div class="box">
        <strong>话题封面(选填)</strong>
        <el-upload
          class="upload"
          :show-file-list="true"
          ref="coverImageUpload"
          drag
          action="a"
          :multiple="false"
          :auto-upload="false"
          :limit="1"
          :before-upload="beforeUpload"
          accept=".png,  .jpg, "
        >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">
            将文件拖到此处，或
            <em>点击上传</em>
          </div>
        </el-upload>
      </div>
      <div class="submit" @click="submit">
        <strong><i class="fad fa-paper-plane"></i>&nbsp;&nbsp;发布话题</strong>
      </div>
    </div>
  </div>
</template>


<script>
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import hljs from "highlight.js";

// highlight.js style
import "highlight.js/styles/github-dark.css";
import { quillEditor } from "vue-quill-editor";

export default {
  components: {
    quillEditor,
  },
  name: "recruitEdit",
  created() {
    const url = `https://vclass.api.cheeseburgerim.space/topic/api/addTopicId?username=${sessionStorage.getItem(
      "userName"
    )}`;
    fetch(url, {
      method: "get",
      credentials: "include",
    })
      .then((res) => res.text())
      .then((data) => {
        this.tid = data;
      });
  },
  data() {
    const toolbarOptions = [
      ["bold", "italic", "underline", "strike"],
      ["blockquote", "code-block"],
      [{ header: 1 }, { header: 2 }],
      [{ list: "ordered" }, { list: "bullet" }],
      [{ script: "sub" }, { script: "super" }],
      [{ size: ["small", false, "large", "huge"] }],
      [{ header: [1, 2, 3, 4, 5, 6, false] }],
      [{ color: [] }, { background: [] }],
      [{ align: [] }],
      ["link", "image"],
    ];

    return {
      coverImage: null,
      tid: ``,
      centerDialogVisible: false,
      title: ``,
      description: ``,
      content: "", // 富文本里的内容
      editorOption: {
        // 富文本编辑器的工具栏
        modules: {
          toolbar: {
            container: toolbarOptions, // 工具栏
            handlers: {
              image: function (value) {
                //替换原图片上传功能
                if (value) {
                  document
                    .querySelector(".quill-picture-uploader input")
                    .click(); //核心
                } else {
                  this.quill.format("image", false);
                }
              },
            },
          },
          syntax: {
            highlight: (text) => hljs.highlightAuto(text).value,
          },
        },
        placeholder: "请输入你的内容",
      },
    };
  },
  methods: {
    beforeUpload(file) {
      // console.log(file);
      this.coverImage = file;
    },
    backup() {
      this.$router.push({ name: "TopicList" });
    },
    onEditorChange(e) {
      const _this = this;
      _this.content = e.html; // 标签以<p></p> 形式渲染 （重点）
      _this.contentTxt = e.text.substr(0, 100);
    },
    //上传图片之前校验
    bfUpload(file) {
      // console.log(file);
      if ("image/png" == file.type || "image/jpeg" == file.type) {
      } else {
        this.$message.error("图片插入失败,请检查文件格式");
        return;
      }

      const fileurl = `https://vclass.api.cheeseburgerim.space/topic/api/uploadTopicImage`;
      let fd = new FormData();
      fd.append("topicId", this.tid);
      fd.append("username", sessionStorage.getItem("userName"));
      fd.append("image", file);
      fetch(fileurl, {
        method: `post`,
        credentials: "include",
        body: fd,
      })
        .then((res) => res.text())
        .then((data) => {
          // this.urlList.push(response.url);
          let quill = this.$refs.myQuillEditor.quill;
          if (data != `fail`) {
            //获取光标所在位置
            let length = quill.getSelection().index;
            //插入图片
            quill.insertEmbed(
              length,
              "image",
              "http://vclass.static.cheeseburgerim.space" + data
            );
            //移动光标到图片后
            quill.setSelection(length + 1);
          } else {
            this.$notify.error({
              title: "错误",
              message: "图片上传失败！",
            });
          }
        })
        .catch((error) => {
          this.$notify.error({
            title: "错误",
            message: "服务器崩溃了~后台小哥哥正在紧急修复中🛠️！",
          });
        });
    },
    submit() {
      this.$refs.coverImageUpload.submit();
      const addurl = `https://vclass.api.cheeseburgerim.space/topic/api/add`;
      if (this.content != `` && this.title != `` && this.description != ``) {
        let fd = new FormData();
        fd.append("username", sessionStorage.getItem("userName"));
        fd.append("fid", sessionStorage.getItem("fid"));
        fd.append("content", this.content);
        fd.append("timestamp", this.currentTime());
        fd.append("title", this.title);
        fd.append("topicId", this.tid);
        fd.append("description", this.description);
        if (this.coverImage != null) {
          // console.log(`hhhh`)
          fd.append("coverImage", this.coverImage);
        }
        fetch(addurl, {
          method: "post",
          credentials: "include",
          body: fd,
        })
          .then((res) => res.text())
          .then((data) => {
            // console.log(data);
            if (data === `success`) {
              this.$notify({
                title: "发布成功",
                message: "您的话题已成功发布啦✅",
                type: "success",
              });
              this.$router.push({ name: "TopicList" });
            } else {
              this.$notify.error({
                title: "发布失败",
                message: "话题发布失败请稍后尝试！",
              });
            }
          })
          .catch((error) => {
            this.$notify.error({
              title: "错误",
              message: "服务器崩溃了~后台小哥哥正在紧急修复中🛠️！",
            });
          });
      } else {
        this.$notify.error({
          title: "发布失败",
          message: "请保证信息填写完整！",
        });
      }
    },
    currentTime() {
      var now = new Date();
      var year = now.getFullYear(); //年
      var month = now.getMonth() + 1; //月
      var day = now.getDate(); //日

      var hh = now.getHours(); //时
      var mm = now.getMinutes(); //分
      var ss = now.getSeconds();

      var clock = year + "-";
      if (month < 10) clock += "0";
      clock += month + "-";
      if (day < 10) clock += "0";
      clock += day + " ";
      if (hh < 10) clock += "0";
      clock += hh + ":";
      if (mm < 10) clock += "0";
      clock += mm + ":";
      if (ss < 10) clock += "0";
      clock += ss;
      return clock;
    },
  },
};
</script>


<style scoped>
#recruitEdit {
  width: 100%;
  min-height: 100vh;
  /* background: pink; */
}
#recruitEdit #r-nav {
  box-sizing: border-box;
  padding: 20px 0;
  color: var(--text2);
  display: flex;
  flex-direction: column;
  align-items: center;
  /* justify-content: space-around; */
  right: 0;
  top: 0;
  height: 100%;
  width: 300px;
  position: fixed;
  /* background: pink; */
  /* border: 1px solid var(--background2); */
}
#recruitEdit #r-nav /deep/ .el-textarea__inner {
  margin-top: 10px;
  background: var(--background3);
  color: var(--text1);
}
#recruitEdit #r-nav .backup {
  margin-top: 10px;
  width: 90%;
  border: 1px solid #e55039;
  color: #eb2f06;
  border-radius: 4px;
  height: 40px;
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: 0.5s;
}
#recruitEdit #r-nav .backup:hover {
  color: #fff;
  background: rgba(235, 47, 6, 0.8);
}
#recruitEdit #r-nav .box {
  margin-top: 20px;
  width: 90%;
}
#recruitEdit #r-nav .box .upload {
  display: flex;
  align-items: center;
  /* justify-content: center; */
  flex-direction: column;
}
#recruitEdit #r-nav .box /deep/ .el-upload {
  transform: scale(0.76);
}
#recruitEdit #r-nav .box /deep/ .el-upload-dragger {
  background: var(--background3);
  color: var(--text1);
}

#recruitEdit #r-nav .submit {
  margin-top: 10px;
  width: 90%;
  border: 1px solid #6a89cc;
  color: #4a69bd;
  border-radius: 4px;
  height: 40px;
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: 0.5s;
}
#recruitEdit #r-nav .submit:hover {
  color: #fff;
  background: rgba(74, 105, 189, 0.8);
}
#recruitEdit #l-main {
  width: calc(100% - 300px);
}
.editor {
  height: calc(100vh - 42px);
}
.editor /deep/ .ql-container {
  font-size: 18px !important;
}
</style>