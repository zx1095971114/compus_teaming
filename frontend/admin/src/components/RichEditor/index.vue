<template>
  <div id="richEditor">
    <el-input @focus="dialogVisible = true" v-model="cont" clearable></el-input>
    <el-upload
      style="display: none"
      class="quill-picture-uploader"
      ref="quilEditorUploader"
      action="a"
      :before-upload="bfUpload"
      with-credentials
    >
    </el-upload>
    <el-dialog
      :visible.sync="dialogVisible"
      append-to-body
      :close-on-click-modal="false"
      style="width: 100%; height: 100%"
      class="dialog"
      :show-close="false"
      center
    >
      <quill-editor
        class="editor"
        v-model="content"
        ref="myQuillEditor"
        :options="editorOption"
        @change="onEditorChange($event)"
      />
      <div class="footer">
        <el-button type="primary" @click="updateContent">确 定</el-button>
        <el-button @click="dialogVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import hljs from "highlight.js";
import "highlight.js/styles/vs2015.css";
import { quillEditor } from "vue-quill-editor";
var quilEditorUploader = null;
export default {
  props: {
    id: {
      type: Number,
      default: -99,
    },
    cont: {
      type: String,
      default: ``,
    },
  },
  name: "RichEditor",
  components: {
    quillEditor,
  },
  mounted() {
    quilEditorUploader =
      this.$refs.quilEditorUploader.$el.firstChild.firstChild;
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
      dialogVisible: false,
      content: this.cont, // 富文本里的内容
      editorOption: {
        // 富文本编辑器的工具栏
        modules: {
          toolbar: {
            container: toolbarOptions, // 工具栏
            handlers: {
              image: function (value) {
                //替换原图片上传功能
                if (value) {
                  quilEditorUploader.click(); //核心
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
    updateContent() {
      var payload = {
        id: this.id,
        content: this.content,
      };
      this.$emit("updateContent", payload);
      this.dialogVisible = false;
    },
    onEditorChange(e) {
      const _this = this;
      _this.content = e.html; // 标签以<p></p> 形式渲染 （重点）
      _this.contentTxt = e.text.substr(0, 100);
    },
    bfUpload(file) {
      if ("image/png" == file.type || "image/jpeg" == file.type) {
      } else {
        this.$message.error("图片插入失败,请检查文件格式");
        return;
      }
    },
  },
};
</script>

<style scoped>
#richEditor{
  width: 100%;
  margin-left: 5px;
}
.editor {
  height: 100%;
  width: 100%;
}
.editor /deep/ .ql-container {
  font-size: 14px !important;
  min-height: 300px !important;
}
.footer {
  width: 200px;
  margin: 0px auto;
  margin-top: 20px;
}
</style>