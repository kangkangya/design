<template>
    <div class="container">
      <div class="row food-title"><div class="col">{{foodDtlInfo.title}}</div></div>
      <div class="row food-banner">
        <div class="col-3">
          <div class="comments-grade">
            <h4>{{avgScore}}<small>分</small></h4>
            <p class="">
              <el-rate
                v-model="avgScore"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value}">
              </el-rate>
            </p>
            <h4 style="font-size: 30px;"><small class="text-muted">来自</small>{{totalCommentNum}}<small>条用户点评</small></h4>
          </div>
          <div v-if="showCommentButton" class="my-comments">
            <MyComments comment-type="food" :ref-ids="dtlId" :refresh-page="refreshPage"></MyComments></div>
          <div v-if="!showCommentButton" class="my-comments">
            登录后可以评价
          </div>
          <div style="color: #666">地址:<small>{{foodDtlInfo.address}}</small></div>
        </div>
        <div class="col-9 food-img">
          <div class="food-img-big">
            <img style="width: 510px; height: 320px" src="../../static/img/yangroumixian.png" alt="">
          </div>
          <div class="food-img-small">
            <div class="food-img-small-one"><img style="width: 300px; height: 155px" src="../../static/img/yangroumixian.png" alt=""></div>
            <div class="food-img-small-two"><img style="width: 300px; height: 155px" src="../../static/img/yangroumixian.png" alt=""></div>
          </div>
        </div>
      </div>
      <div class="row food-comments-list">
        <comment ref="comment" :ref-id="dtlId" :type="type" ></comment>
      </div>
    </div>
</template>

<script>
  import MyComments from '../components/my_comments'
  import Reply from '../components/reply'
  import config from "../../config";
  import foodDtl from "../api/food"
  import comment1 from "../api/comment";
  import Comment from '../components/comment'

  export default {
        name: "FoodDetail",
      components: {
        MyComments,
        Reply,
        Comment
      },
      data() {
          return {
            dtlId: this.$route.query.id,
            avgScore: 0.0,
            type: 'food',
            showCommentButton: false,
            grade : '',
            foodDtlInfo: {
              id: '',
              foodCode: '',
              regionCode: '',
              title: '',
              summary: '',
              address: '',
              picUrl: '',

            },
            totalCommentNum: 0,
            imgUrl: config.dev.imgUrl,
          }
      },
      methods: {
        refreshPage() {
          location.reload();
        },


        loadPage(){
          this.userInfo = JSON.parse(localStorage.getItem('userInfo'));

          if(this.userInfo != null && this.userInfo != undefined && this.userInfo.id != undefined) {
            this.showCommentButton = true;
          }

          var param = {
            id: this.$route.query.id,
          };

          foodDtl.findById(param).then(res => {
            this.foodDtlInfo = res.data.body;
          });

          let query = {
            refId: this.$route.query.id,
            type: 'food'
          };
          comment1.list(query).then(res => {
            this.totalCommentNum = res.data.total;
          })
        }
      },
      mounted: function () {
        this.loadPage();
        this.$nextTick(function () {
          this.avgScore = this.$refs.comment.avgScore;
          console.log(this.$refs.comment.avgScore)
        })
      },
    }
</script>

<style scoped>
  .container {
    background-color: #f3f3f3;
  }
.food-title {
  font-size: 22px;
  font-weight: bold;
  padding: 20px 0;
}
.food-banner {
  padding-bottom: 20px;
  border-bottom: 1px solid #ccc;
}
.food-img-big {
  width: 510px;
  height: 320px;
  float: left;
}
.food-img-small {
  width: 300px;
  height: 320px;
  float: right;
}
  .food-img-small .food-img-small-one {
    margin-bottom: 10px;
  }
  .food-img-small .food-img-small-one,.food-img-small-two {
    width: 100%;
    height: 155px;
  }
  .comments-grade {
    width: 100%;
    height: 180px;
    background-color: #b5ddff;
    padding: 20px 0 0 20px;
  }
.comments-grade h4 {
  font-size: 40px;
  color: #fff;
}
.comments-grade small {
  font-size: 15px;
  color: #666666;
}
.my-comments {
  position: relative;
  padding: 20px 0 20px 15px;

}
  .my-comments p{
    width: 80px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    background-color: orange;
    border-radius: 5px;
    position: absolute;
    right: 20px;
    bottom: 10px;
  }
  .food-comments-list {
    padding-top: 20px;
    padding-bottom: 15px;
    border-bottom: 1px dashed #ccc;
  }
  .head-img {
    border: 1px solid #ccc;
    width: 85px;
    height: 85px;
    border-radius: 50%;
  }
</style>
