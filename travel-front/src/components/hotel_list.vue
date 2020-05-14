<template>
  <div class="container">
    <div class="row">
      <div class="col-sm">
          <div class="card mb-3 btn btn-light" style="max-width: 600px; border: 0" v-for="hotel in hotelList">
            <router-link class="nav-link" :to="{path: '/hotel-detail', query:{id: hotel.id}}">
              <div class="row no-gutters">
                <div class="col-md-5">
                  <img :src="imgUrl + 'hotel/' + hotel.picUrl" class="card-img" alt="...">
                </div>
                <div class="col-md-7">
                  <div class="card-body">
                    <h4 class="card-title">{{hotel.title}}</h4>
                    <p class="card-text"><small class="text-muted">赞数 ({{hotel.likeNum}})</small></p>
                    <p class="card-text"><small class="text-muted">评论 ({{hotel.commentNum}})</small></p>
                    <p class="card-text"><small class="text-danger">
                      <h4><strong>￥ {{hotel.price}} 起</strong></h4>
                    </small></p>
                  </div>
                </div>
              </div>
            </router-link>
          </div>
      </div>
      <div class="col-sm">
        <nearScenicSpoit></nearScenicSpoit>
      </div>
    </div>

    <div class="row">
      <pager :pageParam="pageParam" :page-num-change="pageNumChange"/>
    </div>
  </div>
</template>

<script>
  import config from '../../config/index'
  import hotelApi from "../api/hotel"
  import pager from "./pager"
  import nearScenicSpoit from "./near_scenic_spoit";

  export default {
    name: "hotel_list",
    components: {
      pager, nearScenicSpoit
    },
    data() {
      return {
        hotelList: [],
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
        hotelApi.list(query).then(res => {
          this.hotelList = res.data.body;
          this.pageParam.total = res.data.total;
          this.pageParam.totalPage = res.data.totalPage;
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
