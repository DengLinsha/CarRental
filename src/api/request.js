// 对于axios进行二次封装
import axios from 'axios'
import store from '@/store'

// 1.利用axios对象的create方法创建一个axios实例
const requests = axios.create({
    // 基础路径
    baseURL: "http://localhost:8081/car",
    // 请求超时时间
    timeout: 5000,
})
// 请求拦截器，发请求之前处理一些业务
requests.interceptors.request.use((config) => {
    // // config是配置对象，里面有一个headers请求头属性
    // if (store.state.detail.uuid_token) {
    //     // 请求头添加一个字段（uuidTempId）与后台商量好
    //     config.headers.userTempId = store.state.detail.uuid_token
    // }
    // // 需要携带token带给服务器
    // if (store.state.user.token) {
    //     config.headers.token = store.state.user.token
    // }
    // nProgress.start()
    return config
})

// 响应拦截器，服务器返回数据之后处理一些业务
requests.interceptors.response.use((result) => {
    return result.data
},(error) => {
    // 服务器失败的回调函数
    // 终结Promise链
    return Promise.reject(new Error('faile'))
})

// 对外暴露
export default requests