<template>
  <div class="container">
    <div class="row" style="height:5px"></div>
    <div class="row">
      <div class="w-100"><Navigate selected_idx="0"></Navigate></div>
    </div>

    <div class="row" style="height:40px"></div>
    <div class="row">
      <div class="w-100"><Banner></Banner></div>
    </div>

    <div class="row" style="height:40px"></div>
    <div class="row">
      <div class="w-100"><ScenicSpoit></ScenicSpoit></div>
    </div>

    <div class="row" style="height:40px"></div>
    <div class="row">
      <div class="w-100"><Hotel></Hotel></div>
    </div>

    <div class="row" style="height: 100px"/>
  </div>
</template>

<script>
  import Navigate from '../components/navigate'
  import Banner from '../components/banner'
  import ScenicSpoit from '../components/scenic_spoit'
  import Hotel from '../components/hotel'

  export default {
    name: 'Index',
    components: {
      Navigate, Banner, ScenicSpoit, Hotel
    },
    data () {
      return {
        scenicList: [],
        hotelList: []
      }
    },
    methods: {}
  }
</script>
