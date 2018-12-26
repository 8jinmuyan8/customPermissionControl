<style lang="less">

@menu-color: #FFF;
.app-menu{
  background: @menu-color;

  .h-menu-white{
    font-size: 14px;
    color: rgba(49, 58, 70, 0.8);
    .h-menu-li-selected{
      background-color: rgb(240, 246, 255);
      .h-menu-show:after {
        width: 4px;
      }
    }
    > li >.h-menu-show {
      font-size: 15px;
      .h-menu-show-icon {
        font-size: 20px;
      }
    }
    .h-menu-ul{
      background: rgba(255, 255, 255, 0.07);
    }
    .h-menu-li > ul > li > div {
      padding-left: 54px;
    }
  }
}

</style>
<template>
  <div class="app-menu">
    <Menu :datas="menus" @onclick="trigger" ref='menu' class-name="h-menu-white"></Menu>
  </div>
</template>
<script>

import menuConfig from '../../js/config/menu-config';

export default {
  data() {
    return {
      menus: [],
      select: ''
    }
  },
  watch: {
    $route() {
      this.menuSelect();
    }
  },
  mounted() {
    this.init();
    this.menuSelect();
  },
  methods: {
    init(){
      fetch.get('/sys/user/menu').then(res=>{
        if ('000000' == res.code) {
          let list = res.result;
          this.buildMenus(list);
        }
      })
    },
  buildMenus(list) {
    let menu = [];
    for (let i in list) {
      let item = list[i];
      if (null == item.parentId) {
        item.key = item.path;
        item.title = item.name;
        item.children = [];
        this.setChildren(item.id, item.children, list);
        menu.push(item);
      }
    }
    this.menus = menu;
  },
    setChildren(parentId, children, list) {
      for (let i in list) {
        let item = list[i];
        if (parentId == item.parentId) {
          item.key = item.path;
          item.title = item.name;
          item.children = [];
          this.setChildren(item.id, item.children, list);
          children.push(item);
        }
      }
    },
    menuSelect() {
      if (this.$route.name) {
        this.$refs.menu.select(this.$route.name);
      }
    },
    trigger(data) {
      this.$router.push({ name: data.key });
    }
  }
}
</script>
