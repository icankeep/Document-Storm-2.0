## Document Storm 2.0
Document Storm 重构中...

提供文档转换，文档翻译的一个网站。

目前支持：
1. Word转PDF
1. PDF转Word
1. Markdown转HTML
1. Markdown转PDF
1. HTML转PDF
1. Word格式的英文文档翻译

## 重构后
采用技术：
- 后端服务器基于Java语言，使用Spring Boot、MyBatis等框架，前端基于Bootstrap、Thymeleaf
- 采用ActiveMQ消息队列发送文件操作的相关消息，由文件转换的核心cpu处理器集群处理，异步进行文件转换操作
- 使用Redis共享Session实现SSO单点登录
- 网站还有显示网易云热门评论的小功能
- 使用Docker容器进行部署项目多个模块，每个模块镜像化，使用Swarm和Compose进行编排管理集群
    - MVC模块 --> 依赖Common模块
    - CPU模块 --> 依赖Common模块
    - Redis模块
    - MySQL模块
    - ActiveMQ模块 

## 还需要哪些优化？
- 文档转换方面的相关优化，满足更多格式，转换后效果更佳
- 添加讨论区或者问题专区
- 文件定时过期
- 文档格式的性能优化，对转换时间进行优化(采用分页然后多个任务进行转换？)

## 项目部署
待优化之后写全...



