<script setup>
import { RouterView } from 'vue-router';
import FooterComponent from './components/footer/FooterComponent.vue';
import EditEvent from './views/EditEvent.vue';
</script>

<template>
   <RouterView/>  
</template>

<style lang="scss" scoped>
main {
  width: 100%;
  .wrapper {
    width: 50%;
  }
}
</style>
