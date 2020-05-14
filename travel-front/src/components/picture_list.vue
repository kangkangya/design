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

    <div class="container">
      <div class="row row-cols-4" style="margin-top: 20px;">
        <div class="col" style="margin:20px;" v-for="pics in picUrls">
          <div class="card" style="border: 0px;width: 310px;height: 210px;">
            <img :src="imgUrl + 'scenic/list/' + pics" class="card-img-top">
          </div>
        </div>
      </div>

      <div class="row" style="margin-bottom: 70px;"></div>
    </div>
  </div>
</template>

<script>
  import config from '../../config/index'

  export default {
    data() {
      return {
        userInfo: {},
        picUrls: this.$route.params.picUrls,
        imgUrl: config.dev.imgUrl,
      }
    },
    methods: {
      toScenicListPage() {
        let id = this.$route.params.id;
        this.$router.push({
          path: '/scenic-spoit-detail/' + id
        })
      }
    },
    mounted() {
      this.userInfo = JSON.parse(localStorage.getItem('userInfo'));
    }
  }
</script>
