<template>
    <div class="container">
      <!--===============header========-->
      <div class=" hotel-detail-header">
        <h4>米易和合家欢酒店</h4>
        <small class="text-muted">地址：银苍路17号</small>
      </div>
      <div class="row hotel-img">
        <div class="hotel-img-big" ><img src="../../static/img/comments.png" alt=""></div>
        <ul class="hotel-img-small">
          <li><img src="../../static/img/comments.png" alt=""></li>
          <li><img src="../../static/img/comments.png" alt=""></li>
          <li><img src="../../static/img/comments.png" alt=""></li>
          <li><img src="../../static/img/comments.png" alt=""></li>
          <li><img src="../../static/img/comments.png" alt=""></li>
          <li><img src="../../static/img/comments.png" alt=""></li>
        </ul>
      </div>
      <div class="row hotel-navbar">
        <div class="col-10 hotel-navbar-left">
          <p>预定</p>
          <p>攻略</p>
          <p>点评</p>
          <p>周边酒店</p>
        </div>
        <div class="col-2 hotel-navbar-right">
          <MyComments></MyComments>
        </div>
      </div>
      <div class="row predetermine">
        <div class="row" style="padding-bottom: 20px;">
          <!--日期选择-->
          <div class="block">
            <span class="demonstration">入住时间</span>
            <el-date-picker
              v-model="date"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </div>
          <!--人数选择-->
          <div class="col-2"><el-input placeholder="成人人数" v-model="adult" ></el-input></div>
          <div class="col-2"><el-input placeholder="儿童人数" v-model="children" ></el-input></div>
          <el-button type="primary">查看价格</el-button>
        </div>
        <div class="row">
          <el-table
            border
            :data="tableData"
            style="width: 100%"
            :row-style="{height:'230px'}"
            :cell-style="{padding:'0px'}"
          >
            <el-table-column
              prop="house"
              label="房型"
              width="260">
            </el-table-column>
            <el-table-column
              prop="number"
              label="人数上限"
              width="190">
            </el-table-column>
            <el-table-column
              prop="policy"
              width="220"
              label="相关政策">
            </el-table-column>
            <el-table-column
              prop="price"
              width="220"
              label="价格">
            </el-table-column>
            <el-table-column
              prop="rooms"
              width="240"
              label="预定房间数">
              <template slot-scope="scope">
                <router-link class="nav-link" to="/order">
                  <el-button
                    size="mini"
                    type="primary"
                    @click="handleOrder(scope.$index, scope.row)">预定</el-button>
                </router-link>
              </template>

            </el-table-column>
          </el-table>

        </div>
      </div>
<!--=========基本信息=====-->
      <div class="row basic-information">
        <div class="col-2">基本信息</div>
        <div class="col-10">
          <div class="row row-cols-4">
            <div class="col">入住时间:<h4>14:00</h4>之后</div>
            <div class="col">离店时间:<h4>12:00</h4>之前</div>
            <div class="col">建成于:<h4>1997</h4>年</div>
            <div class="col">酒店规模:<h4>30</h4>间客房</div>
          </div>
        </div>
      </div>
<!--=============主要设施============-->
      <div class="row infrastructure">
        <div class="col-2">主要设施</div>
        <div class="col-10 border-dashed">
          <div class="row row-cols-5">
            <p>wifi服务</p>
            <p>电梯</p>
            <p>免费停车场</p>
            <p>24小时服务</p>
            <p>热水壶</p>
            <p>吹风机</p>
            <p>寄存行李</p>
          </div>
        </div>
      </div>
<!--=============酒店服务============-->
      <div class="row hotel-service">
        <div class="col-2">酒店服务</div>
        <div class="col-10 border-dashed">
          <div class="row row-cols-5">
            <p >
              <i class="el-icon-check"></i>
              洗衣服务
            </p>
            <p >
              <i class="el-icon-check"></i>
              邮政服务
            </p>
            <p>
              <i class="el-icon-check"></i>
              旅游服务
            </p>
            <p>
              <i class="el-icon-check"></i>
              客房服务
            </p>
            <p>
              <i class="el-icon-check"></i>
              自行车租赁
            </p>
            <p>
              <i class="el-icon-check"></i>
              快速入住服务
            </p>
            <p>
              <i class="el-icon-check"></i>
              叫车服务
            </p>
          </div>
        </div>
      </div>
