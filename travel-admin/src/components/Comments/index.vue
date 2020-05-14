<template>
  <div class="comments-list">
    <div class="row comments-detail" v-for="comment in commentList">
      <div class="col-1">
        <div class="demo-type">
          <el-avatar :size="60" src="https://empty" @error="errorHandler">
            <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
          </el-avatar>
        </div>
        <p class="user-name">{{comment.user.nickName}}</p>
      </div>
      <div class="col-11 comments-text">
        <p class=" user-comments-text" >{{comment.comment}}</p>
        <p><small class="text-muted">{{comment.timestamp | dateFormat}}</small></p>
      </div>
    </div>
  </div>

</template>

<script>
    import travelApi from "../../api/travel";
    import timeUtil from "../../../../travel-front/src/utils/time";

    export default {
      name: "index",

      filters:{
        dateFormat: function (val) {
          let date = new Date(val);
          return timeUtil.format(date, 'yyyy-MM-dd hh:mm:ss')
        }
      },

      data(){
        return {
          pageParam: {
            total: 0,
            pageSize: 10,
            pageNum: 1,
            totalPage: 0
          },

          query: {
            refId: this.refId,
            type: this.type,
            pageNum: 1,
            pageSize: 10
          },
          commentList: [],

        }

      },
      props: {
        refId: String,
        type: String
      },
      methods: {
        errorHandler() {

        },

        getCommentList(pageNum) {
          this.query.pageNum = pageNum;
          this.query.pageSize = this.pageParam.pageSize;

          travelApi.commentList(this.query).then(res => {
            this.commentList = res.data.body
            this.pageParam.total = res.data.total
            this.pageParam.totalPage = res.data.totalPage

          })
        },
      },

      mounted() {
        this.getCommentList(this.pageParam.pageNum);
      }
    }
</script>

<style scoped>
  .comments-detail {
    padding-top: 20px;
    border-bottom: 1px dashed #ccc;
  }
  .comments-list .user-name {
    color: orange;
    font-size: 18px;
  }
</style>
