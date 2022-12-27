# 使用xml文件进行配置
# 问题记录

# maven依赖配置
    1.dependencyManagement作用
    可以抽取公共依赖，统一配置版本，子模块引用
    不会直接下载依赖，并且只有子模块引用了之后才会生效。若要下载先屏蔽该标签
# 什么是java bean?
    所以 Java Bean就是一种类，并非所有的类都是 Java Bean，其是一种特殊的类，具有以下特征：

    1.提供一个默认的无参构造函数。
    2.需要被序列化并且实现了 Serializable 接口。
    3.可能有一系列可读写属性，并且一般是 private 的。
    4.可能有一系列的 getter 或 setter 方法。
    5.根据封装的思想，我们使用 get 和 set 方法封装 private 的属性，并且根据属性是否可读写来选择封装方法。

# beanFactory 后置处理器