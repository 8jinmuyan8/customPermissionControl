import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  signIn: null != window.localStorage.getItem('token'),
  showSystab: true,
}

const mutations = {
  setSignInStatus (state, signIn) {
    state.signIn = signIn
  }
}

const actions = {
  async getSignInStatus ({ commit }, signIn) {
    commit('setSignInStatus', signIn)
  }
}

export default new Vuex.Store({
  state,
  actions,
  mutations
})
