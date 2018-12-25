<style lang='less'>
</style>
<template>
  <div class="app-frame">
    <template v-if="!loading">
      <appLogo></appLogo>
      <appHead></appHead>
      <appMenu></appMenu>
      <SysTabs v-if="showSystab" homePage="Home"></SysTabs>
      <div class="app-body">
        <!-- <keep-alive> -->
          <router-view></router-view>
        <!-- </keep-alive> -->
      </div>
    </template>
  </div>
</template>
<script>

import appHead from './app-header';
import appMenu from './app-menu';
import appLogo from './app-logo';
import SysTabs from '../common/SysTabs/SysTabs';
import { mapState } from "vuex";

export default {
  data() {
    return {
      loading: false
    };
  },
  mounted() {

  },
  methods: {

  },
  computed: {
    ...mapState(['showSystab'])
  },
  components: {
    appHead,
    appMenu,
    appLogo,
    SysTabs
  }
};
</script>
