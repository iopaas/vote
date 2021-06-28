import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
// import Vant from "vant";
// import "vant/lib/index.css";
import "amfe-flexible/index.js";
import cache from "@/utils/cache";
import './permission';

Vue.config.productionTip = false;

import {
  Button,
  Cell,
  CellGroup,
  Calendar,
  Popup,
  Field,
  Picker,
  Checkbox,
  CheckboxGroup,
  Icon,
  List,
  Dialog,
  Progress,
  Badge
} from "vant";

Vue.prototype.$cache = cache;

Vue.use(Button);
Vue.use(Cell);
Vue.use(CellGroup);
Vue.use(Calendar);
Vue.use(Popup);
Vue.use(Field);
Vue.use(Picker);
Vue.use(Checkbox);
Vue.use(CheckboxGroup);
Vue.use(Icon);
Vue.use(List);
Vue.use(Dialog);
Vue.use(Progress);
Vue.use(Badge);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
