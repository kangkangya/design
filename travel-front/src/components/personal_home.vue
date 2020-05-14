<template>
    <div style="padding-left: 20px;">
      <div class="row personal-message" >
        <div class="col-2">
          <el-row class="demo-avatar demo-basic">
            <el-col>
              <div class="demo-basic--circle" >
                <div class="block"><el-avatar :size="100" :src="circleUrl"></el-avatar></div>
              </div>
            </el-col>
          </el-row>
        </div>
        <div class="col-10">
          <p class="name">昵称：{{user.nickName}}</p>
          <p>性别：{{user.gender}}</p>
          <p class="profile">个人简介: {{user.summary}}</p>
        </div>
      </div>
      <div class="row ">
        <div class="col-6 ">
          <div class="row notes"><p class="el-icon-edit"></p><p>我的游记</p></div>
          <div class="row color" v-for="travelNote in travelNoteList">
            <ul>
              <li>{{travelNote.title}}</li>
            </ul>
          </div>
        </div>
        <div class="col-6 ">
          <div class="row gowith">
            <p class="el-icon-user"></p><p>我的结伴</p>
          </div>
          <div class="row color" v-for="goWith in goWithList">
            <ul>
              <li>{{goWith.title}}</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
</template>


<script>
  import travelNoteApi from "../api/travel-note";
  import goWithApi from "../api/goWith";
    export default {
        name: "personal_home",
      data() {
          return {
            circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
            user: {
              id: '',
              nickName: '',
              gender: '',
              summary: '',
              travelNotes: [],
              goWith: []
            },
            travelNoteList: [],
            goWithList: [],
          }
      },
      mounted() {
          this.user = this.$store.getters.getUserInfo;
          // console.log(this.$store.getters.getUserInfo);

          if (this.user.gender === "1"){
            this.user.gender = '男';
          }else if (this.user.gender === "2") {
            this.user.gender = '女'
          }

          if (this.user.summary == null || this.user.summary == ""){
            this.user.summary = "Ta很懒，没有简介";
          }

          var param = {
            refId: this.user.id
          }

          travelNoteApi.findByUserId(param).then(res => {
            console.log(res)
            this.travelNoteList = res.data.body;
          })

          goWithApi.findByUserId(param).then(res => {
            console.log(res);
            this.goWithList = res.data.body;

          })

      }
    }
</script>

<style scoped>
.personal-message {
  padding: 20px 0 20px 50px;
  background: cornsilk;
}
  .name {
    color: orange;
    font-size: 18px;
  }
  .notes,.gowith {
    color: orange;
    font-size: 27px;
  }
  .color {
    background-color: cornsilk;
  }
</style>
