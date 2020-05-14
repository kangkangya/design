<template>
  <div class="card border-light mb-3">
    <div class="card-header">
      <div class="row">
        <div class="col-6 col-md-4"><h4><strong>攀枝花景点</strong></h4></div>
        <div class="col-6 col-md-6"></div>
        <div class="col-6 col-md-2">
          <router-link to="/scenic-spoit">
            <h5><strong>查看更多景点 >></strong></h5>
          </router-link>
        </div>
      </div>
    </div>
    <div class="card-body">
      <div class="card-deck">
        <div class="card" v-for="scenic in scenicList1">
          <img :src="imgUrl + scenic.picUrl" class="card-img-top" alt="">
          <div class="card-body">
            <h5 class="card-title">{{scenic.title}}</h5>
            <p class="card-text">{{scenic.summary}}</p>
          </div>
          <div class="card-footer">
            <small class="text-muted">赞数({{scenic.likeNum}}) &#12288;</small>
            <small class="text-muted">评论({{scenic.commentNum}}) &#12288;</small>
            <small class="text-muted">游记({{scenic.travelNoteNum}})</small>
          </div>
        </div>
      </div>
      <div class="card-deck">
        <div class="card" v-for="scenic in scenicList2">
          <img :src="imgUrl + scenic.picUrl" class="card-img-top" alt="">
          <div class="card-body">
            <h5 class="card-title">{{scenic.title}}</h5>
            <p class="card-text">{{scenic.summary}}</p>
          </div>
          <div class="card-footer">
            <small class="text-muted">赞数({{scenic.likeNum}}) &#12288;</small>
            <small class="text-muted">评论({{scenic.commentNum}}) &#12288;</small>
            <small class="text-muted">游记({{scenic.travelNoteNum}})</small>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import config from '../../config/index'
  import indexApi from "../api/index";

  export default {
    data() {
      return {
        scenicList1: [],
        scenicList2: [],
        imgUrl: config.dev.imgUrl + 'scenic/'
      }
    },
    methods: {},
    mounted() {
      indexApi.scenicList().then(res => {
        this.scenicList1 = res.data.splice(0, 4);
        this.scenicList2 = res.data.splice(0, 4);
      })
    }
  }
</script>
