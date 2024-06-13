<template>
  <div class="container">
    <el-form :model="form" ref="form" :rules="rules" label-width="28%">
      <el-form-item label="用户名:" prop="username">
        <el-input v-model="form.username" clearable></el-input>
      </el-form-item>
      <el-form-item label="昵 称:" prop="nickname">
        <el-input v-model="form.nickname" clearable></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="form.sex" placeholder="请选择性别">
          <el-option
            v-for="option in genderOptions"
            :key="option.value"
            :label="option.label"
            :value="option.value"
            clearable
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="密 码:" prop="password">
        <el-input type="password" v-model="form.password" clearable></el-input>
      </el-form-item>
      <el-form-item label="邮 箱:" prop="email">
        <el-input type="email" v-model="form.email" clearable></el-input>
      </el-form-item>
      <el-form-item label="手机号:" prop="telphone">
        <el-input type="tel" v-model="form.telphone" clearable></el-input>
      </el-form-item>
      <el-form-item label="初始资金:" prop="money">
        <el-input type="tel" v-model="form.money" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit" style="width: 70%">注 册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        username: "",
        nickname: "",
        sex: "",
        password: "",
        email: "",
        telphone: "",
        money: Number
      },
      genderOptions: [
        // 性别选项数组
        { label: "男", value: "男" },
        { label: "女", value: "女" },
        // 可以根据需要添加更多选项
      ],
      rules: {
        username: [{ required: true, message: "请输入用户名", trigger: "blur" },],
        nickname: [{ required: true, message: "请输入昵称", trigger: "blur" }],
        sex: [{ required: true, message: "请选择性别", trigger: "change" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        email: [{ required: true, message: "请输入邮箱", trigger: "blur" }],
        telphone: [{ required: true, message: "请输入手机号", trigger: "blur" },],
        money: [{ required: true, trigger: "blur" },],
      },
    };
  },
  components: {},
  mounted() {},
  methods: {
    async onSubmit() {
      try {
        const valid = await new Promise((resolve) => {
          this.$refs.form.validate((valid) => {
            resolve(valid);
          });
        });
        if (valid) {
          if (this.form.username.length < 3) {
            this.$message.error(`账号不能少于3位`);
            return;
          }
          if (this.form.password.length < 5) {
            this.$message.error(`密码不能少于5位`);
            return;
          }
          await this.$store.dispatch("register", this.form);
          this.$message({
            message: "注册成功",
            type: "success",
          });
          this.$router.push({
            path: "/login",
          });
        } else {
          return false;
        }
      } catch (error) {
        this.$message.error("注册失败");
        console.log(error);
      }
    },
  },
};
</script>
<style scoped lang="less">
.container {
  width: 40%;
  margin: 2% auto;
  .el-form {
    background: radial-gradient(circle, rgb(228, 243, 199), #fffef2);
    border: 0.1px solid #fffef2;
    border-radius: 8px;
    box-shadow: 20px 20px 20px 0px rgb(215, 240, 177);
    .el-form-item {
      margin-top: 6%;
    }
    .el-input {
      width: 80%;
    }
  }
}
</style>
