<template>
  <div class="login">
    <div class="login-box">
      <p class="title">找回密码</p>
      <div class="row">
        <div class="col-1"></div>
        <div class="col-9">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="80px" class="demo-ruleForm">
            <el-form-item label="账号" prop="user">
              <el-input placeholder="你的电话号码/邮箱账号" v-model="ruleForm.user"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div class="col-2"></div>
      </div>
      <div class="row">
        <div class="col-2"></div>
        <div class="col-8"><router-link class="nav-link" to="/new-password"><button type="button" class="btn btn-primary btn-block">立即找回</button></router-link></div>
        <div class="col-2"></div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "forget-password",
    data() {
      return {
        ruleForm: {
          user: '',
          password: ''
        }
      }
    }
  }
</script>

<style scoped>
  .login {
    position: relative;
  }
  .login-box {
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
