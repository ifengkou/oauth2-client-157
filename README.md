## OAuth2-Client

基于 springboot 1.5.7 做的测试，老版本的示例很少，大部分是基于 5.0讲解。

OAuth服务端用的是Github的。

如何在Github上创建OAuth-App，见 https://github.com/settings/developers

## 测试

修改 application.properties中的client_id和client_secret 即可部署测试

```properties
security.oauth2.client.client_id=123
security.oauth2.client.client_secret=abc
```

****

## Nginx 做反向代理后，OAuth2-Client 无法正常使用的异常解决方案

见：https://blog.95id.com/oauth2-client-nginx-bugs.html