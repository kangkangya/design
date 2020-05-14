<template>
    <div class="container">
      <div class="row">
        <div class="col-3">
          <p class="hotel-name">酒店名称酒店名称</p>
        </div>
        <div class="col-9">
          <el-table
            :data="tableData"
            border
            style="width: 100%">
            <el-table-column
              prop="date"
              label="入住时间"
              width="220">
            </el-table-column>
            <el-table-column
              prop="name"
              label="离店时间"
              width="220">
            </el-table-column>
            <el-table-column
              prop="address"
              label="成人数">
            </el-table-column>
            <el-table-column
              prop="address"
              label="儿童数">
            </el-table-column>
            <el-table-column
              prop="address"
              label="房间数">
            </el-table-column>
          </el-table>
        </div>
      </div>
      <div class="row message-list">
        <div class="col-6">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="姓名">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="手机">
              <el-input v-model="form.telephone"></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱">
              <el-input v-model="form.email"></el-input>
            </el-form-item>
            <el-form-item label="备注">
              <el-input type="textarea" v-model="form.remarks"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">提交订单</el-button>
              <el-button>取消</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="col-1"></div>
        <div class="col-5">
          <div class="row message-list-detail">
            <div class="col-12 title">预定费用明细</div>
            <div class="col-12 room">
              <div class="row ">
                <div class="col-8">2间房*1晚</div>
                <div class="col-4">￥<small>400</small></div>
              </div>
            </div>
            <div class="col-12 price">
              <div class="row">
                <div class="col-8">总价</div>
                <div class="col-4">￥<small>400</small></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
    export default {
        name: "Order",
      data() {
          return {
            form: {
              name: '',
              telephone: '',
              email: '',
              remarks: ''
            }
          }
      }
    }
</script>

<style scoped>
.container {
  padding-top: 20px;
}
  .hotel-name {
    color: orange;
    font-size: 25px;
  }
  .message-list {
    padding-top: 40px;
    padding-right: 15px;
  }
  .message-list-detail {
    box-shadow: 0px 2px 2px #ccc;
  }
  .message-list-detail .room,.price,.title {
    height: 60px;
    line-height: 60px;
  }
  .title {
    font-size: 20px;
    color: orange;
  }
  .price {
    font-size: 18px;
  }
</style>
