import { backPassword, updatePassword, userInfo, deleteUser, loginUser, registerUser, updateInfo } from "@/api";

const state = {
    loginData: {},
    findUserPassword: '',
    userinfo: {}
};

const mutations = {
    LOGIN(state, loginData) {
        state.loginData = loginData
    },
    GETPASSWORD(state, findUserPassword) {
        state.findUserPassword = findUserPassword
    },
    GETUSERINFO(state, userinfo) {
        state.userinfo = userinfo
    }
};

const actions = {
    async login({commit}, loginData) {
        let result = await loginUser(loginData)
        if (result.code == 200) {
            commit('LOGIN', loginData)
            return 'ok'
        } else {
            return Promise.reject(new Error('faile'));
        }
    },
    async register({commit}, params={}) {
        let result = await registerUser(params)
        if (result.code == 200) {
            return 'ok'
        } else {
            return Promise.reject(new Error('faile'));
        }
    },
    async getPassword({commit}, findUsername) {
        let result = await backPassword(findUsername)
        if (result.code == 200) {
            commit('GETPASSWORD', result.data)
            return 'ok'
        } else {
            return Promise.reject(new Error('faile'));
        }
    },
    async updatePassword({commit}, params={}) {
        let result = await updatePassword(params)
        if (result.code == 200) {
            return 'ok'
        } else {
            return Promise.reject(new Error('faile'));
        }
    },
    async getUserInfo({commit}, username) {
        let result = await userInfo(username);
        if (result.code == 200) {
            commit('GETUSERINFO', result.data)
        }
    },
    async deleteUser({commit}, username) {
        let result = await deleteUser(username);
        if (result.code == 200) {
            return 'ok'
        } else {
            return Promise.reject(new Error('faile'));
        }
    },
    async updateUserInfo({commit}, params={}) {
        let result = await updateInfo(params)
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
    getters,
};