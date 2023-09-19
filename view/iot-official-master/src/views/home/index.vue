<script setup>
  
  import HomeHeader from '@/components/home/HomeHeader.vue'
  import SideBar from '@/components/home/SideBar.vue'

</script>
<template lang="">
  <div style="padding-top: 50px; ">
    <home-header></home-header>
    <a-layout style="min-height: calc(100vh - 50px)">
      <side-bar></side-bar>
      <a-layout style="flex-direction: column;">
        <router-view></router-view>
      </a-layout>
    </a-layout>
  </div>
</template>

<style lang="less" scoped>

</style>
