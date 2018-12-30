<style lang='less'>
</style>
<template>
  <div class="h-panel" v-margin="10">
    <div class="h-panel-bar" v-margin="10">
      <span class="h-panel-title">修改密码</span>
    </div>
    <div class="h-panel-body">
      <Form v-width="400" ref="form" :label-width="150" :rules="rules" :model="data">
        <FormItem label="旧密码" prop="oldpassword">
          <input type="password" v-model="data.oldpassword"/>
        </FormItem>
        <FormItem label="新密码" prop="newpassword1">
          <input type="password" v-model="data.newpassword1"/>
        </FormItem>
        <FormItem label="确认密码" prop="newpassword2">
          <input type="password" v-model="data.newpassword2"/>
        </FormItem>
        <FormItem>
          <Button color="primary" @click="submit">提交</Button>
        </FormItem>
      </Form>
    </div>
  </div>
</template>
<script>


export default {
  data() {
    return {
      data: {
        oldpassword: null,
        newpassword1: null,
        newpassword2: null,
      },
      rules: {
        required: ['oldpassword','newpassword1','newpassword2'],
        combineRules: [{
          refs: ['newpassword1', 'newpassword2'],
          valid: {
            valid: 'equal',
            message: '两次输入的密码不一致'
          }
        }]
      }
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {

    },
    submit(){
      let validResult = this.$refs.form.valid();
      if (!validResult.result) {
        this.$Message.error(`还有${validResult.messages.length}个错误未通过验证。`);
        return;
      }

      let oldPwd = this.data.oldpassword;
      let newPwd = this.data.newpassword1;
      if (oldPwd == newPwd) {
        this.$Message.error("新密码不能和原密码一样");
        return;
      }
      if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/.exec(newPwd)) {
        this.$Message.error("密码必须为8个以上大小写加数字");
        return;
      }

      let p = {oldPwd:oldPwd, newPwd:newPwd};
      fetch.post('/sys/user/updatePassword',p).then(res=>{
        this.$Message(res.msg);
        if ('000000' == res.code) {
          window.localStorage.removeItem('token');
          this.$store.dispatch('getSignInStatus', false);
        }
      });

    }
  },
  computed: {

  }
}
</script>
