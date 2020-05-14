<template>
  <div class="box">
    <div class="head">
      <p class="title">{{tableData.title}}</p>
      <small class="text-muted">地址：{{tableData.address}}</small>
      <p class="introduction" contenteditable="true">{{tableData.summary}}</p>
    </div>
    <div class="img">
      <div contenteditable="true" class="img-list">
        <img :src="baseUrl + tableData.picUrl" alt="图片加载失败">
      </div>
    </div>
    <div class="scenic-spot-dir-list">
      <!--=========基本信息=====-->
      <div class="row basic-information">
        <div class="col-2">基本信息</div>
        <div class="col-10">
          <div class="row row-cols-4">
            <div class="col">入住时间:<h4>{{tableData.startTime}}</h4>之后</div>
            <div class="col">离店时间:<h4>{{tableData.endTime}}</h4>之前</div>
            <div class="col">建成于:<h4>{{tableData.buildTime}}</h4>年</div>
            <div class="col">酒店规模:<h4>{{tableData.roomNum}}</h4>间客房</div>
          </div>
        </div>
      </div>
      <div class="message-list">
        <!--=============主要设施============-->
        <div class="row infrastructure">
          <div class="col-2">主要设施</div>
          <div class="col-10 border-dashed">
            <div class="row row-cols-5" >
              <p v-for="mainFacility in tableData.checkedMainFacilitys">
                <i class="el-icon-check">
                  {{mainFacility}}
                </i>
              </p>
            </div>
          </div>
        </div>
        <!--=============酒店服务============-->
        <div class="row hotel-service">
          <div class="col-2">酒店服务</div>
          <div class="col-10 border-dashed">
            <div class="row row-cols-5" >
              <p  v-for="hotelService in tableData.checkedHotelServices">
                <i class="el-icon-check"></i>
                {{hotelService}}
              </p>

            </div>
          </div>
        </div>
        <!--==============酒店设施===========-->
        <div class="row hotel-facility ">
          <div class="col-2">酒店设施</div>
          <div class="col-10 border-dashed">
            <div class="row row-cols-5" >
              <p v-for="hotelFacility in tableData.checkedHotelFacilitys">
                <i class="el-icon-check"></i>
                {{hotelFacility}}
              </p>

            </div>
          </div>
        </div>
        <!--================房间设施===========-->
        <div class="row room-facility">
          <div class="col-2">房间设施</div>
          <div class="col-10 border-dashed">
            <div class="row row-cols-5" >
              <p v-for="roomFacility in tableData.checkedRoomFacilitys">
                <i class="el-icon-check"></i>
                {{roomFacility}}
              </p>

            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <el-table
          border
          :data="tableData1"
          style="width: 100%"
          :row-style="{height:'250px'}"
          :cell-style="{padding:'0px'}"
        >
          <el-table-column
            prop="imgUrl"
            label="房型"
            width="250">
            <template slot-scope="scope">
              <span>海上花园</span>
              <img :src="baseUrl + 'room-1.jpg'"  alt="" style="width: 200px; height: 200px">
            </template>
          </el-table-column>
          <el-table-column
            prop="number"
            label="人数上限"
            width="170">
          </el-table-column>
          <el-table-column
            prop="policy"
            width="250"
            label="相关政策">
          </el-table-column>
          <el-table-column
            prop="price"
            width="200"
            label="价格">
<!--            <template slot-scope="scope">-->
<!--              <span>￥</span>-->
<!--            </template>-->
          </el-table-column>
          <el-table-column
            prop="rooms"
            width="240"
            label="房间数量">
            <template slot-scope="scope">
              <el-input-number v-model="num" @change="handleChange" :min="0" :max="100" label="描述文字"></el-input-number>
            </template>
          </el-table-column>
          <el-table-column
            prop="rooms"
            label="">
            <template slot-scope="scope">
              <el-button type="primary"@click="handleConfirm">确定</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="row">
      <div class="col-1"><el-button @click="clickEdit" type="primary" style="float: right;">编辑</el-button></div>
      <el-dialog title="编辑酒店" :visible.sync="dialogFormEditVisible">
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
            <el-checkbox-group v-model="form.checkedMainFacilitys" @change="value => handleCheckedChange(value,1)">
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
          <el-button type="primary" @click="handleEdit('form')">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <comments :ref-id="ref-id" :type="type"></comments>
    <Pager :pageParam="pageParam"></Pager>
  </div>
