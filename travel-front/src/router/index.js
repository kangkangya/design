import Vue from 'vue'
import Router from 'vue-router'
import Index from '../view/Index'
import ScenicSpoit from '../view/ScenicSpoit'
import Hotel from '../view/Hotel'
import GoWith from '../view/GoWith'
import Food from '../view/Food'
import TravelNote from '../view/TravelNote'
import ScenicSpoitDetail from '../view/ScenicSpoitDetail'
import HotelDetail from '../view/HotelDetail'
import FoodDetail from '../view/FoodDetail'
import TravelNoteDetail from '../view/TravelNoteDetail'
import GoWithPlan from '../view/GoWithPlan'
import GoWithDetail from '../view/GoWithDetail'
import Login from '../view/Login'
import Register from '../view/Register'
import ForgetPassword from '../components/forget_password'
import NewPassword from '../view/NewPassword'
import Personal from '../view/Personal'
import WriteNotes from '../view/WriteNotes'
import Order from '../view/Order'
import PictureList from "../components/picture_list";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/scenic-spoit',
      name: 'ScenicSpoit',
      component: ScenicSpoit
    },
    {
      path: '/hotel',
      name: 'Hotel',
      component: Hotel
    },
    {
      path: '/go-with',
      name: 'GoWith',
      component: GoWith
    },
    {
      path: '/food',
      name: 'Food',
      component: Food
    },
    {
      path: '/travel-note',
      name: 'TravelNote',
      component: TravelNote
    },
    {
      path: '/scenic-spoit-detail',
      name: 'ScenicSpoitDetail',
      component: ScenicSpoitDetail
    },
    {
      path: '/hotel-detail',
      name: 'HotelDetail',
      component: HotelDetail
    },
    {
      path: '/food-detail',
      name: 'FoodDetail',
      component: FoodDetail
    },
    {
      path: '/travel-note-detail',
      name: 'TravelNoteDetail',
      component: TravelNoteDetail
    },
    {
      path: '/go-with-plan',
      name: 'GoWithPlan',
      component: GoWithPlan
    },
    {
      path: '/go-with-detail',
      name: 'GoWithDetail',
      component: GoWithDetail
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/forget-password',
      name: 'ForgetPassword',
      component: ForgetPassword
    },
    {
      path: '/new-password',
      name: 'NewPassword',
      component: NewPassword
    },
    {
      path: '/personal',
      name: 'Personal',
      component: Personal
    },
    {
      path: '/write-notes',
      name: 'WriteNotes',
      component: WriteNotes
    },
    {
      path: '/order',
      name: 'Order',
      component: Order
    },
    {
      path: '/picture-list',
      name: 'PictureList',
      component: PictureList
    }
  ]
})
