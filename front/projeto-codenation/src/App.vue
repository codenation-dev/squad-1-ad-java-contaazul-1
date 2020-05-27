<template>
  <div class="corpo">
    <link
      href="https://fonts.googleapis.com/css2?family=Cabin:wght@400;500;700&display=swap"
      rel="stylesheet"
    />
    <transition name="pagina">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
</script>

<style>
.corpo {
  font-family: "Cabin", Helvetica, sans-serif;
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
