// 统一接口管理
import requests from "./request";

// 忘记密码
export const backPassword = (username) => {
    // 发请求--返回的是Promise对象
    return requests({
        url: `/user/backPassword?username=${username}`,
        method: 'get',
    })
}

// 登录
export const loginUser = (data) => {
    return requests({
        url: `/user/login?username=${data.username}&password=${data.password}`,
        method: 'get',
    })
}

// 注册
export const registerUser = (params) => {
    console.log(params);
    return requests({
        url: `/user/register`,
        method: 'post',
        data: params
    })
}

// 注销用户
export const deleteUser = (username) => {
    return requests({
        url: `/user/deleteUser?username=${username}`,
        method: 'get',
    })
}

// 个人信息
export const userInfo = (username) => {
    return requests({
        url: `/user/getAll?username=${username}`,
        method: 'get'
    })
}

// 修改密码
export const updatePassword = (params) => {
    return requests({
        url: '/user/modifyUserPassWord',
        method: 'post',
        data: params
    })
}

// 修改个人信息
export const updateInfo = (params) => {
    return requests({
        url: '/user/modifyUserMSG',
        method: 'post',
        data: params
    })
}

// 修改图像
export const updatePicurl = (params) => {
    return requests({
        url: '/user/upload',
        method: 'post',
        data: params
    })
}

// 获取车辆信息
export const getAllCar = () => {
    return requests({
        url: '/car/getAll',
        method: 'get'
    })
}

// 根据车名（品牌）获取车辆信息
export const getCarByName = (carName) => {
    return requests({
        url: `/car/getCarByBrandName?carName=${carName}`,
        method: 'get'
    })
}

// 根据条件获取车辆信息
export const getCarByCondition = (params) => {
    return requests({
        url: '/car/getCarByConditions',
        method:'post',
        data: params
    })
}

// 获取用户租赁订单信息
export const orderInfo = (username) => {
    return requests({
        url: `/order/getOrderByUsername?username=${username}`,
        method: 'get'
    })
}

// 立即支付
export const createOrder = (params) => {
    return requests({
        url: '/order/creatOrder',
        method:'post',
        data: params
    })
}

// 还车
export const carBack = (params) => {
    console.log(params);
    return requests({
        url: `/order/backCar`,
        method: 'post',
        data: params
    })
}

// 加入购物车
export const addCar = (params) => {
    return requests({
        url: `/order/addtOrder`,
        method: 'post',
        data: params
    })
}

// 根据条件搜索租赁订单
export const searchOrder = (userId, carIdentity, carName) => {
    return requests({
        url: `/order/getOrderByCondition?userId=${userId}&carIdentity=${carIdentity}&carName=${carName}`,
        method:'get',
    })
}

// 还车订单接口
export const searchBackOrder = (userId) => {
    return requests({
        url: `/order/getBackOrderByUserId?userId=${userId}`,
        method:'get',
    })
}

// // 获取banner（Home首页轮播图接口）
// export const getBannerList = () => {
//    return mockRequests.get('/banners')
// }

// // 获取floor数据
// export const getFloorList = () => {
//     return mockRequests.get('/floors')
// }

// // 获取搜索模块数据--带参
// export const getSearchInfo = (params) => {
//     // params至少应该为一个空对象
//     return requests({
//         url: '/list',
//         method: 'post',
//         data: params
//     })
// }

// export const getGoodsInfo = (skuId) => {
//     return requests({
//         url: `/item/${ skuId }`,
//         method: 'get',

//     })
// }

// // 将产品添加到购物车中（以及更新某一个产品的个数）
// export const postAddOrUpdateShopCart = (skuId, skuNum) => {
//     return requests({
//         url: `/cart/addToCart/${ skuId }/${ skuNum }`,
//         method: 'post',

//     })
// }

// // 获取购物车列表
// export const getCartList = () => {
//     return requests({
//         url: '/cart/cartList',
//         method: 'get'
//     })
// }

// // 删除购物车商品
// export const deleteCart = (skuId) => {
//     return requests({
//         url: `cart/deleteCart/${skuId}`,
//         method: 'delete'
//     })
// }

// // 修改商品选中状态
// export const updateChecked = (skuId, isChecked) => {
//     return requests({
//         url: `cart/checkCart/${skuId}/${isChecked}`,
//         method: 'get'
//     })
// }

// // 获取验证码
// export const getCode = (phone) => {
//     return requests({
//         url: `/user/passport/sendCode/${phone}`,
//         method: 'get'
//     })
// }

// // 注册
// export const postRegister = (data) => {
//     return requests({
//         url: `/user/passport/register`,
//         method: 'post',
//         data
//     })
// }

// // 登录
// export const postLogin = (data) => {
//     return requests({
//         url: `/user/passport/login`,
//         method: 'post',
//         data
//     })
// }

// // 获取登录后的用户信息---需要带着token向服务器要用户信息
// export const getUserInfo = () => {
//     return requests({
//         url: `/user/passport/auth/getUserInfo`,
//         method: 'get'
//     })
// }

// // 退出登录
// export const logout = () => {
//     return requests({
//         url: '/user/passport/logout',
//         method: 'get'
//     })
// }

// // 获取用户地址信息
// export const getAddressInfo = () => {
//     return requests({
//         url: '/user/userAddress/auth/findUserAddressList',
//         method: 'get'
//     })
// }

// // 获取商品清单
// export const getOrderInfo = () => {
//     return requests({
//         url: '/order/auth/trade',
//         method: 'get'
//     })
// }

// // 提交订单
// export const postSubmitOrder = (tradeNo, data) => {
//     return requests({
//         url: `/order/auth/submitOrder?tradeNo=${tradeNo}`,
//         data,
//         method: 'post'
//     })
// }
// // 获取订单信息
// export const getPayInfo = (orderId) => {
//     return requests({
//         url: `/payment/weixin/createNative/${orderId}`,
//         method: 'get'
//     })
// }
// // 获取支付状态信息
// export const getQueryPayStatus= (orderId) => {
//     return requests({
//         url: `/payment/weixin/queryPayStatus/${orderId}`,
//         method: 'get'
//     })
// }
// // 获取订单列表
// export const getOrderList = (page, limit) => {
//     return requests({
//         url: `/order/auth/${page}/${limit}`,
//         method: 'get'
//     })
// }