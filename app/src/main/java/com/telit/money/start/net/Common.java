package com.telit.money.start.net;

import okhttp3.MediaType;

/**
 * @author qiujuer
 */

public class Common {
    /**
     * 一些不可变的永恒的参数
     * 通常用于一些配置
     */
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public interface Constance {
        // 基础的网络请求地址
        String API_URL = "http://www.0531yun.cn/wsjc";

        //登录的接口
        String loginApp = API_URL + "/app/Login";

    }
}
