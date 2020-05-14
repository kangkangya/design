<template>
  <div>
    <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
      <el-table-column
        label="结伴标题"
        width="220"
        prop="title">
      </el-table-column>
      <el-table-column
        label="结伴目的地"
        width="200"
        prop="destination">
      </el-table-column>
      <el-table-column
        label="关注人数"
        width="100"
        prop="attentionNum">
      </el-table-column>
      <el-table-column
        label="开始时间"
        width="100"
        prop="beginDate">
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
          <router-link class="" :to="{path:'/go-with-detail',query:{id:scope.row.id}}">
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
    name: "go-with",
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
        handleDelete(index, row) {
          let query = {
            id: row.id
          };

          travelApi.goWithDel(query).then(res => {
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
          travelApi.goWithList(query).then(res => {
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
