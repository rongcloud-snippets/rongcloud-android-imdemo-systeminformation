package com.example.rongcloud_android_imdemo_systeminformation;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class RongApplication extends Application {

    private static final String TAG = "RongApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化. 建议在 Application 中进行初始化.
        String appKey = "融云开发者后台创建的应用的 AppKey";
        RongIM.init(this, appKey);

        String token = "用户Token";
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus code) {
                //消息数据库打开，可以进入到主页面
                Log.d(TAG, "onDatabaseOpened: " + code);

                // 是否缓存用户信息. true 缓存, false 不缓存
                // 1. <span style="color:red">当设置 true 后, 优先从缓存中获取用户信息.
                // 2. 更新用户信息, 需调用 RongIM.getInstance().refreshUserInfoCache(userInfo)
                boolean isCacheUserInfo = true;
                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

                    /**
                     * 获取设置用户信息. 通过返回的 userId 来封装生产用户信息.
                     * @param userId 用户 ID
                     */
                    @Override
                    public UserInfo getUserInfo(String userId) {
                        UserInfo userInfo = new UserInfo(userId, "userId 对应的名称", Uri.parse("userId 对应的头像地址"));
                        return userInfo;
                    }

                }, isCacheUserInfo);


                //连接成功后， 跳转到会话列表界面
                Map<String, Boolean> supportedConversation = new HashMap<>();
                supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
                supportedConversation.put(Conversation.ConversationType.GROUP.getName(), false);
                // 把系统消息聚合显示
                supportedConversation.put(Conversation.ConversationType.SYSTEM.getName(), true);
                RongIM.getInstance().startConversationList(getApplicationContext(), supportedConversation);
            }

            @Override
            public void onSuccess(String s) {
                //连接成功
                Log.d(TAG, "onSuccess: " + s);

            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode errorCode) {
                Log.d(TAG, "onError: " + errorCode);

                if (errorCode.equals(RongIMClient.ConnectionErrorCode.RC_CONN_TOKEN_INCORRECT)) {
                    //从 APP 服务获取新 token，并重连
                } else {
                    //无法连接 IM 服务器，请根据相应的错误码作出对应处理
                }
            }
        });


    }
}
