<template>
  <div class="box" >
    <div v-bind="tableData">
      <div class="introduction">
        <p class="title"  >{{tableData.title}}</p>
        <div>
          <p class="el-icon-place"></p>
          <small class="text-muted list-detail">{{tableData.address}}</small>
        </div>
        <p>{{tableData.summary}}</p>
      </div>
      <div class="img">
        <div class="img-list">
          <img :src= "baseUrl + tableData.picUrl" alt="图片加载失败">
        </div>
<!--        <div><Img></Img></div>-->
      </div>
      <div class="scenic-spot-dir-list row">
        <div col-12>
          <div>
            <p class="el-icon-phone-outline"></p>
            电话:<p class="list-detail">{{tableData.phone}}</p>
          </div>

          <div>
            <p class="el-icon-truck"></p>
            交通:<p class="list-detail">{{tableData.traffic}}</p>
          </div>
          <div>
            <p class="el-icon-s-ticket"></p>
            门票:<p class="list-detail">{{tableData.ticket}}</p>
          </div>
          <div>
            <p class="el-icon-date"></p>
            开放时间:<p class="list-detail">{{tableData.openTime}}</p>
          </div>
        </div>

      </div>
    </div>

    <div class="row save">
      <div class="col-1" v-bind="form">
        <el-button @click="clickEdit()" type="primary" style="float: right;">编辑</el-button>
        <el-dialog title="景点信息修改" :visible="dialogFormVisible">
          <el-form :model="form">
            <el-form-item label="标题" :label-width="formLabelWidth">
              <el-input v-model="form.title" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址" :label-width="formLabelWidth">
              <el-input v-model="form.address">
                <el-select style="width: 140px" v-model="form.regionCode" slot="prepend" placeholder="选择景点地区">
                  <el-option label="东区" value="10000"></el-option>
                  <el-option label="西区" value="20000"></el-option>
                  <el-option label="仁和区" value="00000"></el-option>
                  <el-option label="米易县" value="30000"></el-option>
                  <el-option label="盐边县" value="40000"></el-option>
                </el-select>
              </el-input>
            </el-form-item>
            <el-form-item label="电话" :label-width="formLabelWidth">
              <el-input v-model="form.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="交通" :label-width="formLabelWidth">
              <el-input v-model="form.traffic" autocomplete="off"></el-input>
            </el-form-item>
            <div class="row">
              <el-form-item label="门票" :label-width="formLabelWidth">
                <el-input v-model="form.ticket" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="开放时间" :label-width="formLabelWidth">
                <el-input v-model="form.openTime" autocomplete="off"></el-input>
              </el-form-item>
            </div>
            <el-form-item label="描述" :label-width="formLabelWidth">
              <el-input type="textarea" v-model="form.summary" autocomplete="off"></el-input>
            </el-form-item>

            <!--<el-dialog v-if="form.picUrl" :visible.sync="true">
                <img width="100%" :src="form.picUrl" alt="">
            </el-dialog>-->
            <el-form-item label="添加图片" :label-width="formLabelWidth">
              <upload ref="uploadImg" :uploadPram="uploadParam" ></upload>
              <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="form.picUrl" alt="">
              </el-dialog>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleEdit">确 定</el-button>
          </div>
        </el-dialog>
      </div>
      <div class="col-10"></div>
      <!--<div class="col-1"><el-button @click="handleEdit（）" type="primary" style="float: right;">保存</el-button></div>-->
    </div>
    <comments :ref-id="refId" :type="type"></comments>
    <Pager :pageParam="pageParam"></Pager>
  </div>
</template>

<script>
  import comments from '../../components/Comments/index.vue'
  import Pager from '../../../../travel-front/src/components/pager'
  import travelApi from "../../api/travel";
  import upload from '../../components/Img/index'

  export default {
    name: "detail",
    components: {
      comments,
      Pager,
      upload
    },
    data() {
      return {
        dialogVisible: false,
        dialogFormVisible: false,
        formLabelWidth: '120px',
        scenic_id: '',
        refId: this.$route.query.id,
        type: 'scenic',
        tableData: [],
        pageParam: {
          total: 320,
          pageSize: 15,
          pageNum: 1,
          totalPage: 0
        },
        form: {
          id: '',
          picUrl: '',
          title: '',
          summary: '',
          regionCode: '',
          phone: '',
          address: '',
          traffic: '',
          ticket: '',
          openTime: ''
        },
        uploadParam: {
          refId: 'scenic',
          imgCode: 'scenic_dtl'
        },
        baseUrl: 'http://localhost:8888/img/scenic/',
      }
    },
    methods: {

      messageError(m) {
        this.$message.error({
          message: m
        })
      },
      messageSuccess(m) {
        this.$message({
          message: m
        })
      },

      clickEdit() {
        this.form = this.tableData;
        this.dialogFormVisible = true;
      },
      handleEdit() {
        this.form.picUrl = this.$refs.uploadImg.imgUrl;

        if (this.form.title === undefined
          || this.form.title === ''
          || this.form.title === null) {
          this.messageError('景点名称不能为空!');
          return false;
        }

        if (this.form.address === undefined
          || this.form.address === ''
          || this.form.address === null) {
          this.messageError('地址不能为空!');
          return false;
        }

        if (this.form.regionCode === undefined
          || this.form.regionCode === ''
          || this.form.regionCode === null) {
          this.messageError('地区不能为空!');
          return false;
        }

        if (this.form.summary === undefined
          || this.form.summary === ''
          || this.form.summary === null) {
          this.messageError('简介不能为空!');
          return false;
        }

        if (this.form.phone === undefined
          || this.form.phone === ''
          || this.form.phone === null) {
          this.messageError('电话不能为空!');
          return false;
        }

        if (this.form.picUrl === undefined
          || this.form.picUrl === ''
          || this.form.picUrl === null) {
          this.messageError('图片不能为空!');
          return false;
        }

        if (this.form.traffic === undefined
          || this.form.traffic === ''
          || this.form.traffic === null) {
          this.messageError('交通不能为空!');
          return false;
        }

        if (this.form.ticket === undefined
          || this.form.ticket === ''
          || this.form.ticket === null) {
          this.messageError('门票不能为空!');
          return false;
        }

        if (this.form.openTime === undefined
          || this.form.openTime === ''
          || this.form.openTime === null) {
          this.messageError('开放时间不能为空!');
          return false;
        }

        travelApi.scenicDetailEdit(this.form).then(res => {
          if (res.code === 200) {
            this.dialogFormVisible = false;
            this.messageSuccess('修改成功！');
            this.form = {};

            this.getList();

            return true;
          } else {
            this.messageError(res.msg);
            return false;
          }
        })
      },

      getList() {
        let param = {
          id: this.$route.query.id
        };
        travelApi.scenicDetailList(param).then(res => {
          this.tableData = res.data.body;
          console.log(this.tableData);
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
  .box {
    padding: 30px;

  }
  .row {
    padding: 15px;
  }
  .introduction {
    text-align: center;
  }
  .img {
    padding: 20px 0;
  }
  .img .img-list {
    padding-bottom: 20px;
  }
  .img .img-list img {
    width: 200px;
    height: 150px;
    padding-right: 5px;
  }
  .title {
    font-size: 30px;
    text-shadow: 2px 2px 2px #ccc;
    color: orange;
  }
.list-detail {
  display: inline-block;
}
</style>
