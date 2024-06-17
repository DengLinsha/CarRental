import {getAllCar, getCarByName, getCarByCondition, carBack, addCar} from "@/api";
const state = {
    cars:[],
    carsByName: []
};

const mutations = {
    ALLCAR(state, cars) {
        state.cars = cars
    },
    CARBYNAME(state, carsByName) {
        state.cars = carsByName
    },
    CARBYCONDITION(state, carByCondion) {
        state.cars = carByCondion
    }
};

const actions = {
    async allCar({commit}) {
        let result = await getAllCar()
        if (result.code == 200) {
            commit('ALLCAR', result.data)
            return 'ok'
        } else {
            return Promise.reject(new Error('faile'));
        }
    },
    async carByName({commit}, carName) {
        let result = await getCarByName(carName)
        if (result.code == 200) {
            commit("CARBYNAME", result.data) 
            return 'ok'
        } else {
            return Promise.reject(new Error('faile'));
        }
    },
    async carByCondition({commit}, conditions) {
        let result = await getCarByCondition(conditions)
        if (result.code == 200) {
            commit("CARBYCONDITION", result.data) 
            return 'ok'
        } else {
            return Promise.reject(new Error('faile'));
        }
    },
    async carBack({commit}, params) {
        let result = await carBack(params)
        if (result.code == 200) {
            return 'ok'
        } else {
            return Promise.reject(new Error('faile'));
        }
    },
    async carAdd({commit}, params) {
        let result = await addCar(params)
        if (result.code == 200) {
            return 'ok'
        } else {
            return Promise.reject(new Error('faile'));
        }
    },
};

const getters = {

};

export default {
    state,
    mutations,
    actions,
    getters
}
