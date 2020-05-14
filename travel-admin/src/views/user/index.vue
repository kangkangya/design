<template>
  <div>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        label="id"
        width="300"
        prop="id">
      </el-table-column>
      <el-table-column
        label="账户"
        width="150"
        prop="username">
      </el-table-column>
      <el-table-column
        label="昵称"
        width="150"
        prop="nickName">
      </el-table-column>
      <el-table-column
        label="密码"
        width="150"
        prop="password">
      </el-table-column>
      <el-table-column>
        <template slot="header" slot-scope="scope">
          <el-button
            class="filter-item"
            style="margin-left: 10px;"
            type="primary"
            icon="el-icon-edit"
            @click="dialogFormAddVisible= true">
            Add
          </el-button>
          <el-dialog title="添加管理员" :visible.sync="dialogFormAddVisible">
            <el-form :model="form">
              <el-form-item label="账户" :label-width="formLabelWidth">
                <el-input v-model="form.username" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="昵称" :label-width="formLabelWidth">
                <el-input v-model="form.nickName" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="密码" :label-width="formLabelWidth">
                <el-input v-model="form.password" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="确认密码" :label-width="formLabelWidth">
                <el-input v-model="form.confirmPassword" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormAddVisible = false">取 消</el-button>
              <el-button type="primary" @click="handleSave()">确 定</el-button>
            </div>
          </el-dialog>
        </template>
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="clickEdit(scope.$index, scope.row)">Edit
          </el-button>
          <el-dialog title="编辑用户" :visible.sync="dialogFormVisible">
            <el-form :model="form">
              <el-form-item label="用户名" :label-width="formLabelWidth">
                <el-input v-model="form.username" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="密码" :label-width="formLabelWidth">
                <el-input v-model="form.password" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="handleEdit(scope.$index, scope.row)">确 定</el-button>
            </div>
          </el-dialog>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">Delete
          </el-button>
        </template>
      </el-table-column>
      <el-table-column
        align="right">
        <template slot="header" slot-scope="scope">
          <el-input @change="getList()" v-model="search" size="mini" placeholder="输入关键字搜索"/>
        </template>
      </el-table-column>
    </el-table>
    <Pager :pageParam="pageParam" :page-num-change="pageNumChange"></Pager>
  </div>
</template>

<script>
  import Pager from '../../../../travel-front/src/components/pager'
  import travelApi from "../../api/travel";

  export default {
    name: 'User',
    components: {
      Pager
    },
    data() {
      return {
        pageParam: {
          total: 0,
          pageSize: 15,
          pageNum: 1,
          totalPage: 0
        },
        tableData: [],
        search: '',
        dialogFormVisible: false,
        dialogFormAddVisible: false,
        form: {
          id: '',
          username: '',
          password: '',
          nickName: '',
          confirmPassword: ''
        },
        formLabelWidth: '120px'
      }
    },
    methods: {
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
          message: m,
        })
      },

      clickEdit(index, row) {
        this.form = row;
        this.dialogFormVisible = true;
      },

      handleSave() {
        if (this.form.username === undefined
            || this.form.username === ''
            || this.form.username === null) {
          this.messageError('用户名不能为空!');
          return false;
        }

        if (this.form.password === undefined
          || this.form.password === ''
          || this.form.password === null) {
          this.messageError('密码不能为空!');
          return false;
        }

        if (this.form.password !== this.form.confirmPassword) {
          this.messageError('两次输入的密码不相同!');
          return false;
        }


        travelApi.userSave(this.form).then(res => {
          if (res.code === 200) {
            this.dialogFormAddVisible = false

            this.messageSuccess('添加成功！');

            this.getList();

            this.form = {};

            return true;
          } else {
            this.messageError(res.msg);
            return false;
          }
        })
      },

      handleEdit(index, row) {
        if (this.form.username === undefined
          || this.form.username === ''
          || this.form.username === null) {
          this.messageError('用户名不能为空!');
          return false;
        }

        if (this.form.password === undefined
          || this.form.password === ''
          || this.form.password === null) {
          this.messageError('密码不能为空!');
          return false;
        }

        this.form.id = row.id;

        travelApi.userEdit(this.form).then(res => {
          if (res.code === 200) {
            this.dialogFormVisible = false;
            this.messageSuccess('修改成功！');
            this.getList();

            this.form = {};

            return true;
          } else {
            this.messageError(res.msg);
            return false;
          }
        })
      },

      handleDelete(index, row) {
        let query = {
          id: row.id
        };

        travelApi.userDel(query).then(res => {
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
        travelApi.userList(query).then(res => {
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
  nav {
    position: absolute;
    bottom: 10px;
    left: 20px;
  }
</style>
