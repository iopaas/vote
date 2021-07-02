# vote


一.功能设计介绍

    对需求做了简单的梳理，并以用户投票为案例 ，模拟选举投票流程功能。简单的实现及介绍如下：
    详见附件-->《投票服务系统-介绍.pdf》

二.在线演示案例

    1. 管理员端(PC浏览器)： http://47.107.144.13:8008/
    2. 用户端(手机浏览器)： http://47.107.144.13:8009/
	
三.源码代码&相关文档
    由于代码分3部分，方便查看，提交到一个仓库：
    
    1. 后端源程序     ——>saas-vote
    2. 管理员端源程序 ——>vote-pc
    3. 用户端端源程序 ——>vote-h5
    4. 数据库项目设计 ——>saas-vote_20210624.sql
    5. API接口文档地址：http://47.107.144.13:8007/swagger-ui.html#/
    

说明：后端采用Java语言， 前端采用typescirpt ,ES6 ,vue.js等，由于后端功能无法呈现成果，
        特补充了前端的开发及功能效果。
	
四.本地运行说明

   4.1 后端源程序（saas-vote）
   
     a.安装mysql8.0 数据库，数据库导入脚本saas-vote_20210624.sql ，创建授权账号votedb/123456.
     b.安装redis5.0 版本，配置授权密码：123456
     c.使用Eclipse将导入的saas-vote工程，检查修改配置application.properties文件，核对数据库、redis账号及端口。邮件测试需要修改邮件服务器配置。
      Maven运行 
         VoteApplication.java
     
   4.2 PC端源程序（vote-pc）
   
     a.使用Visual Studio Code工具导入vote-pc源码。如果后端服务端口有变化，响应修改vue.config.js 文件。
       TERMAIL运行如下：
       npm install
       npm run serve
       
   4.3 H5端源程序（vote-h5）
   
     a.使用Visual Studio Code工具导入vote-pc源码。如果后端服务端口有变化，响应修改vue.config.js 文件。
       TERMAIL运行如下：
       npm install
       npm run serve
