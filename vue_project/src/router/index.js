import Vue from 'vue'
import VueRouter from 'vue-router'
import Book from '../views/Book'
import AddBook from "../views/AddBook";
import UpdateBook from "../views/UpdateBook";
import Home from "../views/Home"

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '导航一',
    component: Home,
    redirect: "/book",
    children: [
      {
        path: '/book',
        name: '图书信息',
        show: true,
        component: Book
      },
      {
        path: '/addBook',
        name: '添加图书',
        show: true,
        component: AddBook
      },
      {
        path: '/updateBook',
        name: '更新图书',
        show: false,
        component: UpdateBook
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
