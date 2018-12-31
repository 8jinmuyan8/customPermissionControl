module.exports = {
  port: 9012,
  root: 'dist',
  webpack: {
    console: true,
    publicPath: '/',
    output: {
      './index.html': {
        entry: './src/app',
        commons: ['common']
      }
    },
    commonTrunk: {
      common: [
        'manba',
        './src/js/common/utils',
        'hey-global',
        'hey-log',
        'heyui',
        './src/js/common/fetch'
      ]
    },
    alias: {
      js: './src/js/',
      components: './src/components/',
    },
    global: {
      Utils: './src/js/common/utils',
      Manba: 'manba',
      HeyUI: 'heyui',
      G: 'hey-global',
      log: 'hey-log',
      fetch:'./src/js/common/fetch'
    },
    devServer: {
      proxy: {
       // 此处应该配置为开发服务器的后台地址
        '/sys/': {
          target: 'http://localhost:8080'
        }
      },
      historyApiFallback: true
    },
    globalVars: './src/css/var.less',
    externals: {}
  },
  copy: ['static/*']
};
