<template>
  <div class="com-short-rent-caritem">
    <div class="thumb">
      <img :src="car.picUrl" :alt="car.carName" style="width: 100%;">
    </div>
    <div class="data">
      <header>
        <h2>{{ car.carName }}</h2>
        <div class="price">
          ￥<strong>{{ car.price }}</strong
          >/天
        </div>
      </header>
      <p class="info">
        {{ car.carIdentity
        }}<!-- -->
        |
        <!-- -->{{ car.gears
        }}<!-- -->
        |
        <!-- -->{{ car.carSeat }}座<!-- -->
      </p>
      <div class="tag">地址：{{ car.address }}</div>
      <div class="msg">
        {{ car.comments }}
      </div>
      <button class="use" :disabled="car.carState === 1" @click="useCar">
        立即使用
      </button>
      <button class="addCar" :disabled="car.carState === 1" @click="addCar">
        加入购物车
      </button>
      <button class="booking" :disabled="car.bookingState === 1" @click="book">立即预定</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    car: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {};
  },
  components: {},
  mounted() {},
  methods: {
    useCar() {
      this.$bus.$emit("show-use-car-dialog", this.car);
    },
    addCar() {
      this.$bus.$emit("show-add-car-dialog", this.car);
    },
    book() {
      this.$bus.$emit("show-book-car-dialog", this.car);
    }
  },
};
</script>
<style scoped lang="less">
.com-short-rent-caritem {
  width: 900px;
  margin: 10px auto;
  background-color: #f7f2ec;
  border-radius: 10px;
  padding: 16px;
  margin-bottom: 8px;
  display: flex;
  .thumb {
    display: block;
    width: 190px;
    height: 167px;
    border-radius: 6px;
    background-color: #fafafa;
    margin-right: 16px;
    overflow: hidden;
    position: relative;
  }
  .data {
    flex: 1 1;
    position: relative;
    header {
      display: flex;
      height: 40px;
      h2 {
        padding: 2px 8px;
        margin: auto;
        flex: 1 1;
      }
      .price {
        display: flex;
        font-size: 18px;
        align-items: center;
      }
    }
    .info {
      display: block;
      font-size: 14px;
      padding: 2px 8px;
      color: rgba(60, 60, 90, 0.6);
      margin-bottom: 2px;
    }
    .tag {
      width: 350px;
      border: 1px solid rgba(191, 120, 44, 0.2);
      color: #bf782c;
      border-radius: 10px;
      padding: 2px 8px;
      line-height: 1;
      font-size: 14px;
      margin: 18px 0;
    }
    .msg {
      background-color: rgba(44, 155, 142, 0.05);
      border-radius: 30px;
      margin-bottom: 4px;
      padding: 2px 8px;
      text-overflow: ellipsis;
      white-space: nowrap;
      overflow: hidden;
      max-width: 500px;
      font-size: 14px;
      color: #2c9b8e;
    }
    .booking,
    .addCar,
    .use {
      position: absolute;
      right: 0;
      bottom: 0;
      border: 0;
      width: 92px;
      height: 35px;
      background-color: #00cf97;
      display: flex;
      align-items: center;
      justify-content: center;
      color: #fff;
      font-size: 14px;
      font-weight: 700;
      border-radius: 44px;
      cursor: pointer;
    }
    .use {
      right: 0;
      bottom: 43px;
    }
    .addCar {
      right: 0;
      bottom: 86px;
    }
    .use:disabled,
    .booking:disabled {
      background-color: #ccc;
      cursor: not-allowed;
    }
  }
}
</style>
