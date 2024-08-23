/*
 * @Descripttion:
 * @version:
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2022-09-24 19:27:21
 * @Author: huzhushan@126.com
 * @HomePage: https://huzhushan.gitee.io/vue3-element-admin
 * @Github: https://github.com/huzhushan/vue3-element-admin
 * @Donate: https://huzhushan.gitee.io/vue3-element-admin/donate/
 */
// home.js
const Layout = () => import('@/layout/index.vue')
const System = () => import('@/views/system/sysUser.vue')

export default [
  {
    path: '/product',
    component: Layout,
    name: 'prod',
    meta: {
      title: '产品管理',
    },
    icon: 'icon-home',
    children: [
      {
        path: '',
        name: 'system',
        component: System,
      },
    ],
  } /*,  {
    path: '/product',
    component: Layout,
    name: 'Product',
    meta: {
      title: 'product',
    },
    icon: 'icon-home',
    children: [
      {
        path: '',
        name: 'product',
        component: Product
      },
    ],
  },*/,
]
