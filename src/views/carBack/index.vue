<template>
  <div>
    <el-table
      :data="backorders"
      border
      :header-cell-style="{ background: '#eef1f6', color: '#606266' }"
      class="rental-container"
    >
      <el-table-column prop="username" label="账户名" width="130">
      </el-table-column>
      <el-table-column prop="nickname" label="昵称" width="150">
      </el-table-column>
      <el-table-column prop="telphone" label="手机" width="160">
      </el-table-column>
      <el-table-column prop="carName" label="车辆名称" width="140">
      </el-table-column>
      <el-table-column prop="carIdentity" label="车牌号码" width="150">
      </el-table-column>
      <el-table-column prop="gears" label="换挡类型" width="200">
      </el-table-column>
      <el-table-column
      prop="carSeat"
      label="车辆座数"
      width="300">
    </el-table-column>
      
      <el-table-column prop="price" label="总支付" width="140">
      </el-table-column>
      <el-table-column prop="borrowDate" label="借车日期" width="140">
      </el-table-column>
      <el-table-column prop="backDate" label="还车日期" width="140">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  data() {
    return {
      

    };
  },
  computed: {
    ...mapState({
      username: (state) => state.user.loginData.username,
      tempBackOrders: (state) => state.order.backorder,
      userinfo: (state) => state.user.userinfo
    }),
    backorders() {
      return this.tempBackOrders.map(backorder => ({
        ...backorder,
        ...this.userinfo
      }));
    }
  },
  async mounted() {
    this.$store.dispatch("getUserInfo", this.username)
    try {
      await this.$store.dispatch('BackOrder', this.userinfo.userId)
    } catch (error) {
      this.$message.error("该账号没有还车订单信息")
    }
  },
  methods: {
    
  },

};
</script>
<style scoped lang="less">
/deep/ .el-table {
  margin-top: 28px;
}
</style>
