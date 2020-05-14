<template>
  <div class="row">
    <el-button type="primary" @click="centerDialogVisible = true">我要评价</el-button>
    <el-dialog title="评价" :visible.sync="centerDialogVisible" width="70%" center>
      <el-form :model="params" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="总体评价" required>
          <el-rate style="margin-top: 10px;" v-model="params.starNum" show-score text-color="#ff9900"
                   score-template="{value}"></el-rate>
        </el-form-item>
        <el-form-item label="评价内容" required>
          <el-input v-model="params.comment" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="上传照片">
          <upload_picture :limit="limit" code="comment" :ref-id="commentId"></upload_picture>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" alt="">
          </el-dialog>
          <p class="card-text">图片不超过4张</p>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import commentApi from "../api/comment";
  import uuidUtil from "../utils/uuid";
  import upload_picture from "./upload_picture";

  export default {
    data() {
      return {
        dialogVisible: false,
        requestParams: new FormData(),
        commentId: uuidUtil.guid(),
        limit: 4,
        params: {
          id: '',
          comment: '',
          userId: '',
          starNum: 0,
          refId: this.refIds,
          type: this.commentType
        },
        rules: {
          content: [
            {required: true, message: '请填评价内容', trigger: 'blur'},
            {min: 1, max: 500, message: '长度在 1 到 500 个字符', trigger: 'blur'}
          ]
        },
        centerDialogVisible: false
      };
    },
    components: {
      upload_picture
    },
    props: {
      commentType: String,
      refIds: String,
      refreshPage: Function
    },
    methods: {
      messageError(m) {
        this.$message.error({
          message: m
        })
      },

      submitForm() {
        let commentMsg = this.params.comment;
        if (commentMsg === '' ||
          commentMsg === undefined ||
          commentMsg === null) {
          this.messageError('评论内容不能为空！');
          return;
        }

        this.params.id = this.commentId;
        commentApi.save(this.params).then(res => {
          this.centerDialogVisible = false
          this.refreshPage();
        })
      },
    },
    mounted() {
      let userInfo = JSON.parse(localStorage.getItem('userInfo'));
      this.params.userId = userInfo.id;
    }
  }
</script>

<style scoped>

</style>
