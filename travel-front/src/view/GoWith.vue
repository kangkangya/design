<template>
  <div class="container">
    <!--===============navigate==============-->
    <div class="row" style="height:5px"></div>
    <div class="row">
      <div class="w-100">
        <Navigate selected_idx="5"></Navigate>
      </div>
    </div>

    <div class="row" style="height: 20px"/>

    <!--===========banner============-->
    <div class="row">
      <img src="static/img/login_background.jpg" style="width: 100%;">
    </div>

    <!--================结伴热门目的地=============-->
    <div class="row" style="height:40px"></div>

    <div class="row">
      <div class="col" style="left: 2%">
        <h4><strong>热门结伴目的地</strong></h4>
      </div>
    </div>
    <!--=========结伴条件选择===========-->
    <div class="row" style="height: 20px"/>
    <div class="row place-choice">
      <div class="col-4">
        <div class="input-group" style="left: 50px">
          <input type="text" class="form-control" placeholder="目的地" v-model="query.destination">
          <div class="input-group-btn" style="left: 40px;">
            <button type="button" class="btn btn-default dropdown-toggle"
                    data-toggle="dropdown" aria-haspopup="true"
                    aria-expanded="false">出发时间<span class="caret"></span></button>
            <ul class="dropdown-menu dropdown-menu-right">
              <li v-for="travel in travelTimes">
                <button type="button" class="btn btn-default" @click="search(travel.code)">{{travel.name}}
                </button>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="col-4" style="left: 90px">
        <button type="button" class="btn btn-outline-secondary" @click="choseButton(1)">热门结伴</button> &nbsp;
        <button type="button" class="btn btn-outline-secondary" @click="choseButton(2)">最新发表</button> &nbsp;
        <button type="button" class="btn btn-outline-secondary" @click="choseButton(3)">立即出发</button> &nbsp;
      </div>
      <div class="col-4" style="left: 100px">
        <div v-if="isLogin">
          <router-link to="/go-with-plan">
            <button type="button" class="btn btn-outline-primary"><strong>发布我的结伴计划+</strong></button>
          </router-link>
        </div>
      </div>
    </div>

    <!--==========结伴计划======-->
    <div class="row " style="height:25px"></div>

    <div class="row row-cols-5">
      <div class="col" style="margin:26px;" v-for="goWith in goWithList">
        <router-link class="nav-link" :to="{path:'/go-with-detail',query: {id:goWith.id}}">
          <div class="card  btn btn-light text-left" style="width: 16rem;border:0">
            <div style="position: relative; width: 100%">
              <img :src="imgUrl + 'go-with/' + ((goWith.picUrl === undefined || goWith.picUrl === null || goWith.picUrl === '')?'default_goWith_url.jpeg': goWith.picUrl)" class="card-img" alt="">
              <h3 style="position: absolute; bottom: 0; left: 110px; color: white">
                <p class="card-text">
                  <strong>{{goWith.travelDay}}天后</strong>
                </p>
              </h3>
            </div>
            <div class="card-body">
              <h5 class="card-title"><p class="card-text"><strong>{{goWith.target}}|{{goWith.title}}</strong></p></h5>
              <br>
              <p class="card-text product-buyer-name-copy">{{goWith.summary}}</p><br>
              <p class="card-text product-buyer-name">
                <img class="img-circle image-responsive" style="width: 40px;height: 40px;left: 0px;"
                     :src="imgUrl + 'user/' + ((goWith.user.picUrl === null || goWith.user.picUrl === '' || goWith.user.picUrl === undefined) ? 'default_head_url.jpeg' : goWith.user.picUrl)"
                     alt="头像"/>
                &nbsp;&nbsp;&nbsp;<strong style="color: red">{{(goWith.user.nickName === null || goWith.user.nickName
                === '' || goWith.user.nickName === undefined) ? '旅游网站用户' : goWith.user.nickName}}</strong>
              </p>
              <p class="card-text">已有<strong style="font-size:28px;">{{((goWith.attentionNum === null)? 0:goWith.attentionNum )}}</strong>人关注</p>
            </div>
          </div>
        </router-link>
      </div>
    </div>

    <div class="row" style="height: 40px"/>

    <div class="row">
      <div class="col" style="left: 15%">
        <pager :pageParam="pageParam" :page-num-change="pageNumChange"/>
      </div>
    </div>

    <div class="row" style="height: 90px"/>
  </div>
</template>

<script>
  import Navigate from '../components/navigate'
  import config from '../../config/index'
  import goWithApi from "../api/goWith"
  import hotGoWithApi from "../api/hotGoWith"
  import pager from "../components/pager"

  export default {
    components: {
      Navigate, pager
    },
    data() {
      return {
        isLogin: false,
        date: '',
        imgUrl: config.dev.imgUrl,
        hotGoWithList: [],
        goWithList: [],
        travelTimes: [
          {code: '0', name: '全部出发时间'},
          {code: '1', name: '今天'},
          {code: '2', name: '1个月内'},
          {code: '3', name: '1-3个月内'},
          {code: '4', name: '3个月以上'}
        ],
        query: {
          destination: '',
          search: '',
          travelTime: '',
          hotGoWithId: '',
          pageNum: 1,
          pageSize: 12
        },
        pageParam: {
          total: 0,
          pageSize: 12,
          pageNum: 1,
          totalPage: 0
        }
      }
    },
    methods: {
      pageNumChange(newPageNum, oldPageNum) {
        this.pageParam.pageNum = newPageNum;
        this.getGoWithList(newPageNum);
      },

      search(travelTime) {
        this.query.travelTime = travelTime;
        this.query.search = '';
        this.getGoWithList(1);
      },

      getHotGoWithList() {

        goWithApi.hotGoWithList().then(res => {
          console.log(res);
        })
      },

      choseButton(code) {
        this.query.search = code;
        this.query.travelTime = '';
        this.getGoWithList(1);
      },

      getGoWithList(pageNum) {
        this.query.pageNum = pageNum;
        this.query.pageSize = this.pageParam.pageSize;

        goWithApi.list(this.query).then(res => {
          this.goWithList = res.data.body
          this.pageParam.total = res.data.total
          this.pageParam.totalPage = res.data.totalPage
        })
      }
    },
    mounted() {
      this.getHotGoWithList();
      this.getGoWithList(this.pageParam.pageNum);
      this.isLogin = this.$store.getters.isLogin;
    }
  }
</script>

<style scoped>
  .product-buyer-name {
    max-width: 200px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .product-buyer-name-copy {
    overflow: hidden;
    -webkit-line-clamp: 2;
    line-clamp: 2;
    -webkit-box-orient: vertical;
    box-orient: vertical;
    display: -webkit-box;
  }

  .gowith-hotplace .card-deck {
    margin-bottom: 30px;
  }

  .gowith-hotplace .card-title {
    font-weight: bold;
  }

  .place-choice {
    padding-bottom: 35px;
    border-bottom: 1px solid #ccc;
  }

  .place-choice input {
    height: 40px;
    border: 1px solid #DCDFE6;
    border-radius: 4px;
  }

  .place-choice .block {
    display: inline-block;
  }

  .place-choice .place-choice-plan h5 {
    height: 100%;
    line-height: 48px;
    margin: 0;
    background-color: #ccc;
    text-align: center;
  }

  .go-with-content .card-text {
    height: 50px;
    overflow: hidden;
  }

  .place-choice-plan .nav-link {
    padding: 0;
  }

  .go-with-content img {
    width: 286px;
    height: 286px;
  }
</style>
