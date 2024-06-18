<template>
  <el-dialog 
      title="支付成功"
      :visible.sync="dialogVisible"
      width="30%"
      @close="handleClose">
    <div class="paysuccess">
      <div class="success">
        <h3>
          <img src="./images/right.png" width="48" height="48" />
          恭喜您，支付成功啦！
        </h3>
        <div class="paydetail">
          <a class="btn-look" @click="dialogVisible=false">返回租赁订单</a>
        </div>
      </div>
    </div>
  </el-dialog>
  
</template>

<script>
export default {
  name: "PaySuccess",
  data() {
    return {
      dialogVisible: false,
    }
  },
  mounted() {
    this.$bus.$on("show-pay-success-dialog", this.showDialog);
  },
  beforeDestroy() {
    // 移除事件监听器
    this.$bus.$off("show-pay-success-dialog", this.showDialog);
  },
  methods: {
    showDialog() {
      this.dialogVisible = true;
    },
    handleClose() {
      this.$router.push({ path: '/rental' }).catch(err => {
        if (err.name !== 'NavigationDuplicated') {
          throw err;
        }
      });
    },
  }
};
</script>

<style lang="less" scoped>
/deep/ .el-dialog__wrapper{
  margin: 40px auto
}
.paysuccess {
  margin: 20px auto;
  padding: 25px;
  border: 1px solid rgb(211, 211, 211);
  .success {
    margin: 0 auto;

    h3 {
      margin: 20px 0;
      font-weight: 700;
      font-size: 20px;
      line-height: 30px;

      img {
        max-width: 100%;
        vertical-align: middle;
        border: 0;
        margin-right: 14px;
      }
    }

    .paydetail {
      margin: 0 auto;
      font-size: 15px;
      text-align: center;
      a {
          display: inline-block;
          box-sizing: border-box;
          text-align: center;
          vertical-align: middle;
          cursor: pointer;
          border-radius: 2px;
          user-select: none;
          font-size: 18px;
          padding: 4px 20px;
          line-height: 22px;
          text-decoration: none;

          &.btn-look {
            margin-right: 13px;
            color: #fff;
            background-color: #e1251b;
            border: 1px solid #e1251b;
          }
        }
    }
  }
}
</style>
