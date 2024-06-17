<template>
  <div class="container">
    <div class="search">
      <el-input
        v-model="searchForm.carIdentity"
        placeholder="请输入你想要查找的车牌号"
      ></el-input>
      <el-select v-model="searchForm.gears" filterable placeholder="请选择挡位">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      <el-select
        v-model="searchForm.carSeat"
        filterable
        placeholder="请选择座位数"
      >
        <el-option
          v-for="item in seatOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      <button class="search-btn" @click="goSearch">搜索</button>
    </div>
    <div class="info clearfix">
      <div class="search-info leftfix">
        <div class="search-container brand">
          <h2>常见品牌</h2>
          <ul class="search-brand flex-start-class">
            <li
              v-for="brand in brands"
              :key="brand"
              :class="{ current: currentBrand === brand }"
              @click="selectBrand(brand)"
            >
              {{ brand }}
            </li>
          </ul>
        </div>
        <div class="search-container city">
          <h2>取车城市</h2>
          <ul class="search-brand flex-start-class">
            <li
              v-for="city in citys"
              :key="city"
              :class="{ current: currentCity === city }"
              @click="selectCity(city)"
            >
              {{ city }}
            </li>
          </ul>
          <!-- <ul class="search-brand flex-start-class">
            <li v-for="(city, cityIndex) in citys" :key="cityIndex">
              <strong>{{ getCityName(city) }}</strong>
              <ul class="search-brand flex-start-class">
                <li
                  v-for="(area, areaIndex) in getCityAreas(city)"
                  :key="areaIndex"
                  :class="{ current: currentCity === area }"
                  @click="selectCity(area)"
                >
                  {{ area }}
                </li>
              </ul>
            </li>
          </ul> -->
        </div>
        <div class="search-container search-container-border-none">
          <h2>日租价格</h2>
          <ul class="search-brand search-price flex-start-class">
            <li
              v-for="(price, index) in prices"
              :key="index"
              @click="selectPrice(index)"
            >
              <img
                :src="price.selected ? price.selectedImg : price.unselectedImg"
              />
              <span :class="{ current: price.selected }">{{
                price.label
              }}</span>
            </li>
          </ul>
        </div>
      </div>
      <div class="cars rightfix" v-for="car in paginatedCars" :key="car.carId">
        <CarItem :car="car" />
      </div>
      <addCar></addCar>
      <userCar></userCar>
      <bookCar></bookCar>
    </div>
    <Pagination
      :currentPage="currentPage"
      :totalPages="totalPages"
      @change-page="changePage"
      style="margin-bottom: 18px"
    />
  </div>
</template>

<script>
const CarItem = () => import("./CarItem.vue");
const Pagination = () => import("@/components/pagination");
const userCar = () => import("./useCar.vue");
const bookCar = () => import("./bookCar.vue");
const addCar = () => import("./addCar.vue");
import { mapState } from "vuex";

