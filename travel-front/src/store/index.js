import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    isLogin: false,
    userInfo: {}
  },
  getters: {
    getUserInfo(state) {
      if(state.userInfo === undefined || state.userInfo === null || state.userInfo === '') {
        state.userInfo = JSON.parse(localStorage.getItem('userInfo'));
      }

      if(state.userInfo.picUrl === undefined || state.userInfo.picUrl === ''
        || state.userInfo.picUrl === null) {
        state.userInfo.picUrl = 'default_head_url.jpeg';
      }

      return state.userInfo;
    },
    isLogin(state) {
      if(state.isLogin === undefined || state.isLogin === null
        || state.isLogin === '' || !state.isLogin) {
        let flag = localStorage.getItem('isLogin');
        if(flag === undefined || flag === null || flag === '') {
        } else {
          state.isLogin = flag;
        }
      }

      return state.isLogin;
    }
  },
  actions: {
    setUserInfo({commit, state}, data = {}) {
      commit("setUserInfo", data);
    },
    loginFlag({commit, state}, isLogin) {
      commit("loginFlag", isLogin);
    }
  },
  mutations: {
    setUserInfo(state, data = {}) {
      state.userInfo = data;
      localStorage.setItem('userInfo', JSON.stringify(data));
    },
    loginFlag(state, isLogin) {
      state.isLogin = isLogin;
      localStorage.setItem('isLogin', isLogin);
    }
  }
});

export default store