</template>

<script>
  import comments from '../../components/Comments/index.vue'
  import Pager from '../../../../travel-front/src/components/pager'
  import save from '../../components/Save/index'
  import travelApi from "../../api/travel";
  import upload from '../../components/Img/index'



  export default {
    name: "detail",
    components: {
      comments,
      Pager,
      save,
      upload
    },
    data() {
      return {
        baseUrl: 'http://localhost:8888/img/hotel/room',
        refId: this.$route.query.id,
        type: 'hotel',
        num: 1,
        pageParam: {
          total: 150,
          pageSize: 15,
          pageNum: 1,
          totalPage: 0
        },
        tableData1: [{
          type: '标间',
          imgUrl: '房间图片',
          number: '2人',
          policy: '含早餐，在线付，不可取消和变更',
          price: '234',
          rooms: '9',
        },{
          type: '大床房',
          imgUrl: '房间图片',
          number: '2人',
          policy: '含早餐，在线付，不可取消和变更',
          price: '133',
          rooms: '9',
        },{
          type: '高级房',
          imgUrl: '房间图片',
          number: '2人',
          policy: '含早餐，在线付，不可取消和变更',
          price: '133',
          rooms: '9',
        }],
        tableData: [],
        form: {
          id: '',
          regionCode: '',
          title: '',
          summary: '',
          address: '',
          price: '',
          picUrl:'',
          startTime: new Date(),
          endTime: new Date(),
          buildTime: '',
          roomNum: '',
          checkedMainFacilitys: [],
          checkedHotelServices: [],
          checkedHotelFacilitys: [],
          checkedRoomFacilitys: [],
        },
        formLabelWidth: '120px',
        dialogFormEditVisible: false,
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
          imgCode: 'hotel_dtl'
        },
        baseUrl: 'http://localhost:8888/img/hotel/',

      }
    },
    methods: {
      handleChange(value) {
        console.log(value);
      },
      handleConfirm() {

      },
      getList(){
        let param = {
          id: this.$route.query.id,
        };

        travelApi.findHotelById(param).then(res => {
          this.tableData = res.data.body;
        });
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


      //处理复选框全选变化
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
      //处理复选框变化
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

      clickEdit(){
        this.mainFacilitys = this.tableData.checkedMainFacilitys;
        this.hotelServices = this.tableData.checkedHotelServices;
        this.hotelFacilitys = this.tableData.checkedHotelFacilitys;
        this.roomFacilitys = this.tableData.checkedRoomFacilitys;
        this.form = this.tableData;
        this.dialogFormEditVisible = true;
      },

      //提交添加
      handleEdit(formName){
        this.form.picUrl = this.$refs.uploadImg.imgUrl;

        this.$refs[formName].validate((valid) => {

          //验证通过  向服务器提交请求
          if (valid) {
            travelApi.hotelEdit(this.form).then(res=>{
              if(res.code === 200) {
                this.dialogFormEditVisible = false;
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
  .title {
    font-size: 30px;
    text-shadow: 2px 2px 2px #ccc;
    color: orange;
    display: block;
  }
  .head {
    text-align: center;
  }
  .introduction {
    padding-top: 15px;
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
  .basic-information {
    padding: 70px 0 30px 0;
    border-bottom: 1px solid #ccc;
  }
  .basic-information h4 {
    display: inline;
    padding: 0 4px;
  }
  .infrastructure,.hotel-service,.hotel-facility,.room-facility,.hotel-strategy {
    padding-top: 40px;
  }
  .infrastructure p,.hotel-service p,.hotel-facility p,.room-facility p,.hotel-strategy pre {
    font-size: 14px;
    color: #666;
  }
  .border-dashed {
    border-bottom: 1px dashed #ccc;
  }
</style>
