<template>
  <div class="container">
    <div class="row" style="height:5px"></div>
    <div class="row">
      <div class="w-100"><Navigate selected_idx="4"></Navigate></div>
    </div>

    <div class="row">
      <div class="w-100">
        <TravelNoteList></TravelNoteList>
      </div>
    </div>

    <div class="row" style="height: 60px"/>
  </div>
</template>

<script>
  import Navigate from '../components/navigate'
  import TravelNoteList from '../components/travel_note_list'

    export default {
        name: "Travel-Note",
        components: {
          Navigate,
          TravelNoteList
        }
    }
</script>

<style scoped>

</style>
