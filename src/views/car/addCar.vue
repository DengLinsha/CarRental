<template>
    <div class="container">
      <el-dialog
        title="租赁汽车"
        :visible.sync="dialogVisible"
        width="30%"
        @close="handleClose"
      >
        <el-form
          class="detail-form-content"
          ref="ruleForm"
          :model="ruleForm"
          label-width="80px"
        >
        <el-descriptions>
          <el-descriptions-item label="汽车图片">
            <img class="img" :src="ruleForm.picUrl" alt="" width="50%"/>
          </el-descriptions-item>
        </el-descriptions>
          <el-row>
            <el-col :span="12">
              <el-form-item class="input" label="账号" prop="username">
                <el-input
                  v-model="ruleForm.username"
                  placeholder="账号"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="姓名" prop="nickname">
                <el-input
                  v-model="ruleForm.nickname"
                  placeholder="姓名"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="手机" prop="telphone">
                <el-input
                  v-model="ruleForm.telphone"
                  placeholder="手机"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="车辆名称" prop="carName">
                <el-input
                  v-model="ruleForm.carName"
                  placeholder="车辆名称"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="车牌号码" prop="carIdentity">
                <el-input
                  v-model="ruleForm.carIdentity"
                  placeholder="车牌号码"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
  
            <el-col :span="12">
              <el-form-item class="input" label="换挡方式" prop="gears">
                <el-input
                  v-model="ruleForm.gears"
                  placeholder="换挡方式"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="租赁价格" prop="price">
                <el-input
                  v-model="ruleForm.price"
                  placeholder="租赁价格"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
  
            <el-col :span="12">
              <el-form-item class="input" label="租赁天数" prop="dailyday">
                <el-input
                  v-model="ruleForm.dailyday"
                  placeholder="租赁天数"
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row> </el-row>
          <el-form-item class="btn">
            <el-button type="primary" @click="submit" class="sure"
              >确 定</el-button
            >
            <el-button @click="dialogVisible = false">取 消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <pay></pay>
    </div>
  </template>
  
  <script>
  const pay = () => import("../rental/Pay/index.vue")
  import { mapState } from "vuex";
  export default {
    data() {
      return {
        dialogVisible: false,
        ruleForm: {
          dailyday: 1
        },
        car: {},
        params: {}
      };
    },
    components: {pay},
    computed: {
      ...mapState({
        username: (state) => state.user.loginData.username,
        userinfo: (state) => state.user.userinfo,
      }),
    },
    mounted() {
      // 监听事件总线
      this.$bus.$on("show-add-car-dialog", (car) => {
        console.log(car);
        this.car = car;
        this.$store.dispatch("getUserInfo", this.username);
        this.load();
        this.$nextTick(() => {
          this.showDialog();
        });
      });
      
    },
    beforeDestroy() {
      // 移除事件监听器
      this.$bus.$off("show-add-car-dialog", this.showDialog);
    },
    methods: {
      load() {
        Object.assign(this.ruleForm, this.userinfo);
        Object.assign(this.ruleForm, this.car);
      },
      showDialog() {
        this.dialogVisible = true;
      },
      async submit() {
          this.dialogVisible = false;
          const {userId, carId, dailyday, price} = this.ruleForm
          this.params.userId = userId,
          this.params.carId = carId,
          this.params.cost = dailyday * price,
          this.params.dailyday = dailyday,
          this.params.payState = 0
          this.$store.dispatch('carAdd', this.params)
      },
      handleClose() {
        this.$router.push({ path: '/car' }).catch(err => {
          if (err.name !== 'NavigationDuplicated') {
            throw err;
          }
        });
      },
    },
  };
  </script>
  
  <style scoped lang="less">
   /deep/.el-dialog {
    width: 45% !important
  }
  /deep/ .el-dialog__wrapper{
  margin: -80px auto
}
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
  