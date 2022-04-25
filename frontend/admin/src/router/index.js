import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path:'/',
    redirect:'/login'
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/dashboard',
    component: Layout,
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: {
        title: '主页概览',
        icon: 'home',
        affix: true
      }
    }]
  },
  {
    path: '/user',
    component: Layout,
    children: [{
      path: 'index',
      name: 'user',
      component: () => import('@/views/user/index'),
      meta: {
        title: '学生列表',
        icon: 'users',
        nocache: true
      }
    }]
  },
  {
    path: '/class',
    component: Layout,
    redirect: '/class/list',
    name: 'class',
    meta: {
      title: '班级管理',
      icon: 'tree',
    },
    children: [{
      path: 'list',
      name: 'classList',
      component: () => import('@/views/class/list/index'),
      meta: {
        title: '班级列表',
        noCache: true
      }
    }, {
      path: 'create',
      name: 'classCreate',
      component: () => import('@/views/class/create/index'),
      meta: {
        title: '班级创建',
        noCache: true
      },
    }, {
      path: 'edit',
      name: "classEdit",
      component: () => import('@/views/class/edit/index'),
      meta: {
        title: '班级编辑',
        noCache: true
      },
      hidden: true
    }]
  },
  {
    path: '/exam',
    component: Layout,
    redirect: '/exam/list',
    name: 'exam',
    meta: {
      title: '试卷管理',
      icon: 'form',
    },
    children: [{
      path: 'list',
      name: 'examList',
      component: () => import('@/views/exam/list/index'),
      meta: {
        title: '试卷列表',
        noCache: true
      }
    }, {
      path: 'create',
      name: 'examCreate',
      component: () => import('@/views/exam/create/index'),
      meta: {
        title: '试卷创编',
        noCache: true
      }
    }, {
      path: 'analyze',
      name: 'analyze',
      component: () => import('@/views/exam/analyze/index'),
      hidden: true,
      meta: {
        title: '试卷分析',
        noCache: true
      }
    }]
  },
  {
    path: '/question',
    component: Layout,
    redirect: '/question/list',
    name: 'question',
    meta: {
      title: '题库管理',
      icon: 'exam'
    },
    children: [{
        path: 'list',
        component: () => import('@/views/question/list/index'),
        name: 'questionList',
        meta: {
          title: '题目列表',
          noCache: true
        },
      },
      {
        path: 'singleChoice',
        name: 'singleChoice',
        component: () => import("@/views/question/singleChoice/index"),
        name: 'singleChoice',
        meta: {
          title: '单选题创编',
          noCache: true
        },
      },
      {
        path: 'multipleChoice',
        name: 'multipleChoice',
        component: () => import("@/views/question/multipleChoice/index"),
        meta: {
          title: '多选题创编',
          noCache: true
        },
      },
      {
        path: 'trueFalse',
        name: 'trueFalse',
        component: () => import("@/views/question/trueFalse"),
        meta: {
          title: '判断题创编',
          noCache: true
        },
      }, {
        path: 'shortAnswer',
        name: 'shortAnswer',
        component: () => import("@/views/question/shortAnswer"),
        meta: {
          title: '简答题创编',
          noCache: true
        }
      }, {
        path: 'sort',
        name: 'sort',
        component: () => import("@/views/question/sort"),
        meta: {
          title: '排序题创编',
          noCache: true
        }
      },

    ]

  },
  {
    path: '/answerpaper',
    component: Layout,
    redirect: '/answerpaper/examCorrect',
    name: 'answerpaper',
    meta: {
      title: '答卷管理',
      icon: 'edit'
    },
    children: [{
        path: 'screenShot',
        name: 'screenShot',
        component: () => import("@/views/answerpaper/screenShot/"),
        hidden: true,
        meta: {
          title: '考试抓拍',
          noCache: true
        }
      },
      {
        path: 'examCorrect',
        name: 'examCorrect',
        component: () => import("@/views/answerpaper/examCorrect/list"),
        meta: {
          title: '批改列表',
          noCache: true
        }
      }, {
        path: 'resultList',
        name: 'resultList',
        component: () => import("@/views/answerpaper/resultList/list"),
        meta: {
          title: '试卷完成',
          noCache: true
        }
      }, {
        path: 'check',
        name: 'check',
        hidden: true,
        component: () => import("@/views/answerpaper/check"),
        meta: {
          title: '试卷审阅',
          noCache: true,
        }
      }
    ]
  },
  // {
  //   path: '/example',
  //   component: Layout,
  //   redirect: '/example/table',
  //   name: 'Example',
  //   meta: { title: 'Example', icon: 'el-icon-s-help' },
  //   children: [
  //     {
  //       path: 'table',
  //       name: 'Table',
  //       component: () => import('@/views/table/index'),
  //       meta: { title: 'Table', icon: 'table' }
  //     },
  //     {
  //       path: 'tree',
  //       name: 'Tree',
  //       component: () => import('@/views/tree/index'),
  //       meta: { title: 'Tree', icon: 'tree' }
  //     }
  //   ]
  // },

  // {
  //   path: '/form',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'Form',
  //       component: () => import('@/views/form/index'),
  //       meta: { title: 'Form', icon: 'form' }
  //     }
  //   ]
  // },

  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'Nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: () => import('@/views/nested/menu1/index'), // Parent router-view
  //       name: 'Menu1',
  //       meta: { title: 'Menu1' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       component: () => import('@/views/nested/menu2/index'),
  //       name: 'Menu2',
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },


  // 404 page must be placed at the end !!!
  {
    path: '*',
    redirect: '/404',
    hidden: true
  }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({
    y: 0
  }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
