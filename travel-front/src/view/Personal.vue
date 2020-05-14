<template>
    <div class="container">
      <div class="row" style="height:5px"></div>
      <div class="row">
        <div class="w-100"><Navigate></Navigate></div>
      </div>
      <div class="row">
        <div class="col-12">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="Ta的窝" name="first"><PersonalHome></PersonalHome></el-tab-pane>
            <el-tab-pane label="Ta的游记" name="second"><PersonalNotes></PersonalNotes></el-tab-pane>
            <el-tab-pane label="Ta的结伴" name="third"><PersonalGowith></PersonalGowith></el-tab-pane>
          </el-tabs>
        </div>
    </div>
    </div>
</template>

<script>
  import Navigate from '../components/navigate'
  import PersonalHome from '../components/personal_home'
  import PersonalNotes from '../components/personal_notes'
  import PersonalGowith from '../components/personal_gowith'

    export default {
        name: "Personal",
      components: {
        Navigate,
        PersonalHome,
        PersonalNotes,
        PersonalGowith
      },
      data() {
        return {
          activeName: 'first'
        };
      },
      methods: {
        handleClick(tab, event) {
        }
      }
    }
</script>

<style scoped>

</style>
