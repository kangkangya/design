<template>
    <div>
      <el-table
        :data="datas.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
        <el-table-column v-for="(item, idx) in titles"
          :label="titles[idx]"
          width="200"
          :prop="data[idx]">
        </el-table-column>


        <el-table-column
          :label="titles[1]"
          width="200"
          prop="username">
        </el-table-column>
        <el-table-column
            :label="titles[2]"
            width="230"
            prop="password">
        </el-table-column>
        <el-table-column
          width="230">
          <template slot="header" slot-scope="scope">
            <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
              Add
            </el-button>
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
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
</template>

<script>
export default {
  name: 'User',
  data() {
    return {
     //  titles: [
     // 'Date', 'Name',  'Address'
     //  ],
     //
     //  tableData: [{
     //    date: '2016-05-02',
     //    name: '王小虎',
     //    address: '上海市普陀区金沙江路 1518 弄'
     //  }, {
     //    date: '2016-05-04',
     //    name: '王小虎',
     //    address: '上海市普陀区金沙江路 1517 弄'
     //  }, {
     //    date: '2016-05-01',
     //    name: '王小虎',
     //    address: '上海市普陀区金沙江路 1519 弄'
     //  }, {
     //    date: '2016-05-03',
     //    name: '王小虎',
     //    address: '上海市普陀区金沙江路 1516 弄'
     //  }],
      search: ''
    }
  },
  props: {
    titles: Array,
    datas: Array
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    }
  }
}
</script>

<style scoped>

</style>
