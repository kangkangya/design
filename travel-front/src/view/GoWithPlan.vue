<template>
    <div class="container">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="结伴标题" prop="title">
          <el-input v-model="ruleForm.title"></el-input>
        </el-form-item>
        <el-form-item label="出发地" prop="placeOfDeparture">
          <el-input v-model="ruleForm.placeOfDeparture"></el-input>
        </el-form-item>
        <el-form-item label="目的地" prop="destination">
          <el-input v-model="ruleForm.destination"></el-input>
        </el-form-item>
        <el-form-item label="希望人数" prop="number">
          <el-input v-model="ruleForm.number"></el-input>
        </el-form-item>
        <el-form-item label="旅行时间" required>
          <el-col :span="11">
            <el-form-item prop="beginDate">
              <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.beginDate" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-form-item prop="endDate">
              <el-date-picker type="date" placeholder="选择时间" v-model="ruleForm.endDate" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="活动计划" prop="plan">
          <el-input type="textarea" v-model="ruleForm.plan"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
    import goWithApi from "../api/goWith";
    import travelNoteApi from "../api/travel-note";

    export default {
        name: "GoWithPlan",
      data() {
        return {
          ruleForm: {
            refId: '',
            title: '',
            placeOfDeparture: '',
            destination: '',
            beginDate: '',
            endDate: '',
            plan: ''
          },
          rules: {
            title: [
              { required: true, message: '请输入活动标题', trigger: 'blur' },
              { min: 3, max: 5, message: '长度在 5 到 40 个字符', trigger: 'blur' }
            ],
            placeOfDeparture: [
              { required: true, message: '请输入出发地', trigger: 'change' }
            ],
            destination: [
              { required: true, message: '请输入目的地', trigger: 'change' }
            ],
            beginDate: [
              { type: 'date', required: true, message: '请选择开始日期', trigger: 'change' }
            ],
            endDate: [
              { type: 'date', required: true, message: '请选择结束时间', trigger: 'change' }
            ],
            plan: [
              { required: true, message: '请填写活动计划', trigger: 'blur' }
            ]
          }
        };
      },
      methods: {
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {

              this.ruleForm.refId = this.$store.getters.getUserInfo.id;
              if (this.ruleForm.refId === undefined || this.ruleForm.refId === null){
                this.messageError("登陆已过期，请重新登陆");
                this.$router.push({path: '/login'})
                return ;
              }

              goWithApi.save(this.ruleForm).then(res =>{
                if (res.code === 200){
                  this.messageSuccess("发布成功");
                  this.resetForm(formName);
                  this.$router.push({path: '/go-with'})
                }else {
                  this.messageError(res.msg);
                }
              })

            } else {
              alert("发布失败");
              console.log('error submit!!');
              return false;
            }
          });
        },
        resetForm(formName) {
          this.$refs[formName].resetFields();
        },

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

      }
    }
</script>

<style scoped>

</style>
