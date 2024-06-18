<template>
  <div class="pay-main">
    <el-dialog
      title="支付页面"
      :visible.sync="dialogVisible"
      width="30%"
      @close="handleClose"
    >
    <div class="paymark">
      <span class="lead">支付金额：</span>
      <span class="money">￥{{ money }}</span>
    </div>
    <div class="checkout">
      <span style="font-size: 16px;">支付方式：</span>
      <ul class="payType">
        <li><img src="./images/pay2.jpg" /></li>
        <li><img src="./images/pay3.jpg" /></li>
      </ul>
    </div>
    <div class="submit">
      <a class="btn" @click="open">立即支付</a>
    </div>
  </el-dialog>
  <paySuccess></paySuccess>
  </div>
</template>

<script>
const paySuccess = () => import("@/views/rental/PaySuccess/index.vue")
export default {
  data () {
    return {
      dialogVisible: false,
      money: null,
      order: {},
      params: {}
    }
  },
  components: {
    paySuccess
  },
  mounted() {
    this.$bus.$on("show-pay-dialog", order => {
      this.order = order
      this.money = order.dailyday * order.price
      this.$nextTick(() => {
        this.showDialog();
      });
    });
  },
  beforeDestroy() {
    // 移除事件监听器
    this.$bus.$off("show-pay-dialog", this.showDialog);
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
    async open() {
      this.$alert(`<img src="/wechat_code.jpg" style="width: 100%;">`, "请你微信支付", {
        dangerouslyUseHTMLString: true,
        // 居中
        center: true,
        // 显示取消按钮
        showCancelButton: true,
        cancelButtonText: "取消",
        confirmButtonText: "已支付成功",
        // 右上角的×不显示
        showClose: false,
        // 关闭弹框的方法
        beforeClose: (type, instance, done) => {
          // type: 区分取消还是确定按钮
          // instance：当前组件实例
          // done：关闭弹框的方法
          if (type === 'confirm') {
            this.$bus.$emit("show-pay-success-dialog")
          }
          done();
          this.dialogVisible = false;
        }
      });
      const {userId, carId, dailyday} = this.order
      this.params.userId = userId,
      this.params.carId = carId,
      this.params.cost = this.money,
      this.params.dailyday = dailyday,
      this.params.payState = 1
      // console.log(this.params);
      try {
        await this.$store.dispatch('createOrder', this.params)
      } catch (error) {
        this.$message.error("创建订单失败！资金不足")
      }
      
    }
  },
}

</script>
<style scoped lang='less'>
  /deep/ .el-dialog__wrapper{
  margin: 40px auto
}
.pay-main {
  border: 1px solid #ddd;
  margin: 20px auto;
  .paymark {
    margin-bottom: 20px;
    .lead {
      margin-bottom: 18px;
      font-size: 16px;
      font-weight: 400;
      line-height: 22.5px;
    }
    .money {
      font-size: 19px;
      color: #e1251b;
    }
  }
  ul {
    font-size: 0;
    margin: 8px auto;
    li {
      margin: 3px;
      display: inline-block;
      padding: 5px 20px;
      border: 1px solid #ddd;
      cursor: pointer;
      line-height: 18px;
    }
  }
  .submit {
    text-align: center;

    .btn {
      display: inline-block;
      padding: 15px 45px;
      margin: 15px 0 10px;
      font: 18px "微软雅黑";
      font-weight: 700;
      border-radius: 0;
      background-color: #e1251b;
      border: 1px solid #e1251b;
      color: #fff;
      text-align: center;
      vertical-align: middle;
      cursor: pointer;
      user-select: none;
      text-decoration: none;
    }
  }
}
</style>