<template>
  <div class="row save">
    <div class="col-1"><el-button @click="clickEdit()" type="primary" style="float: right;">编辑</el-button></div>
  </div>
</template>

<script>
    export default {
        name: "index",
      methods: {
          clickEdit() {
            this.dialogFormEditVisible = true;
          },
        handleEdit(index, row) {
          console.log(index, row);
        }
      }
    }
</script>

<style scoped>
  .save {
    width: 100%;
    border-bottom: 1px solid #ccc;
    padding-bottom: 10px;
  }
</style>
