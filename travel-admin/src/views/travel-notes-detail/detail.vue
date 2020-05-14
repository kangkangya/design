<template>
  <div class="box">
    <div class="content">
      <div class="header">
        <div class="demo-type">
          <el-avatar :size="60" src="https://empty" @error="errorHandler">
            <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
          </el-avatar>
        </div>
        <h4 class="username">一只大花猫~</h4>
        <h4 class="title">攀枝花公园一日游</h4>
      </div>
      <div class="row travel-dir-list">
        <div>
          <p class="el-icon-date"></p>
          出发时间:2018-11-11
        </div>
        <div>
          <p class="el-icon-sunny"></p>
          出行天数:1天
        </div>
        <div>
          <p class="el-icon-s-custom"></p>
          人数:1人
        </div>
        <div>
          <p class="el-icon-s-ticket"></p>
          人均费用:50RMB
        </div>

      </div>
      <div class="row travel-note-content">
        <div class=" travel-note-content-text">
          <h3>标题一</h3>
          <p>攀枝花 公园位于 攀枝花 东区炳草岗，地理位置是市中心，本地人也称之为炳草岗公园，是全市重点公园和旅游景区之一。她独具山城城市公园特色，占地80余亩，基本上可以概括为树木全覆盖的一个山地公园，公园山顶上是俯瞰 攀枝花 全景的最佳摄影点。因为我从学生时代和后来的工作时期都和它的距离非常近，经常去这里散步，积累了很多照片，记录了多年的光影变迁，现在一起分享给大家</p>
        </div>
        <div class="row travel-note-content-img">
          <div class="col-6"><img src="../../img/im.png" alt=""></div>
          <div class="col-6"><img src="../../img/im.png" alt=""></div>
        </div>
        <div class=" travel-note-content-text">
          <h3>标题一</h3>
          <p>攀枝花 公园位于 攀枝花 东区炳草岗，地理位置是市中心，本地人也称之为炳草岗公园，是全市重点公园和旅游景区之一。她独具山城城市公园特色，占地80余亩，基本上可以概括为树木全覆盖的一个山地公园，公园山顶上是俯瞰 攀枝花 全景的最佳摄影点。因为我从学生时代和后来的工作时期都和它的距离非常近，经常去这里散步，积累了很多照片，记录了多年的光影变迁，现在一起分享给大家</p>
        </div>
        <div class="row travel-note-content-img">
          <div class="col-6"><img src="../../img/im.png" alt=""></div>
          <div class="col-6"><img src="../../img/im.png" alt=""></div>
        </div>
      </div>
    </div>

    <Comments></Comments>
    <Pager :pageParam="pageParam"></Pager>
  </div>
</template>

<script>
  import Comments from '@/components/Comments/index.vue'
  import Pager from '../../../../travel-front/src/components/pager'

    export default {
        name: "detail",
      data() {
          return {
            pageParam: {
              total: 150,
              pageSize: 15,
              pageNum: 1,
              totalPage: 0
            },
          }
      },
      components: {
        Comments,
        Pager
      },
        methods: {
          errorHandler() {
            return true
          }
        }
    }
</script>

<style scoped>
  .header {
    padding: 20px 0px;
    height: 300px;
    background: url("../../img/im.png");
    background-size: 100% 100%;
  }
  .username {
    color: orange;
  }
  .box {
    padding: 30px;

  }
  .content {
    text-align: center;
    border-bottom: 1px solid #ccc;
  }
  .title {
    color: #ffffff;
    font-size: 30px;
    text-shadow: 1px 1px 1px black;
  }
  .travel-dir-list {
    padding: 20px 0;
  }
  .travel-dir-list p{
    font-size: 30px;
    color: orange;
    padding: 5px 15px 0 15px;
  }
  .travel-note-content {
    padding-bottom: 50px;
  }
  .travel-note-content-text h3 {
    padding: 20px 0;
    border-bottom: 5px solid #ccc;
  }
  .comments-detail {
    padding-top: 20px;
    border-bottom: 1px dashed #ccc;
  }
  .comments-list .user-name {
    color: orange;
    font-size: 18px;
  }
</style>
