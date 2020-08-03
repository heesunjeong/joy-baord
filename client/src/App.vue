<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>
      |
      <router-link to="/blah">BlahBlah</router-link>
    </div>
    <router-view/>
  </div>
</template>

<script>
  export default {
    created() {
      // 뷰가 생성되고 데이터가 이미 감시 되고 있을 때 데이터를 가져온다.
      this.fetchData()
    },
    watch: {
      // 라우트가 변경되면 메소드를 다시 호출됩니다.
      '$route': 'fetchData'
    },
    methods: {
      fetchData() {
        //console.log(this.$route);
      }
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }

  #nav {
    padding: 30px;
  }

  #nav a {
    font-weight: bold;
    color: #2c3e50;
  }

  #nav a.router-link-exact-active {
    color: #42b983;
  }
  ul {
    list-style-type: none;
    padding-inline-start: 0px;
  }
</style>
