<template>
  <div class="container">
    <Header></Header>
    <Aside></Aside>
    <router-view></router-view>
  </div>
</template>

<script>
const Header = () => import("@/components/home/header/index")
const Aside = () => import("@/components/home/aside/index")
export default {
  data () {
    return {

    }
  },
  components: {
    Header,
    Aside,
  },
  mounted() {

  },
  methods: {

  },
}

</script>
<style scoped lang='less'>

</style>