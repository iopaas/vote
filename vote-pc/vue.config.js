const CompressionWebpackPlugin = require('compression-webpack-plugin');
const productionGzipExtensions = ['js', 'css'];
const TerserPlugin = require('terser-webpack-plugin');
const isProduction = process.env.NODE_ENV === 'production';
module.exports = {
    productionSourceMap: false, //是否生成map文件,
    //externals: {}, //配置引入某些库但不让webpack打包
    publicPath: process.env.NODE_ENV === 'production' ? './' : '/', //打包时区分开发环境与生产环境静态资源路径
    outputDir: process.env.outputDir, //输出到配置指定的目录
    configureWebpack: (config) => {
        if (isProduction) {
            config.plugins.push(
                new CompressionWebpackPlugin({
                    algorithm: 'gzip',
                    test: new RegExp('\\.(' + productionGzipExtensions.join('|') + ')$'),
                    threshold: 10240,
                    minRatio: 0.8,
                })
            );

            config.plugins.push(
                new TerserPlugin({
                    terserOptions: {
                        ecma: undefined,
                        warnings: false,
                        parse: {},
                        compress: {
                            drop_console: true,
                            drop_debugger: false, //是否删除debugger
                            pure_funcs: ['console.log'], // 移除console
                        },
                    },
                })
            );
        } else {
            config.devtool = 'source-map';
        }
    },
    devServer: {
        // 配置代理
        proxy: {
            //代理接口前缀为/api
            '/api': {
                target: 'http://127.0.0.1:8007',
                ws: true,
                changOrigin: true, //是否跨域
                pathRewrite: {
                    '^/api': '/api', //重写路径
                },
            },
        },
    },
};
