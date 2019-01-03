import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const initRouter = () => {
  const routerParam = {
   mode: 'history',
    routes: [ {
      path: '/',
      component: (resolve) => require(['components/app/app-frame'], resolve),
      children: [{
        path: '/',
        name: 'Home',
        component: (resolve) => require(['components/welcome/home'], resolve),
        meta: {title: '首页', icon: 'icon-monitor'}
      },{
        path: '/profile',
        name: '/profile',
        component: (resolve) => require(['components/sys/profile'], resolve),
        meta: {title: '个人信息', icon: 'h-icon-user'}
      },{
        path: '/sys/updatePassword',
        name: '/sys/updatePassword',
        component: (resolve) => require(['components/sys/updatePassword'], resolve),
        meta: {title: '更改密码', icon: 'h-icon-edit'}
      }, {
          path: '/sys/user',
          name: '/sys/user',
          component: (resolve) => require(['components/setting/sysUser'], resolve),
          meta: {title: '用户管理', icon: 'h-icon-user'}
      }, {
          path: '/sys/role',
          name: '/sys/role',
          component: (resolve) => require(['components/setting/sysRole'], resolve),
          meta: {title: '角色管理', icon: 'h-icon-users'}
      }, {
        path: '/sys/menu',
        name: '/sys/menu',
        component: (resolve) => require(['components/setting/menu'], resolve),
        meta: {title: '菜单管理', icon: 'h-icon-menu'}
      }, {
        path: '/sys/sign-log',
        name: '/sys/sign-log',
        component: (resolve) => require(['components/sys/sign-log'], resolve),
        meta: {title: '登录日志', icon: 'h-icon-task'}
      }, {
        path: '/sys/access-log',
        name: '/sys/access-log',
        component: (resolve) => require(['components/sys/access-log'], resolve),
        meta: {title: '请求日志', icon: 'h-icon-task'}
      }, {
        path: '/setting',
        name: 'setting',
        component: (resolve) => require(['components/setting/setting'], resolve),
        meta: {title: '系统设置'}
      }, {
        path: '/systemError',
        name: 'systemError',
        component: (resolve) => require(['components/error-pages/500'], resolve),
        meta: {title: '系统错误'}
      }, {
        path: '/permissionError',
        name: 'permissionError',
        component: (resolve) => require(['components/error-pages/403'], resolve),
        meta: {title: '权限错误'}
      },
      {
        path: '/notfoundError',
        name: 'notfoundError',
        component: (resolve) => require(['components/error-pages/404'], resolve),
        meta: {title: '页面找不到'}
      },
      {
        path: '*',
        component: (resolve) => require(['components/error-pages/404'], resolve),
        meta: {title: '页面找不到'}
      }]
    }]
  };

  let router = new VueRouter(routerParam);

  router.beforeEach((to, from, next) => {
    HeyUI.$LoadingBar.start();
    if (to.meta && to.meta.title) {
      document.title = to.meta.title + ' - 管理应用';
    } else {
      document.title = '管理系统';
    }
    next();
  });
  router.afterEach(() => {
    HeyUI.$LoadingBar.success();
    Vue.nextTick(() => {
      if(document.querySelector('.app-body')) {
        document.querySelector('.app-body').scrollTop = 0;
      }

    });

  });
  return router;
}

export default initRouter;