<!--==============酒店设施===========-->
      <div class="row hotel-facility ">
        <div class="col-2">酒店设施</div>
        <div class="col-10 border-dashed">
          <div class="row row-cols-5">
            <p >
              <i class="el-icon-check"></i>
              咖啡厅
            </p>
            <p >
              <i class="el-icon-check"></i>
              酒吧
            </p>
            <p>
              <i class="el-icon-check"></i>
              吸烟区
            </p>
            <p>
              <i class="el-icon-check"></i>
              西餐厅
            </p>
            <p>
              <i class="el-icon-check"></i>
              台球
            </p>
            <p>
              <i class="el-icon-check"></i>
              无电梯
            </p>
            <p>
              <i class="el-icon-check"></i>
              收费停车场
            </p>
          </div>
        </div>
      </div>
<!--================房间设施===========-->
      <div class="row room-facility">
        <div class="col-2">房间设施</div>
        <div class="col-10 border-dashed">
          <div class="row row-cols-5">
            <p >
              <i class="el-icon-check"></i>
              叫醒服务
            </p>
            <p >
              <i class="el-icon-check"></i>
              国内长途电话
            </p>
            <p>
              <i class="el-icon-check"></i>
              24小时热水
            </p>
            <p>
              <i class="el-icon-check"></i>
              浴衣
            </p>
            <p>
              <i class="el-icon-check"></i>
              雨伞租借服务
            </p>
            <p>
              <i class="el-icon-check"></i>
              多种规格电源插座
            </p>
            <p>
              <i class="el-icon-check"></i>
              阳台/露台
            </p>
            <p>
              <i class="el-icon-check"></i>
              电视
            </p>
            <p>
              <i class="el-icon-check"></i>
              淋浴设施
            </p>
            <p>
              <i class="el-icon-check"></i>
              独立卫浴间
            </p>
            <p>
              <i class="el-icon-check"></i>
              坐卧两用长沙发
            </p>
            <p>
              <i class="el-icon-check"></i>
              坐卧两用长沙发
            </p>
            <p>
              <i class="el-icon-check"></i>
              宽带上网
            </p>
            <p>
            <i class="el-icon-check"></i>
              坐卧两用长沙发
          </p>
            <p>
            <i class="el-icon-check"></i>
            宽带上网
          </p>




          </div>
        </div>
      </div>
<!--================酒店攻略=======-->
      <div class="row hotel-strategy">
        <div class="col-2">酒店攻略</div>
        <div class="col-7">
          <p>
            <pre>
              必读
                旅行圣经《lonely planet》大理住宿“优选”推荐。
                古城核心难得的白族大院，出入古城交通便捷，闹中取静。
                旅舍花园很大，树木葱茏，鲜花争奇斗艳，还设有非常大的公共区域。
                旅舍附近有银行、菜市场、邮局、医院 。

                贴士
                旅舍附近就有超市和各种特色小吃，非常方便。
                蜂蜂们还可以在公共区域玩桌球、打乒乓，认识新朋友。
                旅社的厨师手艺不错，喜爱美食的蜂蜂怎能错过？
            </pre>
          </p>
        </div>
      </div>
