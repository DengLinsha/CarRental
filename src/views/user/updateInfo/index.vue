<template>
  <div>
      <el-dialog
        title="修改个人信息"
        :visible.sync="dialogVisible"
        width="60%"
        :before-close="handleClose"
      >
      <el-form :model="form" :rules="rules" ref="form" label-width="150px">
        <div class="updateinfo">
          <div class="left">
            <el-form-item label="头像" prop="avator">
              <img style="width:100px;height:95px" :src="form.picUrl" />
            </el-form-item>
            <el-form-item label="通信地址" prop="address">
              <el-input v-model="form.address"></el-input>
            </el-form-item>
            <el-form-item label="昵称" prop="nickname">
              <el-input v-model="form.nickname"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
              <el-switch
                v-model="form.sex"
                active-color="#666"
                inactive-color="pink"
                active-text="男"
                inactive-text="女"
                :active-value= "1"
                :inactive-value= "0"
              >
              </el-switch>
            </el-form-item>
            <el-form-item label="租车" prop="useState">
              <el-switch
                v-model="form.useState"
                active-color="#13ce66"
                inactive-color="#666"
                active-text="正使用"
                inactive-text="未使用"
                :active-value= "1"
                :inactive-value= "0"
              >
              </el-switch>
            </el-form-item>
          </div>
          <div class="right">
            <el-form-item label="用户编号" prop="userId">
              <el-input v-model="form.userId" disabled></el-input>
            </el-form-item>
            <el-form-item label="账号" prop="username">
              <el-input v-model="form.username" disabled></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="telphone">
              <el-input v-model="form.telphone"></el-input>
            </el-form-item>
            <el-form-item label="通信地址" prop="userAddress">
              <el-input v-model="form.userAddress"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email"></el-input>
            </el-form-item>  
            <el-form-item label="预订" prop="bookingCar">
              <el-switch
                v-model="form.bookingCar"
                active-color="#13ce66"
                inactive-color="#666"
                active-text="已预订"
                inactive-text="未预订"
                :active-value= "1"
                :inactive-value= "0"
              >
              </el-switch>
            </el-form-item>
          </div>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleClose">取 消</el-button>
        <el-button type="primary" @click="submit">提 交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>

import { mapState } from 'vuex';
export default {

  data() {
    return {
      dialogVisible: true,
      form: {
        avator: "",
        username: "",
        nickname: "",
        email: "",
        telphone: "",
        sex: null,
        userId: null,
        userAddress: "",
        useState: null,
        bookingCar: null
      },
      formWithoutPicUrl: {},
      rules: {
        userId: [
          { required: true, message: "用户编号不能为空", trigger: "blur" },
        ],
        username: [
          { required: true, message: "账号名不能为空", trigger: "blur" },
        ],
      },
    };
  },
  computed: {
    ...mapState({
      username: state => state.user.loginData.username,
      userinfo: state => state.user.userinfo
    })
  },
  mounted() {
    this.$store.dispatch("getUserInfo", this.username)
    this.load()
  },
  methods: {
    load() {
      const {nickname, avator, telphone, email, sex, useState, bookingCar, userAddress, userId} = this.userinfo
      this.form.nickname = nickname,
      this.form.avator = avator,
      this.form.telphone = telphone,
      this.form.email = email,
      this.form.sex = sex,
      this.form.useState = useState,
      this.form.bookingCar = bookingCar,
      this.form.userAddress = userAddress,
      this.form.userId = userId,
      this.form.username = this.username
    },
    async submit() {
      try {
        // 去除picUrl属性
        // const { picUrl, ...rest } = this.form;
        // this.formWithoutPicUrl = rest;
        // await this.$store.dispatch('updateUserInfo', this.formWithoutPicUrl)
        await this.$store.dispatch('updateUserInfo', this.form)
        this.$message({
          message: "修改个人信息成功！",
          type: "success"
        });
        this.dialogVisible = false;
      } catch (error) {
        console.log(error);
      }
    },
    handleClose() {
      this.dialogVisible = false;
      this.$router.push({
        path: '/home'
      })
    },
  },
};
</script>

<style scoped>
.updateinfo {
  height: 400px;
  overflow: auto;
}
.left {
  /* width: 330px; */
  float: left;
}
.right {
  overflow: hidden;
}
</style>
