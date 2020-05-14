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
          <img :src=" baseUrl + scope.row.picUrl" alt="图片加载失败" style="width: 200px; height: 200px">
        </template>
      </el-table-column>
      <el-table-column
        label="标题"
        width="150"
        prop="title">
      </el-table-column>
      <el-table-column
        label="点赞数"
        width="100"
        prop="likeNum">
      </el-table-column>
      <el-table-column
        label="评论数"
        width="100"
        prop="commentNum">
      </el-table-column>
      <el-table-column
        label="游记数"
        width="100"
        prop="travelNoteNum">
      </el-table-column>
      <el-table-column
        width="100">

        <!--添加景点-->
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
          <el-dialog title="添加景点" :visible="dialogFormAddVisible">
            <el-form :model="form">
              <el-form-item label="图片" :label-width="formLabelWidth">
                <upload ref="uploadImg" :uploadPram="uploadParam" ></upload>
                <el-dialog :visible.sync="dialogVisible">
                  <img width="100%" :src="form.picUrl" alt="">
                </el-dialog>
              </el-form-item>
              <el-form-item label="景点标题" :label-width="formLabelWidth">
                <el-input v-model="form.title" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="景点简介" :label-width="formLabelWidth">
                <el-input type="textarea" v-model="form.summary" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="景点电话" :label-width="formLabelWidth">
                <el-input v-model="form.phone" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="景点地址" :label-width="formLabelWidth">
                <el-input v-model="form.address" autocomplete="off">
                  <el-select style="width: 100px" v-model="form.regionCode" slot="prepend" placeholder="请选择">
                    <el-option label="东区" value="10000"></el-option>
                    <el-option label="西区" value="20000"></el-option>
                    <el-option label="仁和区" value="00000"></el-option>
                    <el-option label="米易县" value="30000"></el-option>
                    <el-option label="盐边县" value="40000"></el-option>
                  </el-select>
                </el-input>
              </el-form-item>
              <el-form-item label="景点交通" :label-width="formLabelWidth">
                <el-input v-model="form.traffic" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="景点门票" :label-width="formLabelWidth">
                <el-input v-model="form.ticket" autocomplete="off">
                </el-input>
              </el-form-item>
              <el-form-item label="开放时间" :label-width="formLabelWidth">
                <el-input v-model="form.openTime" autocomplete="off"></el-input>
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
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          <router-link :to="{ path:'/scenic-spot-detail',query:{id:scope.row.id} }" >
            <el-button size="mini" type="primary">查看详情</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>
    <Pager :pageParam="pageParam" :page-num-change="pageNumChange"></Pager>
  </div>
</template>

<script>
  import Pager from '../../../../travel-front/src/components/pager'
  import travelApi from "../../api/travel";
  import upload from '../../components/Img/index'


  export default {
    name: "scenicSpotIndex",
    components: {
      Pager,
      upload
    },
    data() {
      return {
        region: '',
        pageParam: {
          total: 0,
          pageSize: 15,
          pageNum: 1,
          totalPage: 0,
        },
        tableData: [],
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
        search: '',
        dialogFormVisible: false,
        dialogFormAddVisible: false,
        formLabelWidth: '120px',
        dialogImageUrl: '',
        dialogVisible: false,
        uploadParam: {
          refId: 'scenic',
          imgCode: 'scenic_list'
        },
        baseUrl: 'http://localhost:8888/img/scenic/',
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


        //上传图片
        handleChange(file,fileList) {
          this.form.picUrl = file.url;
          // console.log("change"+this.form.picUrl)
        },
        handleError(err, file, fileList){
          console.log('上传图片失败，请重新上传！')
        },
        handleSuccess(file, fileList) {
          this.form.picUrl = file.url;
          // console.log('success'+ this.form.picUrl)
        },
        handleRemove(file, fileList) {
          console.log(file, fileList);
        },
        handlePictureCardPreview(file) {
          this.form.picUrl = file.url;
          this.dialogVisible = true;
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

          if (this.form.title === undefined
            || this.form.title === ''
            || this.form.title === null) {
            this.messageError('标题不能为空!');
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

          //发送请求
          travelApi.scenicSave(this.form).then(res=>{
            if(res.code === 200) {
              this.messageSuccess("添加成功！");
              this.form = {};

              this.dialogFormAddVisible = false;

              this.getList();

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

          travelApi.scenicDel(query).then(res => {
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
          travelApi.scenicList(query).then(res => {
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
