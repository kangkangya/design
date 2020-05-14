<template>
  <div class="row">
    <div class="col-sm">
      <div class="card-header">
        <div class="row">
          <div class="col-6 col-md-4 "><h5>附近景点</h5></div>
          <div class="col-6 col-md-5"></div>
          <div class="col-6 col-md-3">&nbsp;
            <router-link to="/scenic-spoit">查看更多>></router-link>
          </div>
        </div>
      </div>
      <div class="row" style="height:20px"></div>
      <div class="row">
        <div class="col" v-for="scenic in scenicList1">
          <div class="card" style="width: 14rem;">
            <img :src="imgUrl + 'scenic/' + scenic.picUrl" class="card-img-top" alt="...">
            <div class="card-body">
              <p class="card-text">{{scenic.summary}}</p>
            </div>
          </div>
        </div>
      </div>
      <div class="row" style="height:30px"></div>
      <div class="row">
        <div class="col" v-for="scenic in scenicList2">
          <div class="card" style="width: 14rem;">
            <img :src="imgUrl + 'scenic/' + scenic.picUrl" class="card-img-top" alt="...">
            <div class="card-body">
              <p class="card-text">{{scenic.summary}}</p>
            </div>
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
    name: "near_hotel_list",
    data() {
      return {
        scenicList1: [],
        scenicList2: [],
        imgUrl: config.dev.imgUrl
      }
    },
    methods: {
      getList() {
        indexApi.scenicList().then(res => {
          this.scenicList1 = res.data.splice(0, 2);
          this.scenicList2 = res.data.splice(0, 2);
        })
      }
    },
    mounted() {
      this.getList();
    }
  }
</script>

<style scoped>
  .card {
    border: 0;

  }

  .card img {
    height: 100%;
  }

  .card-body {
    padding: 0px 30px;
  }
</style>
