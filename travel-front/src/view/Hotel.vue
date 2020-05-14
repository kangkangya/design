<template>
  <div class="container">
    <div class="row" style="height:5px"></div>
    <div class="row">
      <div class="w-100">
        <Navigate selected_idx="2"></Navigate>
      </div>
    </div>

    <div class="row">
      <div class="w-100">
        <HotelList></HotelList>
      </div>
    </div>

    <div class="row" style="height: 90px"/>
  </div>
</template>

<script>
  import Navigate from '../components/navigate'
  import HotelList from '../components/hotel_list'

  export default {
    name: "Hotel",
    components: {
      Navigate,
      HotelList
    }
  }
</script>

<style scoped>

</style>
