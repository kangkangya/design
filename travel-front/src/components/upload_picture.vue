<template>
  <el-upload action="upload"
             list-type="picture-card"
             :http-request="uploadFile"
             :on-remove="handleRemove"
             :limit="limit">
    <i class="el-icon-plus"></i>
  </el-upload>
</template>

<script>
  import pictureApi from "../api/picture";

  export default {
    data() {
      return {
        picMap: new Map(),
      };
    },
    props: {
      refId: String,
      code: String,
      limit: Number
    },
    methods: {
      uploadFile(f) {
        let param = new FormData();
        param.append('file', f.file);
        let action = f.action + '/' + this.refId + '/' + this.code;

        pictureApi.upload(action, param).then(res => {
          let uid = f.file.uid;
          this.picMap.set(uid, res);
          console.log(this.picMap)
        })
      },

      handleRemove(file, fileList) {
        this.picMap.forEach((value, key) => {
          if (key === file.uid) {
            pictureApi.del({id: value}).then(res => {
              this.picMap.delete(file.uid);
              console.log(this.picMap)
            })
          }
        });
      }
    },
    mounted() {
    }
  }
</script>

<style scoped>

</style>
