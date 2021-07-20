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
    // 基础的网络请求地址
   // public static final String API_URL = "http://www.0531yun.cn/wsjc";
    public static final String API_URL = "http://iot.0531yun.cn/wsjc";

    //登录的接口
    public static final String loginApp = API_URL + "/app/Login";

    //获取用户设备分组列表
    public static final String DEVICE_LIST = API_URL + "/app/GetUserDeviceGroups";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    //获取实时数据
    public static final String DeviceData = API_URL + "/app/GetDeviceData";
    ;


}
