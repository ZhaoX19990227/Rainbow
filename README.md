# Rainbow

一个基于 Spring Boot、MyBatis 和 Vue 的示例项目，演示注册登录及用户信息管理。

## 构建

```bash
mvn package
```

## 运行

```bash
java -jar target/rainbow-0.0.1-SNAPSHOT.jar
```

默认使用 `application.yml` 中配置的 `local` 环境，如需连接远程数据库可以将 `spring.profiles.active` 设置为 `prod`。
