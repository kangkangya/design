<template>
  <div class="login v2 login-background-img">
    <div class="dialog dialog-shadow" style="display: block; margin-top: -362px;filter:alpha(Opacity=50);-moz-opacity:0.6;opacity: 0.6;background-color: lightcyan">
      <div class="title">
        <h3 style="font-weight: bold">使用账号登录网站</h3>
      </div>
      <div class="registered" style="background-color: lightcyan">
        <div class="content" style="margin-top: 20px;">
          <ul class="common-form" >
            <li class="username border-1p">
              <div style="margin-top: 40px;" class="input">
                <input type="text" v-model="user.username" style="font-weight: bold" placeholder="用户名">
              </div>
            </li>
            <li>
              <div class="input">
                <input type="password" v-model="user.password" style="font-weight: bold" placeholder="密码">
              </div>
            </li>
            <li style="text-align: right;" class="pr">
              <el-checkbox class="auto-login" style="font-weight: bold;">记住密码</el-checkbox>
              <a href="javascript:;" class="register" @click="toRegister()" style="font-weight: bold">注册账号</a>
              <a href="javascript:;" style="padding: 1px 0 0 10px;font-weight: bold" @click="open('找回密码','请联系管理员找回密码!')">忘记密码 ?</a>
            </li>
          </ul>

          <div style="margin-bottom: 30px;">
            <TButton :classStyle="displayOrHidden" text="登录" @btnClick="login()"
                     style="margin: 0; width: 95%;height: 48px;font-size: 20px;line-height: 48px;"/>
          </div>

          <div>
            <TButton text="返回" @btnClick="toIndex()"
                     style="marginTop: 10px;marginBottom: 15px;width: 95%;height: 48px;font-size: 20px;line-height: 48px"/>
          </div>
          <div class="border"></div>
          <div class="footer">
            <div class="other" style="font-weight: bold;color:black;">其它账号登录：</div>
            <a><img @click="open('待开发','此功能开发中...')" style="height: 15px; margin-top: 22px;"
                    src="../../static/img/other-login.png"></a>
          </div>
        </div>
      </div>
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
      return {
        agreement: false,
        user: {
          username: '',
          password: ''
        }
      }
    },
    computed: {
      displayOrHidden() {
        return this.user.username && this.user.password ? 'main-btn' : 'disabled-btn';
      }
    },
    methods: {
      open(t, m) {
        this.$notify.info({
          title: t,
          message: m
        })
      },

      messageError(m) {
        this.$message.error({
          message: m
        })
      },

      login() {
        userApi.login(this.user).then(res => {
          if (res.code === 200) {
            let userInfo = res.body;
            if (userInfo.picUrl === null || userInfo.picUrl === '') {
              userInfo.picUrl = 'default_head_url.jpeg';
            }
            // console.log(userInfo)

            this.$store.commit("setUserInfo", userInfo);
            this.$store.commit("loginFlag", true);

            this.toIndex();
            return true;
          } else {
            this.messageError(res.msg);
            return false;
          }

        })
      },

      toRegister() {
        this.$router.push({
          path: '/register'
        })
      },

      toIndex() {
        this.$router.push({
          path: '/'
        })
      }
    }
  }
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  .login-background-img {
    center: 0px;
    background-image: url("../../static/img/login_background.jpg");
    background-size: cover;
    position: relative;
    min-height: 100vh;
  }

  * {
    box-sizing: content-box;
  }

  .login {
    .input {
      height: 50px;
      display: flex;
      align-items: center;

      input {
        font-size: 16px;
        width: 100%;
        height: 100%;
        padding: 10px 15px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 6px;
      }
    }
  }

  .v2 .dialog {
    width: 450px;
    border: 1px solid #dadada;
    border-radius: 10px;
    top: 50%;
    left: 50%;
    margin-left: -225px;
    position: absolute;

    .title {
      height: auto;
      overflow: visible;
      box-shadow: 0 1px 4px rgba(0, 0, 0, 0.08);
      position: relative;
      background-size: 140px;
      background-position: top center;
      background-repeat: no-repeat;
      height: 92px;
      margin: 23px 0 50px;
      padding: 75px 0 0;
      box-shadow: none;

      h3 {
        padding: 0;
        text-align: center;
        color: #666;
        border-bottom: 1px solid #dcdcdc;
        -webkit-box-shadow: none;
        -moz-box-shadow: none;
        box-shadow: none;
        font-weight: 700;
        position: absolute;
        bottom: 0;
        width: 100%;
        text-align: center;
        margin: 0;
        padding: 0;
        border-bottom: 0;
        -webkit-box-shadow: none;
        -moz-box-shadow: none;
        box-shadow: none;
        line-height: 1em;
        height: auto;
        color: #333;
        font-weight: 400;
      }
    }

    .content {
      padding: 0 40px 22px;
      height: auto;

      .common-form {
        li {
          clear: both;
          margin-bottom: 20px;
          position: relative;
        }
      }
    }
  }

  .dialog-shadow,
  .v2 .bbs .dialog-shadow,
  .v2 .dialog-shadow {
    -webkit-box-shadow: 0 9px 30px -6px rgba(0, 0, 0, 0.2),
    0 18px 20px -10px rgba(0, 0, 0, 0.04), 0 18px 20px -10px rgba(0, 0, 0, 0.04),
    0 10px 20px -10px rgba(0, 0, 0, 0.04);
    -moz-box-shadow: 0 9px 30px -6px rgba(0, 0, 0, 0.2),
    0 18px 20px -10px rgba(0, 0, 0, 0.04), 0 18px 20px -10px rgba(0, 0, 0, 0.04),
    0 10px 20px -10px rgba(0, 0, 0, 0.04);
    box-shadow: 0 9px 30px -6px rgba(0, 0, 0, 0.2),
    0 18px 20px -10px rgba(0, 0, 0, 0.04), 0 18px 20px -10px rgba(0, 0, 0, 0.04),
    0 10px 20px -10px rgba(0, 0, 0, 0.04);
  }

  @media screen and (min-width: 737px),
  screen and (-webkit-max-device-pixel-ratio: 1.9) and (max-width: 736px) and (min-device-width: 737px) {
    .dialog {
      background: url(../../static/img/dialog-gray-bg.png) #fff bottom repeat-x;
      border-radius: 12px;
      display: none;
      margin: -163px 0 0 -218px;
      width: 436px;
      position: fixed;
      left: 50%;
      top: 50%;
    }
    .dialog .title h4 {
      border-bottom: #d1d1d1 solid 1px;
      box-shadow: 0 2px 6px #d1d1d1;
      color: #666;
      font-size: 20px;
      height: 61px;
      line-height: 61px;
      padding: 0 0 0 35px;
    }
    .common-form li {
      clear: both;
      margin-bottom: 20px;
      position: relative;
    }
    .auto-login {
      position: absolute;
      top: 0px;
      left: 2px;
      color: #999;
    }
    .register {
      padding: 1px 10px 0;
      border-right: 1px solid #ccc;
    }
    .border {
      margin-top: 10px;
      border-bottom: 1px solid #ccc;
    }
    .other {
      margin: 20px 5px 0 0;
      width: auto;
      color: #bbb;
      font-size: 12px;
      cursor: default;
      color: #999;
    }
    .agree {
      left: 10%;
      font-weight: bold;
      margin-bottom: 30px;
      font-size: 14px;
      color: #999;
    }
  }

  .registered {
    h3 {
      padding: 0;
      text-align: center;
      color: #666;
      border-bottom: 1px solid #dcdcdc;
      -webkit-box-shadow: none;
      -moz-box-shadow: none;
      box-shadow: none;
      font-weight: 700;
      font-size: 25px;
      height: 60px;
      line-height: 60px;
    }
  }

  ul li {
    list-style-type: none;
    width: 90%;
  }
</style>
