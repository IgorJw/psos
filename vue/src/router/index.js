import { createRouter, createWebHistory } from 'vue-router'
import LoginPanel from '../components/Login/LoginPanel.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/login', component: LoginPanel },
  ],
})

router.beforeEach(async (to, ) => {
  if (
    // make sure the user is authenticated
    !isAuthenticated &&
    // ❗️ Avoid an infinite redirect
    to.name !== 'Login'
  ) {
    // redirect the user to the login page
    return { name: 'Login' }
  }
})