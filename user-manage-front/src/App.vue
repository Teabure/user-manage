<template>
  <h1 align="center">用户管理</h1>
  <TableList/>
</template>

<script>
import TableList from './components/TableList.vue';

export default {
  name: 'App',
  components: {
    TableList
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  margin-top: 60px;
}
</style>
