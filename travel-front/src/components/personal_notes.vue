<template>
    <div class="container">
      <div class="row">
        <div class="col-4 my-notes">
          <div class="row" style="color: orange">我的游记</div>
          <div class="row" v-for="travleNote in travelNoteList">
            <ul>
              <li>{{travleNote.title}}</li>
            </ul>
          </div>
        </div>
        <div class="col-8 notes-content">
          <div class="row">
            <div class="col-8"><p>{{travelNoteList.length}}<small class="text-muted">游记</small></p></div>
            <div class="col-4">
              <router-link class="nav-link" to="/write-notes">
                <el-button type="primary" icon="el-icon-edit">写游记</el-button>
              </router-link>
            </div>
          </div>

          <div class="row ">
            <div class="col-12">
              <div class="row title">
                <p class="">{{travelNoteList[0].title}}</p>
              </div>
              <div class="row">
                <p>{{travelNoteList[0].summary}}</p>
              </div>
            </div>

          </div>
        </div>

      </div>
    </div>
</template>

<script>
    import travelNoteApi from "../api/travel-note";

    export default {
        name: "personal_notes",
      data(){
          return {
            travelNoteList: [],
          }
      },
      mounted() {
        var param = {
          refId: this.user.id,
        };

        travelNoteApi.findByUserId(param).then(res => {
          console.log(res)
          this.travelNoteList = res.data.body;
        })
      }
    }
</script>

<style scoped>
  .row {
    padding-left: 20px;
  }
.my-notes {
  width: 100%;
  box-shadow: 3px 3px 3px #ccc;
}
  .notes-content .title {
    font-size: 22px;
    color: orange;
    border-bottom: 1px solid #ccc;
    width: 100%;
  }
</style>
