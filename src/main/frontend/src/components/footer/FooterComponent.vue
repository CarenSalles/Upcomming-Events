<script setup>
import { useRouter} from "vue-router";

</script>

<template>

        <footer class="footer">
    <div class="footer__container-social-media">
        <p class="footer__container-social-media__copyright">©Copyright 2023</p>
    </div>
</footer>

</template>

<style lang="scss" scoped>
.footer__container-social-media{
  background-image: url("../../assets/img/Rectangle-footer.png");
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