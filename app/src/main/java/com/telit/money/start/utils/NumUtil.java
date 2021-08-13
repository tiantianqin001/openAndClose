package com.telit.money.start.utils;

import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;

import com.telit.money.start.bean.XmlBean;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NumUtil {
    private static final String TAG = "NumUtil";
    //把所有的二进制字节都到集合中
    private static List<String> stringLists = new ArrayList<>();
    private static List<byte[]> bytesLists = new ArrayList<>();
    private static XmlBean xmlBean;
   static List<XmlBean>   xmlBeans = new ArrayList<>();


    //16进制转byte
    public static byte[] hexString2Bytes(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        hexStr = hexStr.replaceAll(" ", "");
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 将byte数组转为16进制字符串
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv.toUpperCase());
        }
        return stringBuilder.toString();
    }


    /**
     * 获取最后一位
      */
    public static String bytesToHexLastString(String originalData){
        stringLists.clear();
        bytesLists.clear();
        //这里主要是获取最后一位，第一步把所有的值都转成2进制放到集合中
        //这里我以第1路为例子   FE 55 11 00 17 01 01 FF FF FF FF FF FF FF FF FF FF FF 52  //这里不要最后一位和第一位

        String[] strings = originalData.split("\\s+");
        for (String string : strings) {
            Log.i(TAG, "onCreate: .................." + string);

       /*     if (string.equals("FF") || string.equals("FE")) {
               continue;
            }*/
            stringLists.add(string);

        }
        //开始把数据转化成二进制
        for (String stringList : stringLists) {
            byte[] bytes = NumUtil.hexString2Bytes(stringList);
            bytesLists.add(bytes);
        }
        //开始把集合的所有数据递归异或获取带最后一位
        Log.i(TAG, "onCreate: " + bytesLists);
        //获取第一位字节
        byte[] fistByte = bytesLists.get(0);
        byte[] bResult = new byte[fistByte.length];
        for (int j = 1; j < bytesLists.size(); j++) {
            //获取第2位字节
            byte[] twoBytes = bytesLists.get(j);
            //第一个字节和第2个字节异或
            for (int i = 0; i < fistByte.length; i++) {
                bResult[i] = (byte) (fistByte[i] ^ twoBytes[i]);
            }
            //把异或后的结果当第一个字节和下一个字节比较
            fistByte=bResult;

        }
       return NumUtil.bytesToHexString(bResult);


    }

    /**
     * 获取要发送的地址
     * @param position
     * @param adress
     * @param isOpen
     */

    public static String getSendInfoAreess(int position, String adress, boolean isOpen ) {
        String result="";
        //FE 55 11 00 17   这个是固定的
        String  fixedPosition = "FE 55 11 00 17";
        //一共有两种地址分别是 01和02


        //地址后面跟了12位，这里主要是要关或者要开其中一路
        String openAndClose = getOpenAndClose(isOpen, position);

        //获取最后一位  是去掉第一位从55 开始的
       String lastString = NumUtil.bytesToHexLastString("55 11 00 17"+" "+adress + openAndClose);

        QZXTools.logD(lastString);
        result = fixedPosition +" "+ adress + openAndClose+" " + lastString;
        return result;
    }

    /**
     * 获取中间12位的字符串
     * @param isOpen
     * @param position
     */

    private static String getOpenAndClose(boolean isOpen, int position) {
        String resule="";
        for (int i = 1; i <=12 ; i++) {
            if (i == position){
                if (isOpen){
                  //当前是开
                    resule=resule+" "+"01";
                }else {
                    //当前是关
                    resule=resule+" "+"00";
                }
            }else {
                resule=resule+" "+"FE";
            }
        }
        return resule;
    }



    public static List<XmlBean> getUrls(InputStream inputStream) throws XmlPullParserException, IOException {
        xmlBeans.clear();

        //得到PULL解析器
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream,"UTF-8");
        //产生事件
        int eventType = parser.getEventType();
        //如果不是文档结束事件就循环推进
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT://开始文档事件
                    break;
                case XmlPullParser.START_TAG://开始元素事件
                    //获取解析器当前指向的元素的名称
                    String name = parser.getName();
                    if ("address".equals(name)) {
                        xmlBean = new XmlBean();
                        xmlBean.setId(Integer.parseInt(parser.getAttributeValue(0)));
                    }
                    if (xmlBean != null) {
                        if ("name".equals(name)) {
                            //获取解析器当前指向元素的下一个文本节点的值
                            xmlBean.setName(parser.nextText());
                        }
                        if ("addre".equals(name)) {
                            xmlBean.setAddre(parser.nextText());
                        }
                        if ("area".equals(name)) {
                            xmlBean.setArea(parser.nextText());
                        }
                        if ("fewWays".equals(name)) {
                            xmlBean.setFewWays(parser.nextText());
                        }
                        if ("url".equals(name)) {
                            xmlBean.setUrl(parser.nextText());
                        }
                        if ("position".equals(name)) {
                            xmlBean.setPosition(Integer.parseInt(parser.nextText()));
                        }
                        if ("port".equals(name)){
                            xmlBean.setPort(Integer.parseInt(parser.nextText()));
                        }
                    }
                    break;
                case XmlPullParser.END_TAG://结束元素事件
                    //判断是都是person的结束事件
                    if ("address".equals(parser.getName())) {
                        xmlBeans.add(xmlBean);
                        xmlBean = null;
                    }
                    break;
            }
            //进入下一个元素并触发相应的事件
            eventType = parser.next();
        }
        return xmlBeans;

    }
}