export default {
  components: {
    CarItem,
    Pagination,
    userCar,
    bookCar,
    addCar
  },
  data() {
    return {
      currentPage: 1,
      itemsPerPage: 5,
      options: [
        {
          value: "手动挡",
          label: "手动挡",
        },
        {
          value: "自动挡",
          label: "自动挡",
        },
      ],
      seatOptions: [
        {
          value: 2,
          label: 2,
        },
        {
          value: 4,
          label: 4,
        },
        {
          value: 6,
          label: 6,
        },
        {
          value: 8,
          label: 8,
        },
      ],
      searchForm: {
        carName: "",
        gears: "",
        carIdentity: "",
        startPrice: null,
        endPrice: null,
        address: "",
        carSeat: null,
      },
      brands: [
        "全部",
        "大众",
        "长安",
        "奥迪",
        "现代",
        "别克",
        "小米",
        "马自达",
        "北京汽车",
        "比亚迪",
        "奔驰",
        "宝马",
      ],
      currentBrand: "全部",
      // citys: [
      //   {
      //     成都: [
      //       "全部",
      //       "青羊区",
      //       "武侯区",
      //       "双流区",
      //       "锦江区",
      //       "成华区",
      //       "金牛区",
      //       "天府新区",
      //     ],
      //   },
      //   {
      //     重庆: [
      //       "渝中区",
      //       "江北区",
      //       "沙坪坝区",
      //       "南岸区",
      //       "九龙坡区",
      //       "大渡口区",
      //       "江津区",
      //       "渝北区",
      //     ],
      //   },
      // ],
      citys: [
        "全部",
        "四川",
        "重庆",
        "湖南",
        "甘肃",
        "山东",
        "云南",
        "北京",
        "上海",
        "广东",
        "浙江",
        "江苏",
        "广西",
        "河北",
        "湖北",
        "山西",
        "陕西",
        "吉林",
        "河南",
      ],
      currentCity: "全部",
      prices: [
        {
          selectedImg:
            "https://imgoss.wkzuche.com/web_imgs/pc/wkzuche/cartype/day-price.jpeg",
          unselectedImg:
            "https://imgoss.wkzuche.com/web_imgs/pc/wkzuche/cartype/day-price-noselected.jpeg",
          label: "不限",
          selected: true,
        },
        {
          selectedImg:
            "https://imgoss.wkzuche.com/web_imgs/pc/wkzuche/cartype/day-price.jpeg",
          unselectedImg:
            "https://imgoss.wkzuche.com/web_imgs/pc/wkzuche/cartype/day-price-noselected.jpeg",
          label: "120",
          selected: false,
        },
        {
          selectedImg:
            "https://imgoss.wkzuche.com/web_imgs/pc/wkzuche/cartype/day-price.jpeg",
          unselectedImg:
            "https://imgoss.wkzuche.com/web_imgs/pc/wkzuche/cartype/day-price-noselected.jpeg",
          label: "150",
          selected: false,
        },
        {
          selectedImg:
            "https://imgoss.wkzuche.com/web_imgs/pc/wkzuche/cartype/day-price.jpeg",
          unselectedImg:
            "https://imgoss.wkzuche.com/web_imgs/pc/wkzuche/cartype/day-price-noselected.jpeg",
          label: "180",
          selected: false,
        },
        {
          selectedImg:
            "https://imgoss.wkzuche.com/web_imgs/pc/wkzuche/cartype/day-price.jpeg",
          unselectedImg:
            "https://imgoss.wkzuche.com/web_imgs/pc/wkzuche/cartype/day-price-noselected.jpeg",
          label: "190",
          selected: false,
        },
        {
          selectedImg:
            "https://imgoss.wkzuche.com/web_imgs/pc/wkzuche/cartype/day-price.jpeg",
          unselectedImg:
            "https://imgoss.wkzuche.com/web_imgs/pc/wkzuche/cartype/day-price-noselected.jpeg",
          label: "",
          selected: false,
        },
      ],
      selectedPriceIndex: 0, // 默认选中第一个
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.cars.length / this.itemsPerPage);
    },
    paginatedCars() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.cars.slice(start, end);
    },
    ...mapState({
      cars: (state) => state.car.cars,
    }),
  },
  mounted() {
    this.$store.dispatch("allCar");
  },

  methods: {
    changePage(page) {
      this.currentPage = page;
    },
    async selectBrand(brand) {
      this.currentBrand = brand;
      if (brand === "全部") {
        this.searchForm.carName = "";
      } else {
        this.searchForm.carName = brand;
      }
      try {
        await this.$store.dispatch("carByCondition", this.searchForm);
      } catch (error) {
        this.$message.error("该品牌的汽车不存在");
      }
    },
    // getCityName(cityObject) {
    //   return Object.keys(cityObject)[0];
    // },
    // getCityAreas(cityObject) {
    //   return Object.values(cityObject)[0];
    // },
    async selectCity(area) {
      this.currentCity = area;
      if (area === "全部") {
        this.searchForm.address = "";
      } else {
        this.searchForm.address = area;
      }
      try {
        await this.$store.dispatch("carByCondition", this.searchForm);
      } catch (error) {
        this.$message.error("该城市的汽车不存在");
      }
    },
    async selectPrice(index) {
      this.prices.forEach((price, i) => {
        if (i <= index) {
          price.selected = true;
        } else {
          price.selected = false;
        }
        if (i === 0) {
          this.searchForm.startPrice = null;
          this.searchForm.endPrice = null;
        }
        if (i === index) {
          this.searchForm.startPrice = 0;
          this.searchForm.endPrice = Number(price.label);
        }
      });
      this.selectedPriceIndex = index;
      try {
        await this.$store.dispatch("carByCondition", this.searchForm);
      } catch (error) {
        this.$message.error("该价格的汽车不存在");
      }
    },
    async goSearch() {
      try {
        await this.$store.dispatch("carByCondition", this.searchForm);
      } catch (error) {
        this.$message.error("暂无符合查询条件的汽车！");
      }
    },
  },
};
</script>

<style scoped lang="less">
.container {
  width: 1300px;
  margin: 8px auto;
}
.search {
  margin: 15px auto;
  display: flex;
  justify-content: space-evenly;
  /deep/ .el-input {
    // margin-left: 18px;
    width: 280px;
  }
  .search-btn {
    width: 110px;
    height: 35px;
    border: 0;
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
}
.search-container {
  background-color: #f6f6f6;
  border-bottom: 1px solid #e4e6e9;
  display: block;
  padding: 10px 0 20px 20px;
  margin-top: 12px;
  width: 300px;
  h2 {
    font-size: 18px;
    font-weight: 500;
    margin-bottom: 10px;
    width: 190px;
  }
  .search-brand {
    display: flex;
    flex-wrap: wrap;
    list-style: none;
    padding: 0;
    margin-bottom: -8px;
    
  }

  .flex-start-class {
    align-items: center;
    display: flex;
    justify-content: flex-start;
  }
}
.brand li,
.city li {
  color: #555;
  cursor: pointer;
  font-size: 15px;
  width: 68px;
  height: 27px;
  line-height: 27px;
}
// .brand {
//   li {
//     width: 68px;
//     height: 27px;
//     line-height: 27px;
//   }
// }
// .city > ul > li {
//   height: 90px;
//   ul > li {
//     width: 75px;
//     height: 30px;
//     line-height: 30px;
//   }
// }
.search-brand li.current,
.search-brand span.current {
  color: #f23732; /* 选中的字体颜色 */
  border-color: #f23732; /* 选中的边框颜色 */
}
.search-price {
  font-size: 0;
  li {
    height: 48px;
    padding-top: 10px;
    position: relative;
    width: 49px;
    span {
      color: #2f2f39;
      cursor: pointer;
      display: inline-block;
      font-size: 14px;
      height: 27px;
      line-height: 27px;
      padding-top: 10px;
      position: absolute;
      right: -20px;
      text-align: center;
      top: 20px;
      width: 40px;
      z-index: 1;
    }
  }
}
</style>
