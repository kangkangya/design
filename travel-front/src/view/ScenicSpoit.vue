<template>
  <div class="container">
    <div class="row" style="height:5px"></div>
    <div class="row">
      <div class="w-100"><Navigate selected_idx="1"></Navigate></div>
    </div>

    <div class="row">
      <div class="w-100">
        <ScenicList></ScenicList>
      </div>
    </div>

    <div class="row" style="height: 90px"/>
  </div>
</template>

<script>
  import Navigate from '../components/navigate'
  import ScenicList from '../components/scenic_list'

  export default {
    name: 'ScenicSpoit',
    components: {
      ScenicList,
      Navigate
    }
  }
</script>
