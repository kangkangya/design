<template>
  <div class="new-password">
    <div class="new-password-box">
      <p class="title">设置新密码</p>
      <div class="row">
        <div class="col-2"></div>
        <div class="col-8">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="80px" class="demo-ruleForm">
            <el-form-item label="新密码" prop="password">
              <el-input type="password" placeholder="输入新密码" v-model="ruleForm.password"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="passwordAgain">
              <el-input type="password" placeholder="再次输入密码" v-model="ruleForm.passwordAgain"></el-input>
            </el-form-item>
            <el-form-item label="验证码" prop="verification">
              <el-input type="password" placeholder="短信验证码" v-model="ruleForm.verification"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div class="col-2"></div>
      </div>
      <div class="row">
        <div class="col-2"></div>
        <div class="col-8"><button type="button" class="btn btn-primary btn-block">完成</button></div>
        <div class="col-2"></div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "NewPassword",
    data() {
      return {
        ruleForm: {
          passwordAgain: '',
          password: '',
          verification: ''
        }
      }
    }
  }
</script>

<style scoped>
  .new-password {
    position: relative;
  }
  .new-password-box {
    width: 500px;
    height: 500px;
    background: #E6E6E6;
    border-radius: 10px;
    position: absolute;
    left: 35%;
    top: 50%;
  }
  .title {
    font-size: 20px;
    color: #666;
    padding: 20px 50px;
  }
</style>
