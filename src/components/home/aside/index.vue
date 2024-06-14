<template>
  <div>
    <el-menu
      class="el-menu-demo"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
    >
      <el-submenu index="1" style="margin-left: -145px;">
        <template slot="title"><User /><span>个人中心</span></template>
        <el-menu-item index="1-1" @click="menuUserHandler('updateinfo')">修改个人信息</el-menu-item>
        <el-menu-item index="1-2" @click="menuUserHandler('updatepassword')">修改密码</el-menu-item>
        <el-menu-item index="1-3" @click="menuUserHandler('getinfo')">展示个人信息</el-menu-item>
      </el-submenu>
      <el-menu-item index="2" @click="menuHandler('car')"><AllApplication /><span>车辆信息</span></el-menu-item>
      <el-menu-item index="3" @click="menuHandler('rental')"><Rss /><span>租赁订单列表</span></el-menu-item>
      <el-menu-item index="4" @click="menuHandler('carBack')"><Time /><span>还车记录</span></el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import { User, AllApplication, Rss, Time } from "@icon-park/vue";
import { mapState } from "vuex";
export default {
  data() {
    return {};
  },
  components: { User, AllApplication, Rss, Time },
  mounted() {},
  methods: {
    menuUserHandler(name) {
      if (name === 'updatepassword') {
        this.$bus.$emit('show-update-password-dialog');
      }
        this.$router.push(`/user/${name}`)
    },
    menuHandler(name) {
        this.$router.push(`/${name}`)
    }
  },
};
</script>
<style scoped lang="less">
span {
    margin-left: 6px;
    vertical-align: middle;
}
</style>
