const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/car': {
        target: 'http://10.201.218.218:9999',
        changeOrigin: true,
        timeout: 60000, // 设置超时时间为 60 秒
      }
    }
  }
})
