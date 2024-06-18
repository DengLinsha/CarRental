<template>
    <div class="container">
      <el-dialog
        title="租赁汽车订单详情"
        :visible.sync="dialogVisible"
        width="30%"
        @close="handleClose"
      >
        <el-form
          class="detail-form-content"
          ref="info"
          :model="info"
          label-width="80px"
        >
        <el-descriptions>
          <el-descriptions-item label="汽车图片">
            <img class="img" :src="info.picUrl" alt="" width="50%"/>
          </el-descriptions-item>
        </el-descriptions>
          <el-row>

            <el-col :span="12">
              <el-form-item class="input" label="账号" prop="username">
                <el-input
                  v-model="info.username"
                  placeholder="账号"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="姓名" prop="nickname">
                <el-input
                  v-model="info.nickname"
                  placeholder="姓名"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="手机" prop="telphone">
                <el-input
                  v-model="info.telphone"
                  placeholder="手机"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="车辆名称" prop="carName">
                <el-input
                  v-model="info.carName"
                  placeholder="车辆名称"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="车牌号码" prop="carIdentity">
                <el-input
                  v-model="info.carIdentity"
                  placeholder="车牌号码"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
  
            <el-col :span="12">
              <el-form-item class="input" label="换挡方式" prop="gears">
                <el-input
                  v-model="info.gears"
                  placeholder="换挡方式"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="换挡方式" prop="carSeat">
                <el-input
                  v-model="info.carSeat"
                  placeholder="座位数"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="租赁价格" prop="price">
                <el-input
                  v-model="info.price"
                  placeholder="租赁价格"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
  
            <el-col :span="12">
              <el-form-item class="input" label="租赁天数" prop="dailyday">
                <el-input
                  v-model="info.dailyday"
                  placeholder="租赁天数"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="总价" prop="cost">
                <el-input
                  v-model="info.cost"
                  placeholder="总价"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="取车地址" prop="address">
                <el-input
                  v-model="info.address"
                  placeholder="取车地址"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="input" label="借车日期" prop="borrowDate">
                <el-input
                  v-model="info.borrowDate"
                  placeholder="借车日期"
                  readonly
                  clearable
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item class="input" label="被预定？" prop="bookingState">
                <el-input
                    v-model="bookingStatus"
                    readonly
                    clearable
                ></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item class="input" label="被使用？" prop="carState">
                <el-input
                    v-model="carStateStatus"
                    readonly
                    clearable
                ></el-input>
                </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-dialog>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        dialogVisible: false,
        info: {},
      };
    },
    computed: {
        bookingStatus() {
            return this.info.bookingState === 1 ? "已被预定" : "未被预定";
        },
        carStateStatus() {
            return this.info.carState === 1 ? "正在使用" : "未被使用";
        },
      
    },
    mounted() {
      // 监听事件总线
      this.$bus.$on("show-detail-dialog", (info) => {
        this.info = info;
        
        this.$nextTick(() => {
          this.showDialog();
        });
      });
      
    },
    beforeDestroy() {
      // 移除事件监听器
      this.$bus.$off("show-detail-dialog", this.showDialog);
    },
    methods: {
      
      showDialog() {
        this.dialogVisible = true;
      },
      async submit() {
          this.dialogVisible = false;
      },
      handleClose() {
        this.$router.push({ path: '/rental' }).catch(err => {
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
  margin: -80px auto;
  height: 770px
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
  