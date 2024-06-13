<template>
  <div class="background">
    <el-row>
      <el-col :span="16">
        <div>
          <h2></h2>
        </div>
      </el-col>
      <el-col :span="8" v-show="showLogin">
        <div class="login">
          <el-form :model="loginForm" :rules="rules" ref="loginForm" class="clearfix">
            <el-form-item  prop="username" :rules="rules.username">
                <el-input
                    v-model="loginForm.username"
                    prefix-icon="el-icon-user"
                    placeholder="请输入账号"
                    
                ></el-input>
            </el-form-item>
            <el-form-item prop="password" :rules="rules.password">
              <el-input
              v-model="loginForm.password"
              prefix-icon="el-icon-lock"
                type="password"
                auto-complete="off"
                placeholder="密码"
              ></el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="success" class="bt" @click="login">登录</el-button>
            </el-form-item>
            <div class="register">
              <router-link class="rightfix register" to="/register">注 册</router-link>
              <span class="rightfix forget-password" @click="forgetPassword">忘记密码？</span>
            </div>
          </el-form>
        </div>
      </el-col>
      <el-col :span="8" v-show="showForget">
        <div class="forget">
            <el-form :model="findForm" :rules="rules" ref="findForm">
                <el-form-item  prop="username" :rules="rules.username">
                <el-input
                    v-model="findForm.username"
                    prefix-icon="el-icon-user"
                    placeholder="请输入账号"
                ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="info" class="bt" @click="findPassword">找回密码</el-button>
                </el-form-item>
                <el-form-item>
                  <el-button type="success" class="bt" @click="goback">返回登录页面</el-button>
                </el-form-item>
            </el-form>
        </div>
    </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
export default {
  data() {
    return {
      showLogin: true,
      showForget: false,
      loginForm: {
        username: "",
        password: "",
      },
      findForm: {
        username: ""
      },
      rules: {
        username: [
          { required: true, message: "请输入账号", trigger: "blur" },
          { min: 3, max: 16, message: "长度为3-16位", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 5, max: 16, message: "长度为5-16位", trigger: "blur" },
        ],
      },
    };
  },
  components: {},
  computed: {
    ...mapState({
      findUserPassword: (state) => state.user.findUserPassword,
    })
  },
  mounted() {},
  methods: {
    async login() {
      try {
        // 确保 validate 方法返回一个 Promise
        const valid = await new Promise((resolve) => {
          this.$refs.loginForm.validate((valid) => {
            resolve(valid);
          });
        });
        if (valid) {
          const { username, password } = this.loginForm;
          await this.$store.dispatch('login', { username, password });
          this.$router.push({ path: '/car' });
        } else {
          return false;
        }
      } catch (error) {
        this.$message.error('用户名或者密码错误！');
        console.log(error);
      }
    },
    forgetPassword() {
        this.showLogin = !this.showLogin;
        this.showForget = !this.showForget;
    },
    async findPassword() {
      try {
        await this.$store.dispatch("getPassword", this.findForm.username);
        this.$message({
            message: `找回密码成功！密码为${this.findUserPassword}`,
            type: 'success'
          });
      } catch (error) {
        this.$message.error('用户名不存在！');
        console.log(error);
      }
        
    },
    goback() {
      this.$router.push({
        path: "/login",
      });
    }
  },
};
</script>
<style scoped lang="less">
.background {
  background-image: url("@/assets/img/car.png");
  background-size: 55%;
  background-repeat: no-repeat;
  background-position-x: 100px;
}
.login,.forget {
    
  margin-top: 25%;
  margin-right: 20%;
  border: darkturquoise solid 1px;
  padding: 5%;
  .register {
    display: flex;
    justify-content: space-between;
    margin-left: 20px;
    .forget-password {
      cursor: pointer;
      color:rgb(130, 66, 3);
    }
  }
}
.bt {
    width: 100%;
}
</style>
