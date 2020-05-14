<template>
  <div class="container">
    <div v-for="food in foodList">
    <router-link class="nav-link" :to="{path: '/food-detail', query:{id:food.id}}">
    <div class="row" >
          <div class="col-6 col-md-4">
            <div class="card" style="width: 18rem;">
              <img :src="imgUrl + food.picUrl" class="card-img" alt="">
            </div>
          </div>
          <div class="col-6 col-md-4">
            <div class="card-body">
              <h5 class="card-title">{{food.title}}</h5>
              <p class="card-text text-muted comments">最新点评:</p>
              <p class="card-text comments-content">
                <small class="text-muted card-break-word">{{food.newComment}}</small>
              </p>
            </div>
          </div>
          <div class="col-6 col-md-4">
            <div class="card-body">
              <p style="color: dodgerblue;">{{food.score}}<small class="text-muted"> 分</small></p>
              <p style="display: inline-block; color: dodgerblue;">{{food.commentNum}}</p><small
              class="text-muted"> 条用户点评</small>
            </div>
          </div>
      </div>
    </router-link>
    </div>

  </div>
</template>

<script>
  import config from '../../config/index';

  export default {
    name: "food_list",
    props: {
      foodList: Array
    },
    data() {
      return {
        imgUrl: config.dev.imgUrl + 'food/',
      }
    }
  }
</script>

<style scoped>
  .card {
    border: 0;
    padding-top: 20px;
  }

  .card img {
    height: 180px;
    width: auto;
  }

  .card-title {
    color: orange;
    font-weight: bold;
  }

  .comments {
    padding-top: 25px;
    margin-bottom: 0px;
  }

  .comments-content {
    padding-left: 10px;
    height: 75px;
    overflow: hidden;
  }

  .card-break-word {
    word-break: break-all;
    word-wrap: break-word;
  }
</style>
