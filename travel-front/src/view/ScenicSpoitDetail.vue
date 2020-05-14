<template>
  <div class="container-fluid">
    <div class="row" style="height: 60px;background-color: ghostwhite;">
      <div class="col-2" style="left: 20px;top: 14px;" @click="toScenicListPage()">
        <img class="btn" style="width: 80px;height: 38px;" src="static/img/back_icon4.jpg"/>
      </div>
      <div class="col-9"></div>
      <div class="col-1" style="top: 10px;">
        <router-link to="/personal">
          <img class="img-circle image-responsive" style="width: 40px;height: 40px;" :src="imgUrl + 'user/' + userInfo.picUrl" alt="头像"/>
        </router-link>
      </div>
    </div>

    <div class="row" style="height: 30px"/>

    <div class="container">
      <div class="row">
        <h2><strong>{{scenicDtlInfo.title}}</strong></h2>
      </div>

      <div class="row" style="border-bottom: 1px solid #ccc;height: 20px;"></div>
    </div>

    <div class="row" style="height: 20px;"></div>

    <div class="container">
      <div class="row">
        <div class="col-2">
          <h5><strong>概况</strong></h5>
        </div>
        <div class="col-8"/>
        <div v-if="showCommentButton" class="col-2">
          <my_comment comment-type="scenic" :ref-ids="dtlId" :refresh-page="refreshPage"></my_comment>
        </div>
        <div v-if="!showCommentButton" class="col-2">
          登录后可以评价
        </div>
      </div>
    </div>
    <div class="row" style="height:20px;"></div>

    <div class="jumbotron jumbotron-fluid bg-light">
      <div class="container">
        <div class="row" style="margin-top: -30px;margin-bottom: -30px" @click="toPictureList()">
          <div class="w-1000">
            <div class="sub">
              <div class="card bg-dark text-white">
                <img :src="imgUrl + 'scenic/' + scenicDtlInfo.picUrl" class="card-img" alt="..." style="height: 312px">
              </div>
            </div>
            <div class="sub1">
              <img src="../../static/img/comments.png">
            </div>
            <div class="sub1">
              <img src="../../static/img/comments.png">
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-9" style="margin-left: 10px;">
          <p class="card-text product-buyer-name-copy">
            {{scenicDtlInfo.summary}}
          </p>
        </div>
        <div class="col-2"/>
      </div>

      <div class="row" style="height:30px;border-bottom: 1px solid #ccc;"></div>

      <div class="row" style="margin-top: 30px;">
        <div class="col" style="margin-left: 10px;">
          <p>电话: {{scenicDtlInfo.phone}}</p>
        </div>
      </div>
      <br/>
      <div class="row" style="margin-top: 10px;">
        <div class="col" style="margin-left: 10px;">
          <p>地址: {{scenicDtlInfo.address}}</p>
        </div>
      </div>
      <br/>
      <div class="row">
        <div class="col" style="margin-left: 10px;">
          <p>交通: {{scenicDtlInfo.traffic}}</p>
        </div>
      </div>
      <br/>
      <div class="row">
        <div class="col" style="margin-left: 10px;">
          <p>门票: {{scenicDtlInfo.ticket}}</p>
        </div>
      </div>
      <br/>
      <div class="row">
        <div class="col" style="margin-left: 10px;">
          <p>开放时间: {{scenicDtlInfo.openTime}}</p>
        </div>
      </div>
    </div>

    <div class="row" style="height:20px;border-bottom: 1px solid #ccc;"></div>

    <!--=========点评======-->
    <div class="container" style="margin-top: 30px">
      <div class="row">
        <h4><strong>全部点评 (共有{{totalCommentNum}}条真实评价)</strong></h4>
      </div>

      <div class="row" style="height:20px;border-bottom: 1px solid #ccc;"></div>

      <div class="row" style="margin-top: 10px">
        <comment :ref-id="dtlId" :type="type" ></comment>
      </div>
    </div>

    <div class="row" style="height: 60px"/>
  </div>
</template>

<script>
  import Comment from '../components/comment'
  import config from "../../config"
  import my_comment from "../components/my_comments";
  import scenicDtl from "../api/scenicDtl";
  import comment1 from "../api/comment";

  export default {
    name: "ScenicSpoitDetail",
    data() {
      return {
        dtlId: this.$route.query.id,
        type: 'scenic',
        userInfo: {},
        showCommentButton: false,
        scenicDtlInfo: {
          id: '',
          picUrl: '',
          title: '',
          summary: '',
          regionCode: '',
          phone: '',
          address: '',
          traffic: '',
          ticket: '',
          openTime: ''
        },
        headerPicUrl: [],
        imgUrl: config.dev.imgUrl,
        totalCommentNum: 0
      }
    },
    components: {
      Comment, my_comment
    },
    methods: {
      refreshPage() {
        location.reload();
      },
      toScenicListPage() {
        this.$router.push({
          path: '/scenic-spoit'
        })
      },
      toPictureList() {
        this.$router.push({ name: 'PictureList', params: { id: this.$route.params.id, picUrls: this.picUrls }});
      },

      loadPage() {
        this.userInfo = JSON.parse(localStorage.getItem('userInfo'));

        if(this.userInfo != null && this.userInfo != undefined && this.userInfo.id != undefined) {
          this.showCommentButton = true;
        }

        // console.log(this.$route.query.id);

        var param = {
          id: this.$route.query.id,
        }

        scenicDtl.findById(param).then(res => {
          this.scenicDtlInfo = res.data.body;
        })

        let query = {
          refId: this.$route.query.id,
          type: 'scenic'
        };
        comment1.list(query).then(res => {
          this.totalCommentNum = res.data.total;
        })
      }
    },
    mounted: function () {
      this.loadPage();
    }
  }
</script>

<style scoped>
  .w-1000 {
    margin: 0 auto;
  }

  .sub {
    width: 700px;
    height: 370px;
    margin-left: 25px;
    float: left;
  }

  .sub1 {
    width: 340px;
    height: 190px;
    margin-left: 6px;
    float: left;
    margin-top: 1px;
  }
</style>
