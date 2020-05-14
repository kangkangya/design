<template>
  <div class="v2 login-background-img">
    <div class="dialog dialog-shadow" style="display: block; margin-top: -270px;filter:alpha(Opacity=50);-moz-opacity:0.6;opacity: 0.6;background-color: lightcyan">
      <el-form ref="user" :rules="rules" :model="user" label-width="80px">
        <el-form-item label="用户名" prop="username" style="margin-top: 40px; margin-right: 50px">
          <el-input v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" style="margin-right: 50px">
          <el-input type="password" v-model="user.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword" style="margin-right: 50px">
          <el-input type="password" v-model="user.confirmPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickName" style="margin-right: 50px">
          <el-input v-model="user.nickName"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender" style="margin-right: 50px">
          <el-radio v-model="user.gender" label="1">男</el-radio>
          <el-radio v-model="user.gender" label="2">女</el-radio>
        </el-form-item>

        <el-form-item label="个人简介" prop="summary" style="margin-right: 50px">
          <el-input type="textarea" v-model="user.summary"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="register('user')">注册</el-button>
          <router-link to="/login">
            <a href="javascript;;">已有账号?点此登录</a>
          </router-link>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import TButton from "../components/tbutton";
  import userApi from "../api/user";

  export default {
    components: {
      TButton
    },
    data() {
      //验证两次密码是否相同的验证器----在rules中调用
      var validatePassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.user.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };

      return {
        user: {
          username: '',
          password: '',
          confirmPassword: '',
          nickName: '',
          gender: '1',
          summary: ''
        },
        rules: {
          username: [{required: true, message: '请输入用户名', trigger:'blur'}],
          password: [{required: true, message: '请输入密码', trigger:'blur'}],
          confirmPassword: [{validator: validatePassword, trigger:'blur'}],
          nickName: [{required: true, message: '请输入昵称', trigger:'blur'}],
          gender: [{required: true}],
        }
      }
    },
    methods: {
      messageError(m) {
        this.$message.error({
          message: m
        })
      },

      messageSuccess() {
        this.$message({
          message: '恭喜您，注册成功！赶紧登录体验吧',
        })
      },

      register(formName) {
        this.$refs[formName].validate((valid) => {
          //表单验证通过
          if (valid){
            userApi.register(this.user).then(res => {
              if (res.code === 200) {
                this.messageSuccess(res.msg);

                setTimeout(this.toLogin, 1000);

                return true;
              } else {
                this.messageError(res.msg);
                return false;
              }

            });
          }else{
            this.messageError('请填写所有必填信息');
            return false;
          }
        });

      },

      toLogin() {
        this.$router.push({
          path: '/login'
        })
      }
    }
  }
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  .login-background-img {
    center: 0px;
    background-image: url("../../static/img/bg-2.jpg");
    background-size: cover;
    position: relative;
    min-height: 100vh;
  }

  * {
    box-sizing: content-box;
  }


  .v2 .dialog {
    width: 450px;
    border: 1px solid #dadada;
    border-radius: 10px;
    top: 50%;
    left: 50%;
    margin-left: -225px;
    position: absolute;

  }

</style>
