<template>
  <div>
    <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      :row-style="{height:'230px'}"
      :cell-style="{padding:'0px'}"
      style="width: 100%">
      <el-table-column
        label="图片"
        width="250"
        prop="picUrl">
        <template slot-scope="scope">
          <img :src="baseUrl + scope.row.picUrl" alt="" style="width: 200px; height: 200px">
        </template>
      </el-table-column>
      <el-table-column
        label="标题"
        width="120"
        prop="title">
      </el-table-column>
      <el-table-column
        label="地址"
        width="120"
        prop="address">
      </el-table-column>
      <el-table-column
        label="点评数"
        width="100"
        prop="commentNum">
      </el-table-column>
      <el-table-column
        label="评分"
        width="100"
        prop="score">
      </el-table-column>
      <el-table-column
        width="100">
        <template slot="header" slot-scope="scope">
          <el-button
            class="filter-item"
            style="margin-left: 10px;"
            type="primary"
            icon="el-icon-edit"
            size="mini"
            @click="dialogFormAddVisible= true">
            Add
          </el-button>
          <el-dialog title="添加美食" :visible.sync="dialogFormAddVisible">
            <el-form :model="form">
              <el-form-item label="图片" :label-width="formLabelWidth">
                <upload ref="uploadImg" :uploadPram="uploadParam" ></upload>
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
              <el-button type="primary" @click="handleSave()">确 定</el-button>
            </div>
          </el-dialog>
        </template>
      </el-table-column>
      <el-table-column
        align="right">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
        </template>
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
          <router-link class="" :to="{ path: '/food-detail', query:{id:scope.row.id}}">
            <el-button size="mini" type="primary">查看详情</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>
      <Pager :pageParam="pageParam" :page-num-change="pageNumChange"/>
  </div>
</template>

<script>
  import Pager from '../../../../travel-front/src/components/pager'
  import travelApi from "../../api/travel";
  import upload from '../../components/Img/index'

    export default {
      name: "food",
      components: {
        Pager,
        upload,
      },
      data() {
        return {
          region: '',
          foodCategory: '',
          pageParam: {
            total: 0,
            pageSize: 15,
            pageNum: 1,
            totalPage: 0
          },
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
          search: '',
          dialogFormVisible: false,
          dialogFormAddVisible: false,
          formLabelWidth: '120px',
          dialogVisible: false,
          uploadParam: {
            refId: 'food',
            imgCode: 'food'
          }
        }
      },
      methods :
        {
          pageNumChange(newPageNum, oldPageNum) {
            this.pageParam.pageNum = newPageNum;
            this.getList();
          },

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

          clickEdit(index, row) {
            this.form = row;
            this.dialogFormVisible = true;
          },


          //提交添加
          handleSave(){
            this.form.picUrl = this.$refs.uploadImg.imgUrl;
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

            if (this.form.title === undefined
              || this.form.title === ''
              || this.form.title === null) {
              this.messageError('标题不能为空!');
              return false;
            }

            if (this.form.regionCode === undefined
              || this.form.regionCode === ''
              || this.form.regionCode === null) {
              this.messageError('地区不能为空!');
              return false;
            }

            if (this.form.address === undefined
              || this.form.address === ''
              || this.form.address === null) {
              this.messageError('地址不能为空!');
              return false;
            }

            travelApi.foodSave(this.form).then(res=>{
              if(res.code === 200) {
                this.dialogFormAddVisible = false;
                this.messageSuccess("添加成功！");

                this.getList();

                this.form = {};

                return true;
              }else {
                this.messageError(res.msg);
                return false;
              }
            })
          },

          handleDelete(index, row) {
            let query = {
              id: row.id
            };

            travelApi.foodDel(query).then(res => {
              this.messageSuccess('删除成功！');
              this.getList();
            })
          },

          getList() {
            let query = {
              pageNum: this.pageParam.pageNum,
              pageSize: this.pageParam.pageSize,
              search: this.search
            };
            travelApi.foodList(query).then(res => {
              this.tableData = res.data.body;

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

</style>