<!--=============评论==========-->
      <div class="hotel-comment">
        <div class="row hotel-comment-navbar">
          <div class="col-10 all-comment">全部评论</div>
          <div class="col-2"><MyComments></MyComments></div>
        </div>
        <div class="row" style="padding-top: 20px; border-bottom: 1px dashed #ccc;">
                <div class="col-2"><p style="width: 60px; height: 60px; margin-left: 20px; border-radius: 50%; border: 1px solid #ccc;"><img src="" alt=""></p></div>
                <div class="col-10">
                  <div class="row" style="color: orange;">用户名xx</div>
                  <div class="row"><small class="text-muted">点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容</small></div>
                  <div class="row" style="padding: 20px 0px 20px 0;">
                    <div class="col-3"><img src="../../static/img/comments.png" alt=""></div>
                    <div class="col-3"><img src="../../static/img/comments.png" alt=""></div>
                    <div class="col-3"><img src="../../static/img/comments.png" alt=""></div>
                    <div class="col-3"><img src="../../static/img/comments.png" alt=""></div>
                  </div>
                  <div class="row"><small class="text-muted">2018-06-28 20:30:55</small></div>
                  <Reply>回复</Reply>
                </div>
              </div>
        <div class="row" style="padding-top: 20px; border-bottom: 1px dashed #ccc;">
                <div class="col-2"><p style="width: 60px; height: 60px; margin-left: 20px; border-radius: 50%; border: 1px solid #ccc;"><img src="" alt=""></p></div>
                <div class="col-10">
                  <div class="row" style="color: orange;">用户名xx</div>
                  <div class="row"><small class="text-muted">点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容点评内容</small></div>
                  <div class="row" style="padding: 20px 0px 20px 0;">
                    <div class="col-3"><img src="../../static/img/comments.png" alt=""></div>
                    <div class="col-3"><img src="../../static/img/comments.png" alt=""></div>
                    <div class="col-3"><img src="../../static/img/comments.png" alt=""></div>
                    <div class="col-3"><img src="../../static/img/comments.png" alt=""></div>
                  </div>
                  <div class="row"><small class="text-muted">2018-06-28 20:30:55</small></div>
                  <Reply>回复</Reply>
                </div>
              </div>
      </div>
    </div>
</template>

<script>
  import MyComments from '../components/my_comments'
  import Reply from '../components/reply'

    export default {
        name: "HotelDetail",
      components: {
        Reply,
        MyComments
      },
      data() {
        return {
          date:'',
          adult: '',
          children: '',
          tableData: [{
            house: '阳光大床房img',
            number: '2人',
            policy: '含早餐，在线付，不可取消和变更',
            price: '111',
            rooms: '9'
          }, {
            house: '阳光大床房img',
            number: '2人',
            policy: '含早餐，在线付，不可取消和变更',
            price: '111',
            rooms: '9'
          }]
        }
      },
      methods: {
        handleOrder(index, row) {
          console.log(index, row);
        }
      }
    }
</script>

<style scoped>
.hotel-detail-header {
  padding: 20px 0;
}
.hotel-img .hotel-img-big {
  width: 680px;
  height: 380px;
}
.hotel-img {
  padding: 0 70px 20px 70px;
}
.hotel-img .hotel-img-big img {
  border-radius: 8px;
  width: 680px;
  height: 380px;
}
.hotel-img .hotel-img-small {
  padding: 0;
  margin: 0;
  width: 320px;
  height: 380px;
  float: right;
}

.hotel-img .hotel-img-small li{
  list-style: none;
  width: 150px;
  height: 120px;
  display: inline;
  margin: 0 0 10px 10px;
  float: left;
}
.hotel-img .hotel-img-small li img {
  width: 150px;
  height: 120px;
  border-radius: 5px;
}
.hotel-navbar {
  box-shadow: 0 5px 5px #ccc;
}
  .hotel-navbar .hotel-navbar-left p {
    padding: 17px 20px 0 20px;
    display: inline-block;
    font-size: 20px;
  }
  .hotel-navbar-right {
    padding-top: 10px;
  }
  .predetermine {
    padding-top: 50px;
  }

  .basic-information {
    padding: 70px 0 30px 0;
    border-bottom: 1px solid #ccc;
  }
  .basic-information h4 {
    display: inline;
    padding: 0 4px;
  }
.infrastructure,.hotel-service,.hotel-facility,.room-facility,.hotel-strategy {
  padding-top: 40px;
}
.infrastructure p,.hotel-service p,.hotel-facility p,.room-facility p,.hotel-strategy pre {
  font-size: 14px;
  color: #666;
}
.border-dashed {
  border-bottom: 1px dashed #ccc;
}
.hotel-strategy {
  border-bottom: 1px solid #ccc;
}
  .hotel-comment {
    padding: 20px 0;
  }
  .all-comment {
    font-weight: bold;
    font-size: 18px;
  }
  .hotel-comment-navbar {
    border-bottom: 1px solid #ccc;
    padding-bottom: 15px;
  }
.el-table__body tr,
.el-table__body td {
  padding: 0;
  height: 40px;
}
</style>
