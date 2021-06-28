import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import cache from '@/utils/cache';
import './permission';
import ECharts from 'vue-echarts';
/* 引入echarts工具 */
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';
import 'echarts/lib/component/legend';

Vue.config.productionTip = false;
Vue.prototype.$cache = cache;

Vue.use(ElementUI, { size: 'small' });

new Vue({
    router,
    store,
    render: (h) => h(App),
}).$mount('#app');
