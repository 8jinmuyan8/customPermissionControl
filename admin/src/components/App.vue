<style lang="less">
  @gradient-color: #43495b;
  .login-vue {
    text-align: center;
    position: absolute;
    top: 0;
    bottom: 0;
    right: 0;
    left: 0;
    background: @gradient-color;
    .login-container {
      width: 400px;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      .login-content {
        letter-spacing: 2px;
        background: #FFF;
        padding: 120px 40px 40px;
        box-shadow: 0 0 30px 0 rgba(0, 0, 0, 0.18);
        border-radius: 3px;
        box-sizing: border-box;
        >div {
          margin: 30px 0;
          label {
            margin-right: 10px;
            line-height: 35px;
            font-size: 18px;
            color: @gray-color;
          }
          input {
            font-size: 16px;
            padding: 8px 0 8px 8px;
            height: 40px;
            width: 100%;
            border: none;
            border-radius: 0;
            border-bottom: 1px solid #d3d3d3;
            box-shadow: inset 0 0 0 1000px #fff;
            outline: none;
            box-sizing: border-box;
            transition: .3s;
            font-weight: 200;
            &:focus {
              border-bottom-color: @gradient-color;
              box-shadow: inset 0 0 0 1000px #fff;
            }
          }
          &.login-title {
            font-size: 30px;
            color: #1d1d1d;
            line-height: 1;
            margin: -16px 0px 40px;
            font-weight: 200;
          }
        }
      }
      .copyright {
        letter-spacing: 1px;
        margin-top: 10px;
        color: #FFF;
      }
    }
    .buttonDiv {
      padding-top: 18px;
      position: relative;
      svg {
        border-radius: 2px;
      }
      p {
        cursor: pointer;
        transition: .2s;
        margin: 0 auto;
        text-align: center;
        height: 48px;
        line-height: 48px;
        font-size: 19px;
        opacity: .7;
        border-radius: 40px;
        background: @gradient-color;
        color: #FFF;
        &:hover {
          opacity: 1;
        }
      }
    }
  }
</style>

<template>
  <div id="app">
    <template  >
      <div class="login" v-if="!signIn">
        <div class="login-vue" v-if="!loading">
          <div class="login-container">
            <div class="login-content">
              <div class="login-title">管理系统</div>
              <div class="login-name">
                <input type="text" v-model="data.username" placeholder="用户名" />
              </div>
              <div class="login-password">
                <input type="password" v-model="data.password" placeholder="密码" />
              </div>
              <div class="buttonDiv">
                <p @click="submit" ><i class="h-loading" v-if="loading"></i>登录</p>
              </div>
            </div>
            <p class="copyright">2018 © admin</p>
          </div>
        </div>
      </div>
      <div v-else>
        <router-view ></router-view>

      </div>
    </template>



  </div>

</template>
<script>

export default {
  data() {
    return {
      data:{username:'',password:''},
      loading:false,
    }
  },
  mounted() {
  },
  computed: {
    signIn() {
      return this.$store.state.signIn;
    }
  },
  methods: {

    submit() {
    //
    this.loading = true;
    let username = this.data.username;
    let password = this.data.password;
    if ('' == username) {
      this.$Message.error('账号不能为空');
      return;
    }
    if ('' == password) {
      this.$Message.error('密码不能为空');
      return;
    }

    let params = {
      username:username,
      password:password
    };
    fetch.post('/sys/user/signIn', params).then((res)=>{
      if ('000000' != res.code) {
        this.$Message.error(res.msg);
        this.$router.go(0);
        return;
      }
      this.password = '';
      let m = res.result;
      window.localStorage.setItem('token', m.token);
      this.$store.dispatch('getSignInStatus', true);
      this.loading = false;
      this.$router.push('/');

    });
  }
  }
}
</script>
