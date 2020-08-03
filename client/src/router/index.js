import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import BoardDetail from '../components/PostDetail.vue'
import input from '../components/Input.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', name: 'home', component: Home},
    {path: '/blah', name: 'about', component: () => import(/* webpackChunkName: "about" */ '../views/Blah.vue')},
    {path: '/post/:id', name: 'detail', component: BoardDetail, props: true},
    {path: '/input', name: 'input', component: input},
    {path: '/about/input', name: 'input', component: input},
  ]
})
