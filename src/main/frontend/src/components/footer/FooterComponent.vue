<script setup>
import { useRouter} from "vue-router";

</script>

<template>

        <footer class="footer">
    <div class="footer__container-social-media">
        <p class="footer__container-social-media__copyright">Upcomming Events ©Copyright 2023</p>
    </div>
</footer>

</template>

<style lang="scss" scoped>
.footer__container-social-media{
  background-color: rgb(23, 23, 62);
  width: 100%;
  height: 10vh;
  display: flex;
  justify-content: center;
  align-items: center;

  &__copyright{
    color: white;
    text-align:center;
    font-size: 3.5vh;

  }
}
</style>