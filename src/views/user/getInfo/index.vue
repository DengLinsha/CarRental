<template>
  <div class="container">
    <el-card>
      <el-descriptions class="margin-top" title="简介" :column="2" border>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-picture-outline"></i>
            头像
          </template>
          <img class="img" :src="avator" alt="" />
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            账户名
          </template>
          {{ username }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-c-scale-to-original"></i>
            用户编号
          </template>
          {{ userId }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-custom"></i>
            昵称
          </template>
          {{ nickname }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-male"></i>
            <i class="el-icon-female"></i>
            性别
          </template>
          <el-tag size="small">{{ sex }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-message"></i>
            邮箱Email
          </template>
          {{ email }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            手机号码
          </template>
          {{ telphone }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-c-scale-to-original"></i>
            余额
          </template>
          {{ money }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-truck"></i>
            是否正在租车
          </template>
          {{ useState }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-coin"></i>
            是否预定汽车
          </template>
          {{ bookingCar }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-location"></i>
            通信地址
          </template>
          {{ userAddress }}
        </el-descriptions-item>
      </el-descriptions>
    </el-card>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: "Info",
  data() {
    return {
      avator: String,
      email: String,
      telphone: String,
      nickname: String,
      sex: String,
      useState: null,
      bookingCar: null,
      userAddress: null,
      userId: null,
      money: null
    };
  },
  computed: {
    ...mapState({
      username: state => state.user.loginData.username,
      userinfo: state => state.user.userinfo
    })
  },
  async mounted() {
    await this.$store.dispatch("getUserInfo", this.username)
    this.load()
  },
  methods: {
    load() {
      const {nickname, picUrl, telphone, email, sex, useState, bookingCar, address, userId, money} = this.userinfo
      this.nickname = nickname,
      this.picUrl = picUrl,
      this.telphone = telphone,
      this.email = email,
      this.sex = sex,
      this.useState = useState,
      this.bookingCar = bookingCar,
      this.address = address,
      this.userId = userId,
      this.money = money
    },
  },
};
</script>

<style scoped>
.container {
  margin-top: 2%;
}
/deep/ .el-descriptions {
  font-size: 16px;
}
/deep/.el-descriptions__title {
  font-size: 22px;
}
.img {
  width: 80px;
  height: 80px;
}
</style>
