<template>
  <div class="row">
    <div class="col-sm-8">
      <nav class="navbar navbar-expand-lg navbar-light">
        <ul class="navbar-nav">
          <li class="nav-item" v-for="(item, idx) in navItemList" :key="'nav_item_' + idx" :class="navActive(idx)">
            <router-link class="nav-link" :to="item.path">
              <h4 @click="selectTab(item.id)">{{item.name}}</h4>
            </router-link>
          </li>
        </ul>
      </nav>
    </div>
    <div v-if="!isLogin" class="col-sm-4 navbar-text ml-auto">
      <div class="row">
        <div class="col-sm-6"></div>
        <div class="col-sm-3">
          <router-link class="nav-link" to="/login">
            <button type="button" class="btn btn-light"><h5><strong>登录</strong></h5></button>
          </router-link>
        </div>
        <div class="col-sm-3">
          <router-link class="nav-link" to="/register">
            <button type="button" class="btn btn-light"><h5><strong>注册</strong></h5></button>
          </router-link>
        </div>
      </div>
    </div>
    <div v-if="isLogin" class="col-sm-4 navbar-text ml-auto">
      <div class="row">
        <div class="col-sm-6">
        </div>
        <div class="col-sm-2" >
          <router-link to="/personal">
            <img class="img-circle image-responsive" style="width: 40px;height: 40px;" :src="imgUrl + userInfo.picUrl" alt="头像"/>
          </router-link>
        </div>
        <div class="col-sm-4">
          <button type="button" class="btn btn-light" @click="loginOut()"><h6><strong>登出</strong></h6></button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import config from '../../config/index';

  export default {
    data() {
      return {
        navItemList: [
          {id: 0, name: '首页', path: '/'},
          {id: 1, name: '景点', path: '/scenic-spoit'},
          {id: 2, name: '酒店', path: '/hotel'},
          {id: 3, name: '美食', path: '/food'},
          {id: 4, name: '游记', path: '/travel-note'},
          {id: 5, name: '结伴', path: '/go-with'}
        ],
        current_idx: this.selected_idx,
        userInfo: {},
        imgUrl: config.dev.imgUrl + 'user/'
      }
    },
    props: {
      selected_idx: 0
    },
    methods: {
      navActive(idx) {
        if (this.current_idx == idx) {
          return 'page-item active'
        } else {
          return 'page-item'
        }
      },
      selectTab(idx) {
        this.current_idx = idx;
      },
      loginOut() {
        this.$store.commit("setUserInfo", {});
        this.$store.commit("loginFlag", false);
        localStorage.clear();
        this.$router.push({
          path: '/'
        })
      }
    },
    computed: {
      isLogin: function () {
        this.userInfo = this.$store.getters.getUserInfo;
        return this.$store.getters.isLogin;
      }
    }
  }
</script>
<style>
  .nav-link {
    display: inline-block;
    padding: 0;
  }
</style>
