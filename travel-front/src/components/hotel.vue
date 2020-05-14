<template>
  <div class="card border-light mb-3">
    <div class="card-header">
      <div class="row">
        <div class="col-6 col-md-4"><h4><strong>攀枝花酒店</strong></h4></div>
        <div class="col-6 col-md-6"></div>
        <div class="col-6 col-md-2">
          <router-link to="/hotel">
            <h5><strong>查看更多酒店 >></strong></h5>
          </router-link>
        </div>

      </div>
    </div>
    <div class="card-body">
      <div class="card-deck">
        <div class="card" v-for="hotel in hotelList1">
          <img :src="imgUrl + hotel.picUrl" class="card-img-top" alt="">
          <div class="card-body">
            <h5 class="card-title">{{hotel.title}}</h5>
            <p class="card-text">{{hotel.summary}}</p>
          </div>
          <div class="card-footer">
            <small class="text-muted">赞数({{hotel.likeNum}}) &#12288;&#12288;</small>
            <small class="text-muted">评论({{hotel.commentNum}}) &#12288;&#12288;</small>
            <small class="text-danger"><strong>￥ {{hotel.price}} 起</strong></small>
          </div>
        </div>
      </div>
      <div class="card-deck">
        <div class="card" v-for="hotel in hotelList2">
          <img :src="imgUrl + hotel.picUrl" class="card-img-top" alt="">
          <div class="card-body">
            <h5 class="card-title">{{hotel.title}}</h5>
            <p class="card-text">{{hotel.summary}}</p>
          </div>
          <div class="card-footer">
            <small class="text-muted">赞数({{hotel.likeNum}}) &#12288;&#12288;</small>
            <small class="text-muted">评论({{hotel.commentNum}}) &#12288;&#12288;</small>
            <small class="text-danger"><strong>￥ {{hotel.price}} 起</strong></small>
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
        hotelList1: [],
        hotelList2: [],
        imgUrl: config.dev.imgUrl + 'hotel/'
      }
    },
    methods: {},
    mounted() {
      indexApi.hotelList().then(res => {
        this.hotelList1 = res.data.splice(0, 3);
        this.hotelList2 = res.data.splice(0, 3);
      })
    }
  }
</script>
