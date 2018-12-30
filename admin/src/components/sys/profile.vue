<style lang='less'>
</style>
<template>
    <div class="h-panel" v-margin="10" v-padding="10">
        <div class="h-panel-bar">
            <span class="h-panel-title">个人信息</span>
        </div>
        <div class="h-panel-body">
            <Form :readonly="true">
                <FormItem label="姓名">{{user.name}}</FormItem>
                <FormItem label="账号">{{user.account}}</FormItem>
                <FormItem label="角色">{{user.roleName}}</FormItem>
                <FormItem label="手机号">{{user.mobile}}</FormItem>
                <FormItem label="邮箱">{{user.email}}</FormItem>
                <FormItem label="生日">{{user.birthday}}</FormItem>
                <FormItem label="性别">{{user.sex==0?'女':'男'}}</FormItem>
            </Form>
        </div>

    </div>
</template>
<script>
  export default {
    data() {
      return {
        user: {
          name: ''
        }
      }
    },
    mounted() {
      this.init()
    },
    methods: {
      init() {
        fetch.get('/sys/user/info').then(res=>{
          this.user = res.result;
        });
      }
    },
    computed: {

    }
  }
</script>
