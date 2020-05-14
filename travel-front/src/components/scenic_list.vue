<template>
  <div class="container">
    <div class="row">
      <div class="col-sm">

        <div class="card mb-3 btn btn-light" style="max-width: 600px; border: 0" v-for="scenic in scenicList">
          <router-link class="nav-link" :to="{path: '/scenic-spoit-detail', query:{id:scenic.id} }">

            <div class="row no-gutters">
              <div class="col-md-5">
                <img :src="imgUrl + 'scenic/' + scenic.picUrl" class="card-img" alt="">
              </div>
              <div class="col-md-7">
                <div class="card-body">
                  <h5 class="card-title">{{scenic.title}}</h5>
                  <p class="card-text"><small class="text-muted">赞数 ({{scenic.likeNum}})</small></p>
                  <p class="card-text"><small class="text-muted">评论 ({{scenic.commentNum}})</small></p>
                  <p class="card-text"><small class="text-muted">游记 ({{scenic.travelNoteNum}})</small></p>
                </div>
              </div>
            </div>
          </router-link>
        </div>
      </div>

      <div class="col-sm">
        <div class="card-header">
          <div class="row">
            <div class="col-6 col-md-4"><h5>附近酒店</h5></div>
            <div class="col-6 col-md-4"></div>
            <div class="col-6 col-md-4">&nbsp;
              <router-link to="/hotel">查看更多>></router-link>
            </div>
          </div>
        </div>
        <div class="row" style="height:20px"></div>
        <div class="row">
          <div class="col" v-for="hotel in hotelList1">
            <div class="card" style="width: 14rem;">
              <img :src="imgUrl + 'hotel/' + hotel.picUrl" class="card-img-top" alt="">
              <div class="card-body">
                <p class="card-text">{{hotel.summary}}</p>
              </div>
            </div>
          </div>
        </div>
        <div class="row" style="height:30px"></div>
        <div class="row">
          <div class="col" v-for="hotel in hotelList2">
            <div class="card" style="width: 14rem;">
              <img :src="imgUrl + 'hotel/' + hotel.picUrl" class="card-img-top" alt="">
              <div class="card-body">
                <p class="card-text">{{hotel.summary}}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <pager :pageParam="pageParam" :page-num-change="pageNumChange"/>
    </div>
  </div>
</template>

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

<script>
  import config from '../../config/index'
  import scenicApi from "../api/scenic"
  import indexApi from "../api/index";
  import pager from "./pager";

  export default {
    components: {
      pager
    },
    data() {
      return {
        scenicList: [],
        hotelList1: [],
        hotelList2: [],
        imgUrl: config.dev.imgUrl,
        pageParam: {
          total: 0,
          pageSize: 8,
          pageNum: 1,
          totalPage: 0
        }
      }
    },
    methods: {
      pageNumChange(newPageNum, oldPageNum) {
        this.pageParam.pageNum = newPageNum;
        this.getList();
      },

      getList() {
        let query = {
          pageNum: this.pageParam.pageNum,
          pageSize: this.pageParam.pageSize,
        };
        scenicApi.list(query).then(res => {
          this.scenicList = res.data.body;
          this.pageParam.total = res.data.total;
          this.pageParam.totalPage = res.data.totalPage;
        })

        indexApi.hotelList().then(res => {
          this.hotelList1 = res.data.splice(0, 2);
          this.hotelList2 = res.data.splice(0, 2);
        })
      }
    },
    mounted() {
      this.getList();
    }
  }
</script>
