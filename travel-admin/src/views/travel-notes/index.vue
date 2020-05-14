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
          <img :src="scope.row.picUrl" alt="" style="width: 200px; height: 200px">
        </template>
      </el-table-column>
      <el-table-column
        label="标题"
        width="200"
        prop="title">
      </el-table-column>
      <el-table-column
        label="游记内容"
        width="200"
        prop="summary">
      </el-table-column>
      <el-table-column
        label="浏览数"
        width="100"
        prop="browseNum">
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
          <router-link class="" to="/travel-notes-detail">
            <el-button
              size="mini"
              type="primary">查看详情</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>
    <Pager :pageParam="pageParam"></Pager>
  </div>
</template>

<script>
  import Pager from '../../../../travel-front/src/components/pager'
  import travelApi from "../../api/travel";


  export default {
    name: "travel-notes",
    components: {
      Pager
    },
    data() {
      return {
        pageParam: {
          total: 150,
          pageSize: 15,
          pageNum: 1,
          totalPage: 0
        },
        tableData: [],
        form: {},
        search: '',
        dialogFormVisible: false,
        formLabelWidth: '120px',
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
        // handleChange(file,fileList) {
        //   this.form.picUrl = file.url;
        //   console.log("change"+this.form.picUrl)
        // },
        // handleError(err, file, fileList){
        //   console.log('上传图片失败，请重新上传！')
        // },
        // handleSuccess(file, fileList) {
        //   this.form.picUrl = file.url;
        //   console.log('success'+ this.form.picUrl)
        // },
        // handleRemove(file, fileList) {
        //   console.log(file, fileList);
        // },
        // handlePictureCardPreview(file) {
        //   this.form.picUrl = file.url;
        //   this.dialogVisible = true;
        // },

        handleDelete(index, row) {
          let query = {
            id: row.id
          };

          travelApi.travelNotesDel(query).then(res => {
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
          travelApi.travelNotesList(query).then(res => {
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
