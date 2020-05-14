<template>
  <div class="container">
    <div class="row" style="height:5px"></div>
    <div class="row">
      <div class="w-100">
        <Navigate selected_idx="3"></Navigate>
      </div>
    </div>

    <div class="row">
      <div class="w-100">
        <!--==========header==========-->
        <div class="row header">
          <h4>攀枝花美食</h4>
        </div>
        <!--=========美食条件筛选==========-->
        <div class="row food-selection">
          <div class="col-4">
            <div class="btn-group dropright">
              <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                      aria-expanded="false">
                美食分类选择
              </button>
              <div class="dropdown-menu">
                <a href="#" class="dropdown-item" @click="selectFoodCategory('')">全部</a>
                <a href="#" class="dropdown-item" @click="selectFoodCategory(foodCategory.code)"
                   v-for="foodCategory in foodCategoryList">
                  {{foodCategory.name}}
                </a>
              </div>
            </div>
          </div>
          <div class="col-5">
            <div class="btn-group dropright">
              <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                      aria-expanded="false">
                地区选择
              </button>
              <div class="dropdown-menu">
                <a href="#" class="dropdown-item" @click="selectRegionCategory('')">全部</a>

                <a href="#" class="dropdown-item" @click="selectRegionCategory(region.code)"
                   v-for="region in regionList">
                  {{region.name}}
                </a>
              </div>
            </div>
          </div>
          <div class="col-3">
            <el-input placeholder="请输入美食关键字" v-model="searchKey" class="input-with-select">
              <el-button slot="append" icon="el-icon-search" @click="getList(0)"></el-button>
            </el-input>
          </div>
        </div>
      </div>
    </div>
    <!--========food-list======-->
    <div class="row">
      <FoodList :food-list="foodList" :page-param="pageParam"></FoodList>
    </div>

    <div class="row" style="height:40px"></div>

    <div class="row">
      <div class="col" style="left: 15%">
        <pager :pageParam="pageParam" :page-num-change="pageNumChange"/>
      </div>
    </div>

    <div class="row" style="height: 100px"/>
  </div>
</template>

<script>
  import Navigate from '../components/navigate'
  import FoodList from '../components/food_list'
  import foodCategoryApi from "../api/foodCategory"
  import regionApi from "../api/region"
  import foodApi from "../api/food"
  import pager from "../components/pager";

  export default {
    name: "Food",
    components: {
      Navigate,
      FoodList,
      pager
    },
    data() {
      return {
        foodCategoryList: [],
        regionList: [],
        foodList: [],
        searchKey: '',
        foodCode: '',
        regionCode: '',
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
        this.getList(newPageNum);
      },

      selectFoodCategory(code) {
        this.foodCode = code;
        this.getList(0);
      },

      selectRegionCategory(code) {
        this.regionCode = code;
        this.getList(0);
      },

      getList(pageNum) {
        let query = {
          pageNum: pageNum,
          pageSize: this.pageParam.pageSize,
          search: this.searchKey,
          regionCode: this.regionCode,
          foodCode: this.foodCode
        };

        foodApi.list(query).then(res => {
          this.foodList = res.data.body
          this.pageParam.total = res.data.total;
          this.pageParam.totalPage = res.data.totalPage;
        })
      }
    },
    mounted() {
      foodCategoryApi.list().then(res => {
        this.foodCategoryList = res.data
      })

      regionApi.list().then(res => {
        this.regionList = res.data
      })

      this.getList(1);
    }
  }
</script>

<style scoped>
  .header, .food-selection {
    padding: 20px 0;
    border-bottom: 1px solid #ccc;
  }

  .header h4 {
    color: orchid;
    font-weight: bold;
  }

  .header .input-with-select {
    width: 330px;
  }

</style>
