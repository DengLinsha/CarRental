<template>
  <div class="navbar clearfix">
		<div class="title-menu leftfix">
			<Car/><span class="title-name">汽车租赁系统</span>
		</div>
		<div class="right-menu rightfix">
			<span class="user-info">用户{{ username }}</span>
			<span class="logout" @click="logout">退出登录</span>
      <span class="delete-user" @click="deleteUser">注销</span>
		</div>
	</div>
</template>

<script>
import {Car} from "@icon-park/vue";
import { mapState } from "vuex";
export default {
  data () {
    return {

    }
  },
  computed: {
    ...mapState({
      username: (state) => state.user.loginData.username
    })
  }, 
  components: {
    Car
  },
  mounted() {

  },
  methods: {
    logout() {
      this.$router.push({
          path: "/login"
      })
    },
    deleteUser() {
      this.$store.dispatch('updateUser', this.username)
      this.$router.push({
            path: "/login"
        })
    }
  },
}

</script>
<style scoped lang='less'>
.navbar {
    padding: 0 20px;
    height: 50px;
    line-height: 50px;
    font-weight: bold;
    background-color: chocolate;
}
.title-menu {
    span {
        font-size: 20px;
    }
}
.right-menu {
    span {
        font-size: 15px;
        margin-right: 18px;
    }
    .logout,.delete-user {
        cursor: pointer;
    }
}
</style>