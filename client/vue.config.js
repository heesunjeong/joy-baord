module.exports = {
    productionSourceMap: false,
    devServer: {
        proxy: { // proxyTable 설정
            '^/api': {
                target: 'http://localhost:8080/',
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}
