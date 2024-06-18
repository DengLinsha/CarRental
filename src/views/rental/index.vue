<template>
  <div>
    <el-form :model="searchForm" class="form-content">
      <el-row class="search">
        <el-form-item>
          <el-input
            v-model="searchForm.carIdentity"
            placeholder="车牌号码"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input
            v-model="searchForm.carName"
            placeholder="车辆名称"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            class="btn"
            icon="el-icon-search"
            type="success"
            @click="search"
            >查询</el-button
          >
        </el-form-item>
      </el-row>
    </el-form>
    <el-table
      :data="orders"
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
      <!-- <el-table-column
      prop="carSeat"
      label="车辆座数"
      width="300">
    </el-table-column> -->
      <el-table-column prop="payState" label="是否支付" width="200">
        <template slot-scope="scope">
          <span v-if="scope.row.payState === 1">已支付</span>
          <span
            v-else
            style="color: #c80e0e; font-weight: bold; cursor: pointer"
            @click="pay"
            ><i class="el-icon-edit"></i>未支付</span
          >
        </template>
      </el-table-column>
      <el-table-column prop="price" label="总价" width="140">
      </el-table-column>
      <el-table-column class="action" label="操作" width="200">
        <template slot-scope="scope">
          <el-button
            class="btn"
            type="success"
            size="mini"
            @click="detail(scope.row)"
            ><i class="el-icon-tickets el-icon--right" />详情</el-button
          >
          <el-button
            class="btn"
            type="success"
            size="mini"
            @click="carBack(scope.row)"
            ><i class="el-icon-tickets el-icon--right" />还车</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <pay></pay>
    <detail></detail>
  </div>
</template>

<script>
import { mapState } from "vuex";
const pay = () => import("./Pay/index.vue");
const detail = () => import("./detail.vue");

export default {
  data() {
    return {
      searchForm: {
        carIdentity: "",
        carName: ""
      },
      carBackParams: {}
    };
  },
  computed: {
    ...mapState({
      username: (state) => state.user.loginData.username,
      tempOrders: (state) => state.order.order,
      userinfo: (state) => state.user.userinfo
    }),
    orders() {
      return this.tempOrders.map(order => ({
        ...order,
        ...this.userinfo
      }));
    }
  },
  mounted() {
    this.$store.dispatch("getOrderInfo", this.username);
    this.$store.dispatch("getUserInfo", this.username)
    
  },
  components: { pay, detail },
  methods: {
    async search() {
      let result = await this.$API.searchOrder(this.userinfo.userId, this.searchForm.carIdentity, this.searchForm.carName)
      this.$store.commit("SEARCHORDER", result.data)
    },
    pay() {
      this.$bus.$emit("show-pay-dialog", this.orders);
    },
    detail(row) {
      console.log(row);
      this.$bus.$emit("show-detail-dialog", row);
    },
    carBack(row) {
      if (row.payState === 0) {
        this.$message.error("您还未支付，不能还车！");
        return; // 如果未支付，直接返回
      }
      this.$confirm("是否确定还车？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        const { userId, carId } = row;
        this.carBackParams.userId = userId;
        this.carBackParams.carId = carId;
        this.$store.dispatch('carBack', this.carBackParams);
      }).catch(() => {
        this.$message.info("已取消还车操作");
      });
    }
  },
};
</script>
<style scoped lang="less">
.search {
  margin: 15px 0 0 0 !important;
  display: flex;
  justify-content: space-evenly;
}
.rental-container {
  margin: 0 auto;
}
.btn {
  background-color: rgb(232, 198, 111);
  border: 1px solid rgb(232, 198, 111);
}
</style>
