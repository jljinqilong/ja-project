# ja-project
晶澳太阳能项目



晶澳太阳能管理系统 ：采普数据系统公司

前后端分离 前端reactJs, 后端springboot,springcloud

项目部署：

    需要环境： 
        ja-web 安装nodeJs， npm install(或者使用阿里镜像 cnpm install) 生成 node_modules 目录
        后端模块 jdk 1.8  
        数据库 mysql
        nginx 反向代理 nginx.conf 见 deploy 目录
        redis 配置 端口默认 6379
        
    前端 根据package.json  npm run build 打包后生成 dist 静态文件，然后将dist放到服务器目录，修改nginx.conf静态文件映射(这样就可以通过nginx访问到前端打包的静态文件)。
    后端 依次启动 
        ja-discovery(注册中心Eureka模块) 
        ja-config(配置服务模块,该项目下resources/configs 配置了 dev, test, prd 等环境配置，配置内容是mysql连接url，redis[缓存]，fdfs[文件服务], activemq[消息中间件])
        ja-gateway(网关模块)
        ja-system(系统服务模块)
        ja-staff(员工管理模块)
        ja-org(组织架构模块) 
        ja-emolument(薪酬管理模块)
        ja-attendance(人员考勤模块)
        ja-sale(销售管理模块)
        
        备注： ja-discovery,ja-config,ja-gateway 必须要按照顺序依次启动，其他模块没有优先级
        
    项目请求流程： 前端请求先经过nginx(负载均衡)转发到ja-gateway, ja-gateway(网关模块)将请求分发给具体的模块
        
        
