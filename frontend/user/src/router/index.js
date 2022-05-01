import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

export default new Router({
  routes: [{
      path: '/',
      redirect: '/welcome/sign'
    },
    {
      path: '/welcome',
      redirect: '/sign',
      component: () => import('@/views/welcome/index.vue'),
      children: [{
          path: 'sign',
          name: 'sign',
          component: () => import('@/views/welcome/sign.vue'),
        },
        {
          path: 'account',
          name: 'account',
          component: () => import("@/views/welcome/account.vue")
        }
      ]
    },
    {
      path: '/homepage',
      name: 'homepage',
      redirect: '/homepage/recruitList',
      component: () => import('@/views/homepage/index.vue'),
      children: [{
          path: 'recruitList',
          name: 'recruitList',
          component: () => import("@/views/recruitList/index.vue")
        }, {
          path: 'account',
          name: 'myaccount',
          component: () => import("@/views/account/index.vue")
        }, {
          path: 'message',
          name: 'message',
          component: () => import("@/views/message/index.vue")
        }, {
          path: 'recruitEdit',
          name: 'recruitEdit',
          component: () => import("@/views/recruitEdit/index.vue")
        },
        {
          path: 'session',
          name: 'session',
          component: () => import("@/views/session/index.vue"),
        }, {
          path: 'history',
          name: 'history',
          component: () => import("@/views/history/index.vue"),
        },
        {
          path: 'recruitContent',
          name: 'recruitContent',
          component: () => import("@/views/recruitContent/index.vue"),
        }

      ]
    },
  ]
})
