<template>
  <div>
    <el-dialog
      title="修改密码"
      :visible.sync="dialogVisible"
      width="30%"
      @close="handleClose"
    >
      <el-form
        ref="ruleForm"
        :rules="rules"
        :model="ruleForm"
        label-width="80px"
      >
        <el-form-item label="原密码" prop="oldpassword">
          <el-input type="password" v-model="ruleForm.oldpassword"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newpassword">
          <el-input type="password" v-model="ruleForm.newpassword"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="repassword">
          <el-input type="password" v-model="ruleForm.repassword"></el-input>
        </el-form-item>
        <el-form-item class="btn">
          <el-button type="primary" @click="onUpdateHandler" class="sure">确 定</el-button>
          <el-button @click="dialogVisible = false">取 消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  data () {
    return {
      dialogVisible: true,
      ruleForm: {
        oldpassword: '',
        newpassword: '',
        repassword: ''
      },
      rules: {
        oldpassword: [
          { required: true, message: "原密码不能为空", trigger: "blur" }
        ],
        newpassword: [
          { required: true, message: "新密码不能为空", trigger: "blur" }
        ],
        repassword: [
          { required: true, message: "确认密码不能为空", trigger: "blur" }
        ]
      }
    }
  },
  computed: {
    ...mapState({
      username: state => state.user.loginData.username,
      password: state => state.user.loginData.password
    })
  },
  mounted() {
    // 监听事件总线
    this.$bus.$on('show-update-password-dialog', this.showDialog);
  },
  beforeDestroy() {
    // 移除事件监听器
    this.$bus.$off('show-update-password-dialog', this.showDialog);
  },
  methods: {
    showDialog() {
      this.dialogVisible = true;
    },
    async onUpdateHandler() {
      this.$refs.ruleForm.validate(async valid => {
        if (valid) {
          if (this.ruleForm.oldpassword !== this.password) {
            this.$message.error("原密码错误");
            return;
          }
          if (this.ruleForm.newpassword !== this.ruleForm.repassword) {
            this.$message.error("两次密码输入不一致");
            return;
          }
          if (this.ruleForm.newpassword.length < 5) {
            this.$message.error(`密码不能少于5位`);
            return;
          }
          try {
            await this.$store.dispatch('updatePassword', {
              username: this.username,
              password: this.ruleForm.newpassword
            });
            this.$message({
              message: "修改密码成功, 下次登录系统生效",
              type: "success"
            });
            this.dialogVisible = false;
          } catch (error) {
            this.$message.error("修改密码失败");
          }
        }
      });
    },
    handleClose() {
      this.ruleForm.oldpassword = '';
      this.ruleForm.newpassword = '';
      this.ruleForm.repassword = '';
      this.$router.push({
        path: '/car'
      })
    }
  }
}
</script>

<style scoped lang="less">
.btn {
  display: flex;
  justify-content: space-around;
  .sure {
    margin-left: -72px;
  }
}

/deep/ .el-button--default {
  margin-left: 80px;
}
</style>
