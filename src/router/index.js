import Vue from "vue";
import VueRouter from "vue-router";
Vue.use(VueRouter);

const Login = () => import("../views/login/index.vue");
const Register = () => import("../views/register/index.vue");
const Home = () => import("../views/home/index.vue");
const getInfo = () => import("../views/user/getInfo/index.vue");
const updateInfo = () => import("../views/user/updateInfo/index.vue");
const updatePassword = () => import("../views/user/updatePassword/index.vue");
const car = () => import("../views/car/index.vue");
const rental = () => import("../views/rental/index.vue");
const carBack = () => import("../views/carBack/index.vue");


export default new VueRouter({
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/login',
            component: Login,
        },
        {
            path: '/register',
            component: Register,
        },
        {
            path: '/home',
            component: Home,
            children: [
                {
                    path: '/user/getinfo',
                    component: getInfo,
                },
                {
                    path: '/user/updatepassword',
                    component: updatePassword,
                },
                {
                    path: '/user/updateinfo',
                    component: updateInfo,
                },
                {
                    path: '/car',
                    component: car,
                },
                {
                    path: '/rental',
                    component: rental,
                },
                {
                    path: '/carback',
                    component: carBack,
                },
            ]
        },
    ]
})
