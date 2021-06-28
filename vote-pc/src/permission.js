import router from './router';
import { getToken, removeToken, getAccount } from '@/utils/auth';

router.beforeEach((to, from, next) => {
    if (to.name === 'login') {
        removeToken();
        next();
        return false;
    }
    if (getToken() && getAccount()) {
        if (to.path === '/') {
            next({
                path: '/member',
            });
        } else {
            next();
        }
    } else {
        removeToken();
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
