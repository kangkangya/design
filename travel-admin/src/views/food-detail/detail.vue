<template>
  <div class="box">
    <div class="introduction">
      <p class="title" >{{tableData.title}}</p>
      <p class="score">{{tableData.score}}</p>
      <p class="address">地址：{{tableData.address}}</p>
    </div>
    <div class="img" contenteditable="">
      <div class="img-list">
        <img :src=" baseUrl + tableData.picUrl" alt="图片加载失败"></img>
      </div>
      <!--<Img></Img>-->
    </div>
    <div class="row save">
      <div class="col-10"></div>
      <div class="col-1"><el-button @click="clickEdit" type="primary" style="float: right;">编辑</el-button></div>
      <el-dialog title="编辑美食" :visible.sync="dialogFormAddVisible">
        <el-form :model="form">
          <el-form-item label="图片" :label-width="formLabelWidth">
            <upload ref="uploadImg" :uploadPram="uploadParam" ></upload>

            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="form.picUrl" alt="">
            </el-dialog>
          </el-form-item>

          <el-form-item label="美食标题" :label-width="formLabelWidth">
            <el-input v-model="form.title" autocomplete="off">
              <el-select style="width: 100px" v-model="form.foodCode" slot="prepend" placeholder="美食分类">
                <el-option label="川菜" value="00000"></el-option>
                <el-option label="浙菜" value="10000"></el-option>
                <el-option label="黑菜" value="20000"></el-option>
                <el-option label="鲁菜" value="30000"></el-option>
                <el-option label="粤菜" value="40000"></el-option>
                <el-option label="湘菜" value="50000"></el-option>
              </el-select>
            </el-input>
          </el-form-item>
          <el-form-item label="地址" :label-width="formLabelWidth">
            <el-input v-model="form.address" autocomplete="off">
              <el-select style="width: 100px" v-model="form.regionCode" slot="prepend" placeholder="美食地区">
                <el-option label="东区" value="10000"></el-option>
                <el-option label="西区" value="20000"></el-option>
                <el-option label="仁和区" value="00000"></el-option>
                <el-option label="米易县" value="30000"></el-option>
                <el-option label="盐边县" value="40000"></el-option>
              </el-select>
            </el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormAddVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleEdit()">确 定</el-button>
        </div>
      </el-dialog>
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
            pageParam: {
              total: 150,
              pageSize: 15,
              pageNum: 1,
              totalPage: 0
            },
            refId: this.$route.query.id,
            type: 'food',
            baseUrl: 'http://localhost:8888/img/food/',
            tableData: [],
            form: {
              id: '',
              foodCode: '',
              regionCode: '',
              title: '',
              picUrl: '',
              commentNum: '',
              score: '',
              address: ''
            },
            formLabelWidth: '120px',
            dialogVisible: false,
            dialogFormAddVisible: false,
            uploadParam: {
              refId: 'food',
              imgCode: 'food'
            },
          }
      },
      methods: {
        //编辑按钮点击函数
        clickEdit(){
            this.form = this.tableData;
            this.dialogFormAddVisible = true;
        },

        messageSuccess(m){
          this.$message({
            message:m
          });
        },
        messageError(m){
          this.$message.error({
            message:m
          })
        },


        handleEdit()
        {
          if (this.form.picUrl === undefined
            || this.form.picUrl === ''
            || this.form.picUrl === null) {
            this.messageError('图片不能为空!');
            return false;
          }

          if (this.form.foodCode === undefined
            || this.form.foodCode === ''
            || this.form.foodCode === null) {
            this.messageError('食物分类不能为空!');
            return false;
          }

          if (this.form.regionCode === undefined
            || this.form.regionCode === ''
            || this.form.regionCode === null) {
            this.messageError('地区不能为空!');
            return false;
          }

          if (this.form.title === undefined
            || this.form.title === ''
            || this.form.title === null) {
            this.messageError('标题不能为空!');
            return false;
          }

          if (this.form.address === undefined
            || this.form.address === ''
            || this.form.address === null) {
            this.messageError('地址不能为空!');
            return false;
          }
          travelApi.foodEdit(this.form).then(res => {
            if (res.code === 200){
              this.dialogFormAddVisible = false;
              this.messageSuccess("修改成功");
              this.getList();
              this.form = {};
            }else {
              this.messageError("修改失败");
            }
          })

        },
        getList() {
          let param = {
            id: this.$route.query.id
          };
          travelApi.findFoodById(param).then(res => {
            this.tableData = res.data.body;
          });

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
  .introduction {
    text-align: center;
  }
  .img {
    padding: 20px 0 0 0;
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

</style>
