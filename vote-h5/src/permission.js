import router from './router';
import {  getToken, setRouterParams } from '@/utils/cache';

router.beforeEach((to, from, next) => {
    if (to.name === 'login'|| to.name === 'home' || to.name === 'query') {
        next();
        return false;
    }
    if (getToken()) {
        if (to.path === '/') {
            next({
                path: '/query',
            });
        } else {
            next();
        }
    } else {
        setRouterParams(to);
        next({
            path: '/login',
        });
    }
});

router.afterEach((to) => {
    /* 路由发生变化修改页面title */
    if (to.meta.title) {
        document.title = to.meta.title;
    }
});
