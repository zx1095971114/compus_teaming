<template>
  <div id="recruitEdit">
    <el-drawer title="我是标题" :visible.sync="drawer" :with-header="false">
      <div class="drawer-wrapper">
        <h3>———————— 标签选择 ————————</h3>
        <div class="tags">
          <div>
            <el-checkbox-group v-model="recruitInfo.ttags">
              <el-checkbox-button
                border
                v-for="tag in tags"
                :label="tag"
                :key="tag"
                >{{ tag }}</el-checkbox-button
              >
            </el-checkbox-group>
          </div>
        </div>
      </div>
    </el-drawer>
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
        v-model="recruitInfo.content"
        ref="myQuillEditor"
        :options="editorOption"
        @change="onEditorChange($event)"
      />
    </div>
    <div id="r-nav" class="animate__animated animate__fadeInRight">
      <div class="box">
        <div class="backup" @click="centerDialogVisible = true">
          <strong
            ><i class="fas fa-arrow-alt-left"></i>&nbsp;&nbsp;退出编辑</strong
          >
        </div>
      </div>
      <div class="box">
        <strong>招募标题(必填)</strong>
        <el-input
          type="textarea"
          :autosize="{ minRows: 1, maxRows: 4 }"
          placeholder="请输入话题标题(大约在20左右)"
          v-model="recruitInfo.title"
        >
        </el-input>
      </div>
      <div class="box">
        <strong>招募简介(必填)</strong>
        <el-input
          type="textarea"
          :autosize="{ minRows: 6, maxRows: 8 }"
          placeholder="请输入话题简介内容(大约在100字左右)"
          v-model="recruitInfo.description"
        >
        </el-input>
      </div>
      <div class="box">
        <strong>招募人数(必填)</strong>
        <el-input-number
          v-model="recruitInfo.num"
          :min="1"
          :max="10"
          label="描述文字"
        ></el-input-number>
      </div>
      <div class="box">
        <strong>截止时间(必填)</strong>
        <el-date-picker
          v-model="recruitInfo.endtime"
          type="datetime"
          placeholder="选择日期"
        >
        </el-date-picker>
      </div>
      <div class="box">
        <strong>招募帖封面(选填)</strong>
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
      <div class="box">
        <strong>展示板块(必选)</strong>
        <el-select v-model="recruitInfo.class" placeholder="请选择">
          <template slot="prefix">展示板块:</template>
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
        <strong>招募标签(必选)</strong>
        <div class="button" @click="drawer = true">
          ★&nbsp;&nbsp;&nbsp;绑定标签
        </div>
      </div>
      <div class="box">
        <div class="submit" @click="submit">
          <strong
            ><i class="fad fa-paper-plane"></i>&nbsp;&nbsp;发布招募</strong
          >
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import hljs from "highlight.js";

import "highlight.js/styles/github-dark.css";
import { quillEditor } from "vue-quill-editor";

export default {
  components: {
    quillEditor,
  },
  name: "recruitEdit",
  created() {
    
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
      recruitInfo: {
        endtime: "",
        ttags: [],
        num: 3,
        title: "",
        description: "",
        class: "学习板块",
        content: "", // 富文本里的内容
      },
      tags: [
        "大作业组队",
        "大创组队",
        "毕设讨论",
        "实验室招募",
        "研友寻找",
        "竞赛组队",
        "志愿者招募",
        "学业辅导班",
        "拼单购物",
        "拼车出行",
        "帮忙取外卖",
        "帮忙取快递",
        "帮砍互助",
        "寻找女朋友",
        "寻找男朋友",
        "铁友寻找",
        "开黑四缺一",
        "ACM打铁",
        "姐妹相约购物",
        "狼人杀",
        "三国杀",
        "UNO桌游",
        "斗地主",
        "是兄弟一起打麻将",
        "美食城探索",
        "联名问政",
        "出售物品",
        "回收物品",
        "兼职招募",
      ],
      drawer: false,
      options: [
        {
          value: "学习板块",
          label: "学习板块",
        },
        {
          value: "生活板块",
          label: "生活板块",
        },
        {
          value: "娱乐板块",
          label: "娱乐板块",
        },
        {
          value: "其他板块",
          label: "其他板块",
        },
      ],
      coverImage: null,
      tid: ``,
      centerDialogVisible: false,
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
    },
    //获取时间戳
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
}
#recruitEdit #r-nav {
  padding: 20px 0;
  box-sizing: border-box;
  color: var(--text2);
  display: flex;
  flex-direction: column;
  align-items: center;

  right: 0;
  top: 0;
  height: 100%;
  overflow: auto;
  width: 300px;
  position: fixed;
}
#recruitEdit #r-nav /deep/ .el-textarea__inner {
  margin-top: 10px;
  background: var(--background3);
  color: var(--text1);
}
#recruitEdit #r-nav .backup {
  margin-top: 10px;
  width: 100%;
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
  margin-top: 5px;
  width: 90%;
}
#r-nav .button {
  z-index: 5;
  width: 100%;
  border: 1px solid #00875a;
  color: #00b894;
  border-radius: 4px;
  height: 40px;
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: 0.5s;
}
#r-nav .button:hover {
  color: #fff;
  background: #00b894;
}
#recruitEdit #r-nav .box .upload {
  display: flex;
  align-items: center;

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
  width: 100%;
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
#recruitEdit .drawer-wrapper {
  width: 100%;
  height: 100%;
  background: #f4f4f4;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}
#recruitEdit .drawer-wrapper h3 {
  font-family: "SentyGoldenBell";
}
</style>