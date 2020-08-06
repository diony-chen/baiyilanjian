此组件是基于 rabbitmq 的消息中间件，保证消息100%发送成功。

默认支持数据库为 mysql，所以需要依赖 learn-shop-base-jpa组件,自动新建表mq_publisher，
如果想要使用其它保存方式请实现 StoredOperations 接口。

使用方法：

1.添加配置文件：rabbitmqcofnig.properties
```properties
v1.spring.rabbitmq.host=49.235.132.80
v1.spring.rabbitmq.port=5672
v1.spring.rabbitmq.username=chenhuan
v1.spring.rabbitmq.password=Chen1234..
v1.spring.rabbitmq.virtual-host=/learn-default
#consume 手动 ack
v1.spring.rabbitmq.listener.simple.acknowledge-mode=manual
#1.当mandatory标志位设置为true时，
#   如果exchange根据自身类型和消息routingKey无法找到一个合适的queue存储消息，
#   那么broker会调用basic.return方法将消息返还给生产者;
#2.当mandatory设置为false时，出现上述情况broker会直接将消息丢弃;通俗的讲，
#   mandatory标志告诉broker代理服务器至少将消息route到一个队列中，
#   否则就将消息return给发送者;
v1.spring.rabbitmq.template.mandatory=true
#publisher confirms 发送确认
v1.spring.rabbitmq.publisher-confirms=true
#returns callback ：
#   1.未送达exchange
#   2.送达exchange却未送道queue的消息 回调returnCallback.（注意）出现2情况时，publisher-confirms 回调的是true
v1.spring.rabbitmq.publisher-returns=true
# 消费但未确认消息最大数量
v1.spring.rabbitmq.listener.simple.prefetch=5
# 自定义
# 是否开启重试机制
v1.spring.rabbitmq.custom.is-retry=true
# 发送重试次数
v1.spring.rabbitmq.custom.send-retry-count=3
# 消费失败后重新放入队列中重试次数
v1.spring.rabbitmq.custom.receive-retry-count=3
# 扫描需要重试消息的频率
v1.spring.rabbitmq.custom.retry-cron=0/5 * * * * ?
# 重试消息的阀值，超过时有警告日志显示
v1.spring.rabbitmq.custom.cache-threshold=100
# 消息模板的名称
v1.spring.rabbitmq.custom.template-name=publicRabbitTemplate
```

2.注入：
```java
@Autowired
private StoredRabbitTemplate storedRabbitTemplate;
```

3.pom.xml
```xml
<dependency>
    <groupId>com.billow</groupId>
    <artifactId>learn-shop-base-mq</artifactId>
</dependency>
```

4.由于 PublisherPo 在包 com.billow.mq.stored.mysql.po 下，可能你的系统的 po 文件可能在 com.xx.xx.cc 下
所以需要在启动类上添加以下注解:
```java
@EntityScan(basePackages = {"com.billow.mq", "com.xx.xx.cc"})
@EnableJpaRepositories(basePackages = {"com.billow.mq", "com.xx.xx.cc"})
```