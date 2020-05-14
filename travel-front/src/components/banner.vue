<template>
  <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel" data-interval="2000">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleCaptions" v-for="(banner,idx) in bannerList" data-slide-to="0" :class="{ active: idx == 0 }"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item" v-for="(banner,idx) in bannerList" :class="{ active: idx == 0 }">
        <img :src="imgUrl + banner.picUrl" class="d-block w-100" alt="">
        <div class="carousel-caption d-none d-md-block">
          <h5>{{banner.title}}</h5>
          <p>{{banner.summary}}</p>
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
        bannerList: [],
        imgUrl: config.dev.imgUrl + 'index/banner/'
      }
    },
    mounted() {
      indexApi.bannerList().then(res => {
        this.bannerList = res.data
      })
    }
  }
</script>
