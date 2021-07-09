package com.telit.money.start.netty;
import java.util.UUID;

/**
 * author: qzx
 * Date: 2019/4/28 11:40
 */
public class MsgUtils {
    public static final String SEPARATOR = " ";//分隔符

    public static final String HEAD_HEART = "HeartBeat";//心跳

    public static final String HEAD_ACKNOWLEDGE = "Acknowledgement";//消息反馈

    public static final String HEAD_JOINCLASS = "JoinClass";//连接服务成功后加入班级
    public static final String HEAD_RECONNECT = "Reconnect";//第二次连接的时候发送重连命令
    public static final String HEAD_JOIN_CLASS_SUCCESS = "JoinClassSucess";//加入班级成功
    public static final String HEAD_RECONNECT_SUCCESS = "ReconnectSucess";//重新加入班级成功

    public static final String HEAD_OUT_OF_CLASS = "OutOfClass";//退出登录时引用
    public static final String HEAD_ONE_OPEN = "HEAD_ONE_OPEN";  //一键开机
    public static final String HEAD_ONE_CLOSE = "HEAD_ONE_CLOSE";  //一键关机


    /**
     * @describe 消息反馈
     * @author luxun
     * create at 2017/5/5 0005 17:21
     * <p>
     * 注意：这个uuid是来自服务端带过来的,服务端去重,这个不要
     */
    public static String createAcknowledge(String fromServerUUID) {
        return HEAD_ACKNOWLEDGE + SEPARATOR + fromServerUUID + "\r\n";
    }

    /**
     * @describe 心跳信息
     * @author luxun
     * create at 2017/3/21 16:51
     */
    public static String heartMsg() {

//        QZXTools.logE("heartMsg=" + UserUtils.getStudentId(), null);
        return HEAD_HEART + SEPARATOR + MsgUtils.uuid() + "\r\n";
    }




    /*
     * 教师端推送地址
     *
     * */



    public static String uuid() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }


}
