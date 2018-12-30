<style lang="less">
.app-header {
  color: rgba(49, 58, 70, 0.8);

  .h-autocomplete {
    line-height: 1.5;
    float: left;
    margin-top: 15px;
    margin-right: 20px;
    width: 120px;
    &-show,&-show:hover, &-show.focusing {
      outline: none;
      box-shadow: none;
      border-color: transparent;
      border-radius: 0;
    }
    &-show.focusing{
      border-bottom: 1px solid #eee;
    }
  }
  &-info &-icon-item {
    cursor: pointer;
    display: inline-block;
    float: left;
    padding: 0 15px;
    margin-right: 10px;
    &:hover {
      background: @hover-background-color;
    }
    i {
      font-size: 18px;
    }
    a {
      color: inherit;
    }
  }

  &-dropdown{
    float: right;
    padding: 0 20px 0 15px;
    .h-icon-down {
      right: 20px;
    }
    cursor: pointer;
    &:hover, &.h-pop-trigger {
      background: @hover-background-color;
    }
    &-dropdown {
      padding: 5px 0;
      .h-dropdownmenu-item {
        padding: 8px 20px;
      }
    }
  }

  &-menus{
    display: inline-block;
    vertical-align: top;
    >div {
      display: inline-block;
      font-size: 15px;
      padding: 0 25px;
      color: @dark-color;
      &:hover{
        color: @primary-color;
      }
      +div {
        margin-left: 5px;
      }
      &.h-tab-selected{
        color: @white-color;
        background-color: @primary-color;
      }
    }
  }
}
</style>

<template>
  <div class="app-header">
    <div class="float-right app-header-info">
      <AutoComplete v-model="searchText" config="globalSearch" placeholder="全局搜索.."></AutoComplete>
      <div class="app-header-icon-item">
        <Badge :count="2"><i class="h-icon-bell"></i></Badge>
      </div>

      <DropdownMenu className="app-header-dropdown" trigger="hover" offset="0 5" :width="150" placement="bottom-end" :datas="infoMenu" @onclick="trigger"><span>{{name}}</span></DropdownMenu>
    </div>
  </div>
</template>
<script>

export default {
  data() {
    return {
      searchText: '',
      name:'',
      infoMenu: [
        { key: 'info', title: '个人信息', icon: 'h-icon-user' },
        { key: 'updatePassword', title: '修改密码', icon: 'h-icon-edit' },
        { key: 'logout', title: '退出登录', icon: 'h-icon-outbox' },
      ]
    };
  },
  computed: {

  },
  methods: {
    trigger(data) {
      if (data == 'logout') {
        fetch.get('/sys/user/signOut').then(res=>{
          this.$Message(res.msg);
          if ('000000' == res.code) {
            window.localStorage.removeItem('token');
            this.$store.dispatch('getSignInStatus', false);
          }
        });
      } else if (data == 'info') {
        this.$router.push('/profile')
      } else if (data == 'updatePassword') {
        this.$router.push('/sys/updatePassword')
      }
    }
  },created(){
    fetch.get('/sys/user/info').then(res=>{
      if ('000000' == res.code) {
        this.name = res.result.account
      }
    });
  }
};
</script>
