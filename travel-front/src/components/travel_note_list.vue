<template>
  <div class="container">
    <div class="row">
      <div class="col-sm">
        <router-link class="nav-link" to="/travel-note-detail">
          <div class="row" v-for="travelNote in travelNoteList">
            <div class="col-6 col-md-6  btn btn-light" style="border: 0">
              <div class="card" style="width: 15rem;">
                <img :src="imgUrl + travelNote.picUrl" class="card-img" alt="">
              </div>
            </div>
            <div class="col-6 col-md-6">
              <div class="card-body">
                <h5 class="card-title">{{travelNote.title}}</h5>
                <p class="card-text comments-content">
                  <small class="text-muted card-break-word">{{travelNote.summary}}</small>
                </p>
                <p class="card-text">
                  <small class="text-muted">{{travelNote.browseNum}} 浏览</small>
                </p>
              </div>
            </div>
          </div>
        </router-link>
      </div>

      <div class="col-sm">
        <nearScenicSpoit></nearScenicSpoit>
      </div>
    </div>

    <div class="row" style="height:40px"></div>

    <div class="row">
      <pager :pageParam="pageParam" :page-num-change="pageNumChange"/>
    </div>
  </div>
</template>

<script>
  import pager from "./pager"
  import config from "../../config"
  import travelNoteApi from "../api/travel-note"
  import nearScenicSpoit from "./near_scenic_spoit";

  export default {
    components: {
      pager, nearScenicSpoit
    },
    data() {
      return {
        travelNoteList: [],
        imgUrl: config.dev.imgUrl + 'travel-note/',
        pageParam: {
          total: 0,
          pageSize: 15,
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
        travelNoteApi.list(query).then(res => {
          this.travelNoteList = res.data.body;
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

  .travel-note-list-img img {
    height: 100%;
  }

  .card-body {
    padding: 0px 30px;
  }

  .card-break-word {
    word-break: break-all;
    word-wrap: break-word;
  }
</style>
