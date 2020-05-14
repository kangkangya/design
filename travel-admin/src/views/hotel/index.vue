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
        prop="imgUrl">
        <template slot-scope="scope">
          <img :src=" baseUrl + scope.row.picUrl" alt="" style="width: 200px; height: 200px">
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
        prop="like">
      </el-table-column>
      <el-table-column
        label="评论数"
        width="100"
        prop="comments">
      </el-table-column>
      <el-table-column
        label="价格"
        width="100"
        prop="price">
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
            @click="clickAdd">
            Add
          </el-button>
          <el-dialog title="添加酒店" :visible.sync="dialogFormAddVisible">
            <el-form :model="form" ref="form" >
              <el-form-item label="图片" :label-width="formLabelWidth">
                <upload ref="uploadImg" :uploadPram="uploadParam" ></upload>

                <el-dialog :visible.sync="dialogVisible">
                  <img width="100%" :src="form.picUrl" alt="">
                </el-dialog>
              </el-form-item>
              <el-form-item label="酒店标题" prop="title" :label-width="formLabelWidth" :rules="validateRule">
                <el-input v-model="form.title" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="酒店简介" prop="summary" :label-width="formLabelWidth" :rules="validateRule">
                <el-input type="textarea" v-model="form.summary" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="地址" prop="address" :label-width="formLabelWidth" :rules="validateRule">
                  <el-input v-model="form.address" autocomplete="off">
                    <el-select style="width: 100px" v-model="form.regionCode" slot="prepend" placeholder="酒店地区">
                      <el-option label="东区" value="10000"></el-option>
                      <el-option label="西区" value="20000"></el-option>
                      <el-option label="仁和区" value="00000"></el-option>
                      <el-option label="米易县" value="30000"></el-option>
                      <el-option label="盐边县" value="40000"></el-option>
                    </el-select>
                </el-input>
              </el-form-item>
              <el-form-item label="价格" prop="price" :label-width="formLabelWidth" :rules="numberRule">
                <el-input v-model.number="form.price" autocomplete="off"></el-input>
              </el-form-item>
              <div class="row row-cols-2">
                <el-form-item label="入住时间" prop="startTime" label-width="100px" :rules="validateRule">
                  <el-time-picker v-model="form.startTime">
                  </el-time-picker>
                </el-form-item>
                <el-form-item label="离店时间" prop="endTime" label-width="100px" :rules="validateRule">
                  <el-time-picker v-model="form.endTime">
                  </el-time-picker>
                </el-form-item>
              </div>
              <div class="row row-cols-2">
                <el-form-item label="修建时间" prop="buildTime" label-width="100px" :rules="validateRule">
                  <el-date-picker v-model="form.buildTime" type="year" >
                  </el-date-picker>
                </el-form-item>
                <el-form-item label="酒店规模" prop="roomNum" label-width="100px" :rules="numberRule">
                  <el-input-number v-model="form.roomNum" controls-position="right" :min="1">
                  </el-input-number>
                </el-form-item>
              </div>

              <el-form-item label="主要设施" prop="mainFacility" :label-width="formLabelWidth" >
                <el-checkbox :indeterminate="isIndeterminate.mainFacility" v-model="checkAll.mainFacility"
                             @change="checked => handleCheckAllChange(checked,1)">全选</el-checkbox>
                <el-checkbox-group v-model="form.checkedMainFacilitys"
                                   @change="value => handleCheckedChange(value,1)">
                    <el-checkbox v-for="mainFacility in mainFacilitys"
                                 :label="mainFacility" :key="mainFacility">{{mainFacility}}</el-checkbox>
                </el-checkbox-group>
              </el-form-item>

              <el-form-item label="酒店服务" prop="hotelService" :label-width="formLabelWidth" >
                <el-checkbox :indeterminate="isIndeterminate.hotelService" v-model="checkAll.hotelService"
                             @change="checked => handleCheckAllChange(checked,2)">全选</el-checkbox>
                <el-checkbox-group v-model="form.checkedHotelServices" @change="value => handleCheckedChange(value,2)">
                  <el-checkbox v-for="hotelService in hotelServices"
                               :label="hotelService" :key="hotelService">{{hotelService}}</el-checkbox>
                </el-checkbox-group>
              </el-form-item>

              <el-form-item label="酒店设施" prop="hotelFacilily" :label-width="formLabelWidth" >
                <el-checkbox :indeterminate="isIndeterminate.hotelFacility" v-model="checkAll.hotelFacility"
                             @change="checked => handleCheckAllChange(checked,3)">全选</el-checkbox>
                <el-checkbox-group v-model="form.checkedHotelFacilitys" @change="value => handleCheckedChange(value,3)">
                  <el-checkbox v-for="hotelFacility in hotelFacilitys"
                               :label="hotelFacility" :key="hotelFacility">{{hotelFacility}}</el-checkbox>
                </el-checkbox-group>
              </el-form-item>

              <el-form-item label="房间设施" prop="roomFacility" :label-width="formLabelWidth" >
                <el-checkbox :indeterminate="isIndeterminate.roomFacility" v-model="checkAll.roomFacility"
                             @change="checked => handleCheckAllChange(checked,4)">全选</el-checkbox>
                <el-checkbox-group v-model="form.checkedRoomFacilitys" @change="value => handleCheckedChange(value,4)">
                  <el-checkbox v-for="roomFacility in roomFacilitys"
                               :label="roomFacility" :key="roomFacility">{{roomFacility}}</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormAddVisible = false">取 消</el-button>
              <el-button type="primary" @click="handleSave('form')">确 定</el-button>
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
          <router-link :to="{path: '/hotel-detail',query: {id:scope.row.id}}">
            <el-button size="mini" type="primary">查看详情</el-button>
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
  import conApi from "../../api/const";
  import upload from '../../components/Img/index'


  export default {
    name: "hotel",
    components: {
      Pager,
      upload
    },
    data() {
      return {
        region: '',
        pageParam: {
          total: 150,
          pageSize: 15,
          pageNum: 1,
          totalPage: 0
        },
        tableData: [],
        form: {
          id: '',
          regionCode: '',
          title: '',
          summary: '',
          address: '',
          price: '',
          picUrl:'',
          startTime: new Date(2020,1,1,14,0),
          endTime: new Date(2020,1,2,12,0),
          buildTime: '',
          roomNum: '',
          checkedMainFacilitys: [],
          checkedHotelServices: [],
          checkedHotelFacilitys: [],
          checkedRoomFacilitys: [],

        },
        search: '',
        dialogFormVisible: false,
        dialogFormAddVisible: false,
        formLabelWidth: '120px',
        imgUrl: '/img/im.png',
        dialogImageUrl: '',
        dialogVisible: false,
        mainFacilitys: [],
        hotelServices: [],
        hotelFacilitys: [],
        roomFacilitys: [],
        isIndeterminate: {
          mainFacility:true,
          hotelService:true,
          hotelFacility:true,
          roomFacility:true
        },
        checkAll: {
          mainFacility:false,
          hotelService:false,
          hotelFacility:false,
          roomFacility:false
        },
        validateRule: [
          {required: true,message: '该项为必填项'}
        ],
        numberRule: [
          {required: true, message: '该项为必填项'},
          {type: 'number', message: '该项必须为数字'}
        ],
        uploadParam: {
          refId: 'hotel',
          imgCode: 'hotel_list'
        },
        baseUrl: 'http://localhost:8888/img/hotel/',
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
        clickAdd(){
          this.dialogFormAddVisible = true;
          this.mainFacilitys = conApi.getMainFacility();
          this.hotelServices = conApi.getHotelService();
          this.hotelFacilitys = conApi.getHotelFacility();
          this.roomFacilitys = conApi.getRoomFacility();
        },

        handleCheckAllChange(checked,type) {
          if(type === 1){
            this.form.checkedMainFacilitys = checked ? this.mainFacilitys : [];
            this.isIndeterminate.mainFacility = false;
          }else if (type === 2){
            this.form.checkedHotelServices = checked ? this.hotelServices : [];
            this.isIndeterminate.hotelService = false;
          }else if (type === 3){
            this.form.checkedHotelFacilitys = checked ? this.hotelFacilitys : [];
            this.isIndeterminate.hotelFacility = false;
          }else{
            this.form.checkedRoomFacilitys = checked ? this.roomFacilitys : [];
            this.isIndeterminate.roomFacility = false;
          }
        },
        handleCheckedChange(value,type) {
          let checkedCount = value.length;
          if(type === 1){
            this.checkAll.mainFacility = checkedCount === this.mainFacilitys.length;
            this.isIndeterminate.mainFacility = checkedCount > 0 && checkedCount < this.mainFacilitys.length;
          }else if(type === 2){
            this.checkAll.hotelService = checkedCount === this.hotelServices.length;
            this.isIndeterminate.hotelService = checkedCount > 0 && checkedCount < this.hotelServices.length;
          }else if (type === 3){
            this.checkAll.hotelFacility = checkedCount === this.hotelFacilitys.length;
            this.isIndeterminate.hotelFacility = checkedCount > 0 && checkedCount < this.hotelFacilitys.length;
          }else{
            this.checkAll.roomFacility = checkedCount === this.roomFacilitys.length;
            this.isIndeterminate.roomFacility = checkedCount > 0 && checkedCount < this.roomFacilitys.length;
          }
        },

          //提交添加
        handleSave(formName){
          this.form.picUrl = this.$refs.uploadImg.imgUrl;

          this.$refs[formName].validate((valid) => {

            //验证通过  向服务器提交请求
            if (valid) {
              travelApi.hotelSave(this.form).then(res=>{
                if(res.code === 200) {
                  this.dialogFormAddVisible = false;
                  this.messageSuccess("添加成功！");
                  this.resetForm(formName);
                  this.getList();
                  return true;
                }else {//响应代码不是200
                  this.messageError(res.msg);
                  return false;
                }
              });
            } else {//验证未通过
              this.messageError('表单填写错误！')
              return false;
            }
          });
        },
        //重置表单数据为初始化数据
        resetForm(formName)
        {
          if (this.$refs[formName]!==undefined) {
            this.$refs[formName].resetFields();
          }
        },

        handleDelete(index, row) {
          let query = {
            id: row.id
          };

          travelApi.hotelDel(query).then(res => {
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
          travelApi.hotelList(query).then(res => {
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
