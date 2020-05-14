<template>
  <div>
    <div class="go-with-background-img">
      <div class="go-with-top">
        <p class="title">{{goWith.title}}</p>
        <div>
          <h4>{{goWith.applicantNum}}</h4><p>&nbsp;人报名</p>
          <h4>{{goWith.attentionNum}}</h4><p>&nbsp;人关注</p>
        </div>
        <div v-if="!isFollow" class="go-with-enter">
          <el-button @click="follow">关注</el-button>
        </div>
        <div v-if="isFollow" class="go-with-enter" @click="cancleFollow"><el-button>取消关注</el-button></div>
        </div>
    </div>
    <div class="container">
      <div class="row go-with-summary">
        <ul>
          <li>
            <i class="el-icon-date"></i>
            <span>出发时间:{{goWith.beginDate}}</span>
          </li>
          <li>
            <i class="el-icon-place"></i>
            <span>出发地:{{goWith.placeOfDeparture}}</span>
          </li>
          <li>
            <i class="el-icon-map-location"></i>
            <span>目的地:{{goWith.destination}}</span>
          </li>
          <li>
            <i class="el-icon-user"></i>
            <span>希望人数:{{goWith.number}}人</span>
          </li>
        </ul>
      </div>
      <div class="row go-with-text">
        <div class="col-2 go-with-text-left">
          <p class="user-img"><img src="" alt=""></p>
          <p class="user-name">{{this.goWith.user.nickName}}</p>
        </div>
        <div class="col-10 go-with-text-right">
          <div class="row">
            <h4>{{goWith.title}}</h4>
            <p >
             <pre style="color: #666">
              {{goWith.plan}}
             </pre>
            </p>
          </div>
          <div v-if="!isSignIn" class="row sign-up">
            <p>我要报名</p>
            <div class="sign-up-form">
              <el-form ref="form" :model="form" label-width="150px">
                <el-form-item label="联系方式">
                  <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                  <el-radio-group v-model="form.sex" style="padding-top: 7px">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                  </el-radio-group>
                </el-form-item>
                <div class="row">
                  <el-form-item label="同行人数/男">
                    <el-input placeholder="男" v-model="form.manNum"></el-input>
                  </el-form-item>
                  <el-form-item label="同行人数/女">
                    <el-input placeholder="女" v-model="form.womanNum"></el-input>
                  </el-form-item>
                </div>

                <el-form-item label="同行名单">
                  <el-input  v-model="form.order"  ></el-input>
                </el-form-item>
                <el-form-item label="备注">
                  <el-input type="textarea" v-model="form.remarks"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onSubmit">报名</el-button>
                </el-form-item>
              </el-form>
            </div>
          </div>
          <div v-if="isSignIn" class="row sign-up">
            <el-button type="primary" @click="cancleSign">取消报名</el-button>
          </div>

        </div>
      </div>
      <div class="container" style="margin-top: 30px">
        <div class="row">
          <div class="col-8">
            <h4 ><strong>全部评价 (共有{{totalCommentNum}}条评价)</strong></h4>
          </div>
          <div class="col-4">
            <my_comment comment-type="go-with" :ref-ids="refId" :refresh-page="refreshPage"></my_comment>
          </div>
        </div>

        <div class="row" style="height:20px;border-bottom: 1px solid #ccc;"></div>

        <div class="row" style="margin-top: 10px">
          <comment :ref-id="refId" :type="type" :key="com"></comment>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import Reply from '../components/reply'
  import goWithApi from "../api/goWith";
  import enterInfoApi from "../api/enterInfo";
  import followApi from "../api/follow";
  import Comment from "../components/comment";
  import my_comment from "../components/my_comments";
  import comment1 from "../api/comment";


  export default {
        name: "GoWithDetail",
      components: {
          Reply,
          Comment,
          my_comment
      },
      data() {
        return {
          com: '',
          totalCommentNum: 0,
          type:'go-with',
          refId: this.$route.query.id,
          isSignIn: false,
          isFollow: false,
          form:{
            id: '',
            goWithId: '',
            userId:'',
            phone: '',
            sex: '',
            manNum: 0,
            womanNum: 0,
            order: '',
            remarks: '',
          },
          goWith: {
            id: '',
            title: '',
            placeOfDeparture: '',
            destination: '',
            number: 0,
            plan: '',
            beginDate: '',
            applicantNum:0,
            attentionNum:0,
            user:{
              id: '',
              nickName: '',
            }
          },
          followInfo: {
            id: '',
            userId: '',
            refId: '',
            type: 'go-with'
          },

        }
      },
      methods: {
        refreshPage() {
          this.com = new Date().getTime();
        },

        follow(){
          followApi.save(this.followInfo).then(res => {
            if (res.code === 200){
              this.isFollow = true;
              this.messageSuccess("关注成功");

              this.goWith.attentionNum = this.goWith.attentionNum + 1;
              goWithApi.update(this.goWith).then(res => {
              });


            } else {
              this.messageError("关注失败")
            }
          })
        },
        cancleFollow(){
          var param = {
            id: this.followInfo.id
          }
          followApi.delete(param).then(res => {
            this.isFollow = false;

            this.goWith.attentionNum = this.goWith.attentionNum - 1;
            goWithApi.update(this.goWith).then(res => {
            });
          })
        },


        onSubmit() {
          if (this.form.goWithId === undefined || this.form.goWithId === null){
            this.messageError("未知错误，请刷新页面后重试");
            return  ;
          }

          enterInfoApi.save(this.form).then(res => {
            if (res.code === 200){
              this.messageSuccess("报名成功");
              this.isSignIn = true;
              this.goWith.applicantNum = this.goWith.applicantNum + 1;
              goWithApi.update(this.goWith).then(res => {
                this.goWith = res.body;
              })
            }else {
              this.messageError("未知错误，请刷新页面后重试");
            }
          });

        },

        cancleSign(){
          var param = {
            id: this.form.id
          }
          enterInfoApi.delete(param).then(res => {
            this.isSignIn = false;
            this.goWith.applicantNum = this.goWith.applicantNum - 1;
            goWithApi.update(this.goWith).then(res => {
              this.goWith = res.body;
            })

          })
        },

        submitForm(formName) {

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
      },
      mounted() {

          this.form.userId = this.$store.getters.getUserInfo.id;
          this.followInfo.userId = this.$store.getters.getUserInfo.id;
          if (this.form.userId === undefined || this.form.userId === null){
            this.messageError("未登录或登录已过期，请重新登陆");
            this.$router.push({path: '/login'})
            return ;
          }

          this.form.goWithId = this.$route.query.id;
          this.followInfo.refId = this.$route.query.id;
          this.refId = this.$route.query.id;

          var param1 = {
            id: this.$route.query.id
          }

          goWithApi.findById(param1).then(res => {
            if (res.data.code === 200){
              this.goWith = res.data.body;
            }else {
              this.messageError(res.msg);
              this.$router.push({path: '/go-with'})
            }
          });
          var param2 = {
            userId: this.form.userId,
            goWithId: this.$route.query.id
          }
          enterInfoApi.findByUserId(param2).then(res => {
            if (res.data.code === 200){
              this.isSignIn = true;
              this.form.id = res.data.body.id;
            }
          });

          followApi.findOne(this.followInfo).then(res => {
            if (res.code === 200){
              this.isFollow = true;
              this.followInfo = res.body;
            }
          })

        let query = {
          refId: this.$route.query.id,
          type: 'go-with'
        };
        comment1.list(query).then(res => {
          this.totalCommentNum = res.data.total;
        })
      }
    }
</script>

<style scoped>
 .go-with-background-img {
  width: 100%;
  height: 400px;
  background-image: url("../../static/img/gowithimg.png");
  background-size: 100% 100%;
   position: relative;
}
  .go-with-top {
    width: 600px;
    height: 200px;
    position: absolute;
    left: 50%;
    top: 50%;
    margin-left: -300px;
    margin-top: -100px;
    text-align: center;
  }
   .go-with-top .title {
     color: #fff;
     font-size: 30px;
     font-weight: 500;
     text-shadow: 1px 2px 2px rgba(0,0,0,1);
   }
 .go-with-top p {
   display: inline-block;
   padding-right: 15px;
   color: #fff;
   font-size: 20px;
 }
 .go-with-top h4 {
   color: #fff;
   display: inline-block;
   font-size: 30px;
 }
.go-with-enter {
  padding-top: 70px;
  color: #fff;
  font-size: 22px;
  font-weight: 500;
  color: orange;
}
  .go-with-enter .el-icon-message {
    color: orange;
    font-size: 45px;
  }
 .go-with-summary {
   padding: 40px 0;
   border-bottom: 1px solid #ccc;
 }
  .go-with-summary li {
    list-style: none;
    float: left;
    font-size: 20px;
    color: #666;
    padding-right: 80px;
    padding-top: 30px;
  }
  .go-with-summary i {
    font-size: 30px;
  }
  .go-with-text {
    padding: 30px 0;
  }
  .go-with-text .user-img {
    width: 80px;
    height: 80px;
    border: 1px solid #ccc;
    border-radius: 50%;
  }
 .go-with-text .user-name {
   color: orange;
   font-weight: 600;
 }
 .go-with-text-right p {
   font-size: 14px;
   color: #666;
 }
 .sign-up {
   padding-top: 30px;
 }
  .sign-up p,.leave-message p {
    font-size: 21px;
  }
 .comments-detail {
   border-bottom: 1px dashed #ccc;
   padding-top: 20px;
 }
 .user-name {
   width: 80px;
   text-align: center;
   color: orange;
 }
 .comments-header {
   width: 80px;
   height: 80px;
   border-radius: 50%;
   border: 1px solid #ccc;
 }
 .comments-text .user-comments-text {
    font-size: 14px;
  }
</style>
