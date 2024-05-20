# 🚀 Spring Boot 万用模板 🚀

**作者**: [XiaoZhangCode](https://github.com/XiaoZhangCode) 🌟

## 📘 简介

Spring Boot 万用模板是一个**集成众多实用功能和组件**的Spring Boot项目模板，🚀助力开发者**快速构建稳定、高效的后端服务**。模板内嵌了MySQL、Redis、Sa-Token认证鉴权、Spring Boot Admin监控中心、OpenAPI 3规范支持、COS对象存储、文件上传等功能，并使用了Spring Boot 2.7、Knife4J（Swagger UI的增强版）作为接口文档管理工具。🔧

## 🎨 功能特性

- 🔰 **Spring Boot 2.7**：基于最新版本的Spring Boot框架，提供强大的自动配置和快速开发能力。
- 💾 **MySQL**：使用MySQL作为数据库存储，支持高性能、高并发的数据存储需求。
- 🐢 **Redis**：集成Redis作为缓存和消息中间件，助力提升系统性能和响应速度。
- 🔑 **Sa-Token**：轻量级无状态Java权限认证框架，提供便捷的认证鉴权功能。
- 🔭 **Spring Boot Admin**：提供对Spring Boot应用的监控管理，轻松查看应用状态、性能指标等。
- 📖 **OpenAPI 3**：支持最新的OpenAPI 3规范，用于接口文档描述，方便前后端开发人员进行接口对接。
- 📁 **COS对象存储**：集成COS（如云厂商的云对象存储服务）实现文件存储，满足文件存储和访问的需求。
- 📂 **文件上传**：支持文件上传功能，轻松实现用户文件上传至服务器。
- 📘 **Knife4J**：基于Swagger UI的接口文档管理工具，提供友好的界面和丰富的功能，方便开发者查看和管理接口文档。

## 📘 使用说明

### 1️⃣ 克隆项目

```bash
git clone https://github.com/XiaoZhangCode/spring-boot-init.git
cd spring-boot-init
```

### 2️⃣ 配置环境

- 修改`application.properties`或`application.yml`文件中的数据库、Redis、COS等配置信息，确保与实际环境相匹配。
- 根据需要修改其他相关配置，如端口号、日志级别等。

### 3️⃣ 启动项目

```bash
mvn spring-boot:run
```
或者，如果你使用的是IDE（如IntelliJ IDEA或Eclipse），直接运行主启动类即可启动项目。🏃

### 4️⃣ 访问接口文档

在浏览器中输入`http://localhost:8080/doc.html`（默认端口为8080，根据实际情况调整）即可访问Knife4J提供的接口文档页面，轻松查看和管理项目中的接口信息。📖

### 5️⃣ 监控管理

Spring Boot Admin的监控管理页面通常可以通过`http://localhost:8080/bootAdmin`访问（默认端口为8080，根据实际情况调整）。在该页面上，你可以查看应用的运行状态、性能指标、日志信息等。🔍

## 💡 注意事项

- 🔍 请确保已经正确配置数据库、Redis等外部服务，并检查连接信息是否正确。
- 🔧 如有需要，可以根据项目实际情况调整配置和代码，以满足特定需求。

## 💪 贡献与反馈

如果你在使用过程中遇到问题或有任何建议，欢迎通过GitHub的Issue或Pull Request进行反馈和贡献。🤝 我们非常期待你的参与和贡献！

## 📧 联系方式

- 邮箱：[1687438992@qq.com](mailto:1687438992@qq.com)
- GitHub仓库：[https://github.com/XiaoZhangCode/spring-boot-init](https://github.com/XiaoZhangCode/spring-boot-init) 🔗