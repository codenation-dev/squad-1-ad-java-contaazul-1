<template>
  <div class="corpo">
    <transition name="pagina">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
export default {};
</script>

<style>
.corpo {
  font-family: Helvetica, sans-serif;
  width: 96%;
  margin: 0 auto;
}
.pagina-enter-active,
.pagina-leave-active {
  transition: opacity 0.3s;
}
.pagina-enter,
.pagina-leave-active {
  opacity: 0;
}
</style>
