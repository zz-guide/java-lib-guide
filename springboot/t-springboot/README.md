#1.返回的是字符串的话，响应类型是text/plain;charset=UTF-8
#2.返回的是对象，响应类型就是application/json
#3.参数校验，全局异常处理

#参考链接：https://github.com/JustryDeng/CommonRepository


#问题
1.全局异常如何区分不用的异常返回不用的信息呢？

2.validation 的group特性感觉有点鸡肋，与其一个dto上写不同的校验规则还不如写多个dto呢？

3.@Valited和@Valid的区别

4.msg国际化配置和validation的快速失败，默认是校验全部参数