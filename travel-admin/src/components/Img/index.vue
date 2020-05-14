<template>
    <div>
      <el-upload
        :action="uploadUrl + uploadPram.refId + '/' + uploadPram.imgCode"
        list-type="picture-card"
        :on-preview="handlePictureCardPreview"
        :on-remove="handleRemove"
        :on-success="handleSuccess">
        <i class="el-icon-plus"></i>
      </el-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImgUrl" alt="">
      </el-dialog>
    </div>
</template>

<script>
  export default {
    name: 'index',
    props: {
      uploadPram:{
        type: [Object]
      },
    },
    data() {
      return {
        baseUrl: 'http://localhost:8888/img/',
        imgUrl: '',
        dialogImgUrl: '',
        dialogVisible: false,
        uploadUrl: 'http://localhost:8080/api/picture/upload/',

      };
    },
    methods: {
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogVisible = true;
      },
      handleSuccess(res, file, fileList) {
        this.imgUrl =  res;
        this.dialogImgUrl = this.baseUrl + this.uploadPram.refId + '/' + this.uploadPram.imgCode + '/' + res;
        console.log("success" );
      },
    }
  }
</script>

<style scoped>

</style>
