<template>
    <div class="container">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="游记标题">
          <el-input v-model="form.title"></el-input>
        </el-form-item>
        <el-form-item label="游记内容">
          <el-input type="textarea" v-model="form.summary"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即发表</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
    import travelNoteApi from "../api/travel-note";

    export default {
        name: "WriteNotes",
      data() {
          return {
            form: {
              title: '',
              summary: '',
              refId: '',
            },

          }
      },

      methods: {
          messageSuccess(m) {
            this.$message({
              message: m
            })
          },

          messageError(m) {
            this.$message.error({
              message: m
            })
          },

          onSubmit(){
            if (this.form.title === undefined
              || this.form.title === null
              || this.form.title === ""){
              this.messageError("标题不能为空")
            }

            if (this.form.summary === undefined
              || this.form.summary === null
              || this.form.summary === ""){
              this.messageError("内容不能为空")
            }

            this.form.refId = this.$store.getters.getUserInfo.id;
            if (this.form.refId === undefined || this.form.refId === null){
              this.messageError("登陆已过期，请重新登陆");
              this.$router.push({path: '/login'})
              return ;
            }

            travelNoteApi.save(this.form).then(res => {
              this.messageSuccess("发布游记成功");
              console.log(res);
              this.$router.push({path: '/personal'})
            })

          }
      }
    }
</script>

<style scoped>

</style>
