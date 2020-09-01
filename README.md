# rongcloud-android -imdemo-systeminformation

rongcloud-android-imdemo-systeminformation 是基于融云 IMKit SDK 的简单集成，内部实现了系统消息的demo，使用了融云最新的 4.0 SDK。


# 使用步骤

## 前置条件

1. 参数配置：

开发者需将项目中的 `Appkey` 和 `Token`，替换为自己的真实数据

[AppKey 获取地址](https://developer.rongcloud.cn/app/appkey/0vMK99Huzz-qw40Ybv4NDA)

[Token 获取地址](https://developer.rongcloud.cn/apitool/Mw8EsJmV43kZBugTMSAZXg)


## 发送接收消息

1. 系统会话仅支持服务端发送消息，所以客户端无法在系统会话内发送消息。

2. 新用户默认是空的会话列表，是无法进入会话页面的。开发者可在 [开发者后台 - 服务管理 - API 调用 - 消息服务](https://developer.rongcloud.cn/apitool/kNUDHRczlPHkECa0SJ8X3Q)  中调    用对应接口给当前用户发送消息。

   发送成功后，接收方的会话列表展示此会话。

3. 用户可在开发者后台的消息服务 - 发送系统消息 中填入对应参数发送系统消息。 自定义消息可输入下面内容，demo 中已经实现此自定义消息的编解码与 UI 显示。
	
	fromUserId: 10086
	objectName: RCS:InformationMsg
	
	content: {"inforTitle":"苹果股价还得涨","inforSubTitle":"北京时间8月25日，近日分析机构D.A. Davidson知名分析师汤姆·福尔特（Tom Forte）表示，苹果公司股价将会在今年迎来更多历史性的突破，这主要得益于5G版iPhone 12的推出以及苹果的居家办公。","inforUrl":"https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_10030678008519773834%22%7D&n_type=0&p_from=1"}

## 替换会话列表内头像和用户名

头像可替换 `RongCloud.bundle` 中的 `default_portrait`
用户名可替换 `string.xml` 中的 `conversation_systemMessage_collection_title`


## 替换子会话列表内头像和用户名

可实现相应的用户信息代理，返回对应的用户信息，可参考 demo 代码。

# 更多

[融云官网](https://www.rongcloud.cn/)

[融云文档](https://docs.rongcloud.cn/v3/)
