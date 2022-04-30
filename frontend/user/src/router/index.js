import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

export default new Router({
  routes: [
    {
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
  ]
})
