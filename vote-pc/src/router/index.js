import Vue from 'vue';
import VueRouter from 'vue-router';
import Index from '@/views/index/index.vue';
// import Home from '../views/Home.vue';

Vue.use(VueRouter);

const routes = [
    {
        path: '/login',
        name: 'login',
        meta: {
            title: 'Login System',
        },
        component: () => import('@/views/login/login.vue'),
    },
    {
        path: '/',
        name: 'index',
        meta: {
            title: '主页',
        },
        component: Index,
        children: [
            {
                path: '/stat',
                name: 'stat',
                meta: {
                    title: '投票統計',
                },
                component: () => import('@/views/stat/stat.vue'),
            },
            {
                path: '/activity',
                name: 'activity',
                meta: {
                    title: '投票活動管理',
                },
                component: () => import('@/views/activity/activity.vue'),
            },
            {
                path: '/uservote',
                name: 'uservote',
                meta: {
                    title: '投票用户管理',
                },
                component: () => import('@/views/uservote/uservote.vue'),
            },
            {
                path: '/member',
                name: 'member',
                meta: {
                    title: '候选人管理',
                },
                component: () => import('@/views/member/member.vue'),
            },
        ],
    },
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
});

export default router;
