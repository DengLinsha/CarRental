import {orderInfo, createOrder, searchBackOrder} from "@/api"

const state = {
    order: [],
    backorder: []
};

const mutations = {
    GETORDERINFO(state, order) {
        state.order = order
    },
    SEARCHORDER(state, order) {
        state.order = order
    },
    BACKORDER(state, backorder) {
        state.backorder =backorder
    }
};

const actions = {
    async getOrderInfo({commit}, username) {
        let result = await orderInfo(username)
        if (result.code == 200) {
            commit('GETORDERINFO', result.data)
            return 'ok'
        } else {
            return Promise.reject(new Error('faile'));
        }
    },
    async createOrder({commit}, params) {
        let result = await createOrder(params)
        if (result.code == 200) {
            return 'ok'
        } else {
            return Promise.reject(new Error('faile'));
        }
    },
    async BackOrder({commit}, userId) {
        let result = await searchBackOrder(userId)
        if (result.code == 200) {
            commit('BACKORDER', result.data)
            return 'ok'
        } else {
            return Promise.reject(new Error('faile'));
        }
    }
};

const getters = {

};

export default {
    state,
    mutations,
    actions,
    getters
}