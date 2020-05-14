<template>
  <div class="box">
    <div class="content">
      <div class="header">
        <div class="demo-type">
          <el-avatar :size="60" src="https://empty" @error="errorHandler">
            <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
          </el-avatar>
        </div>
        <h4 class="username">{{this.goWith.user.nickName}}</h4>
        <h4 class="title">{{this.goWith.title}}</h4>
      </div>
      <div class="go-with-text">
        <p>
        <pre>
        {{this.goWith.plan}}
        </pre>
        </p>
      </div>
      <div class="row">
        <div class="col-9"></div>
        <div class="col-3">
          <table style="">
            <tr>
              <th class="el-icon-date">出发时间:</th>
              <td>{{this.goWith.beginDate}}</td>
            </tr>
            <tr>
              <th class="el-icon-place">出发地:</th>
              <td>{{this.goWith.placeOfDeparture}}</td>
            </tr>
            <tr>
              <th class="el-icon-map-location">目的地:</th>
              <td>{{this.goWith.destination}}</td>
            </tr>
            <tr>
              <th class="el-icon-user">希望人数:</th>
              <td>{{this.goWith.number}}</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <Comment :ref-id="this.$route.query.id" :type="type"></Comment>
    <Pager :pageParam="pageParam"></Pager>
  </div>
</template>

<script>
  import Comment from '../../components/Comments/index'
  import Pager from '../../../../travel-front/src/components/pager'
  import travelApi from "../../api/travel";


  export default {
        name: "index",
        data() {
              return {
                type:'go-with',
                pageParam: {
                  total: 150,
                  pageSize: 15,
                  pageNum: 1,
                  totalPage: 0
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

              }
        },
      components: {
        Comment,
        Pager
      },

      methods: {
        errorHandler() {
          return true
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
        let query = {
          id: this.$route.query.id,
        }
        travelApi.goWithFindById(query).then(res => {
          if (res.data.code === 200){
            this.goWith = res.data.body;
          }else {
            this.messageError(res.data.msg);
          }
        })
    }

  }
</script>

<style scoped>
.box {
  padding: 30px;

}
.content {
  text-align: center;
  border-bottom: 1px solid #ccc;
}
.header {
  /*box-shadow: 0 2px 2px #ccc;*/
  padding: 20px 0px;
  height: 300px;
  background: url("../../img/im.png");
  background-size: 100% 100%;
}
  .title {
    color: #ffffff;
    font-size: 30px;
    text-shadow: 1px 1px 1px black;
  }
.username {
  color: orange;
}

</style>
