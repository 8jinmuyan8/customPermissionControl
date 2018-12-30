import Vue from 'vue';
import App from 'components/App.vue';

import heyuiConfig from 'js/config/heyui-config';
import routerConfig from 'js/config/router-config';
import store from './js/common/store'

require('./css/app.less');

//process.env.NODE_ENV == 'development' && require('./mock')

//HeyUI已经设定为全局变量，无需引用
//设定全局变量请参考根目录下的hey.js文件


heyuiConfig();
Vue.use(HeyUI);

const router = routerConfig();

const app = new Vue({
  router,
  store,
  el: '#app',
  render: h => h(App)
});

export default app;
