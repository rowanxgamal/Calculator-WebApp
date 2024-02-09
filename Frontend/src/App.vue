<template>
  <Calculator_comp/>
</template>

<script>
import Calculator_comp from './components/Calculator_comp.vue'

export default {
  name: 'App',
  components: {
    Calculator_comp
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
