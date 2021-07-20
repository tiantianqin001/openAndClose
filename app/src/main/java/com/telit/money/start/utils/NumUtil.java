package com.telit.money.start.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NumUtil {
    private static final String TAG = "NumUtil";
    //把所有的二进制字节都到集合中
    private static List<String> stringLists = new ArrayList<>();
    private static List<byte[]> bytesLists = new ArrayList<>();

//16进制转2进制
    public static byte[] hexString2Bytes(String hexStr) {
        if (hexStr.length() < 1)
            return null;
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
            if (!string.equals("FF")) {
                stringLists.add(string);
            }

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

}
