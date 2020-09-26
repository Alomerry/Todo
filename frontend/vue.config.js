module.exports = {
  configureWebpack: config => {
    config.devServer = {
      proxy: {
        '/api': {
          target: 'http://localhost:8081/',
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