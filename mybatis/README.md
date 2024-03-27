# mybatis
- [参考](https://www.kuangstudy.com/zl/ssm#1377533013419716610)
- [官方文档](https://mybatis.org/mybatis-3/zh/configuration.html)

java这类框架都支持2种方式配置，一种是xml文件，一种是基于注解

## 配置文件mybatis-config.xml

示例：
xml文件中可以引用properties文件中的变量
缺点就是有很多硬编码的地方，比如namespace,id等

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "https://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="${driver}"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${username}"/>
        <property name="password" value="${password}"/>
      </dataSource>
    </environment>
  </environments>
  <mappers>
    <mapper resource="org/mybatis/example/BlogMapper.xml"/>
  </mappers>
</configuration>
```