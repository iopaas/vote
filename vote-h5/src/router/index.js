import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'home',
        meta: {
            title: '投票入口',
        },
        component: () => import('@/views/home/home.vue'),
    },
    {
        path: '/login',
        name: 'login',
        meta: {
            title: '用户登记',
        },
        component: () => import('@/views/login/user-login.vue'),
    },
    {
        path: '/query',
        name: 'query',
        meta: {
            title: '投票查询',
        },
        component: () => import('@/views/query/query.vue'),
    },
    {
        path: '/query-details',
        name: 'query-details',
        meta: {
            title: '投票详情',
        },
        component: () => import('@/views/query/query-details.vue'),
    },
    {
        path: '/analysis',
        name: 'analysis',
        meta: {
            title: '数据统计',
        },
        component: () => import('@/views/analysis/index.vue'),
    },
 
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
});

router.afterEach((to) => {
    /* 路由发生变化修改页面title */
    if (to.meta.title) {
        document.title = to.meta.title;
    }
});

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}


export default router;
