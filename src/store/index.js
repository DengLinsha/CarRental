import Vue from "vue";
import Vuex from "vuex"

Vue.use(Vuex)

import user from "./user";
import car from './car';
import order from "./order"

export default new Vuex.Store({
    // 实现Vuex仓库模块式开发存储数据
    modules: {
        user,
        car,
        order
    }
})

