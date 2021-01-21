module.exports = {
  configureWebpack: config => {
    config.devServer = {
      proxy: {
        '/api': {
          target: 'http://localhost:8088/v1',
          changeOrigin: true,
          ws: true,
          pathRewrite: {
            '^/api': ''
          }
        }
      }
    }
  }
}