package com.telit.money.start.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.util.Xml;

import com.telit.money.start.bean.AdviceBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumUtil {
    private static final String TAG = "NumUtil";
    //把所有的二进制字节都到集合中
    private static List<String> stringLists = Collections.synchronizedList(new ArrayList<>());
    private static List<byte[]> bytesLists = Collections.synchronizedList(new ArrayList<>());

    private static List<AdviceBean> adviceBeans = Collections.synchronizedList(new ArrayList<>());

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
    public static synchronized String bytesToHexLastString(String originalData){
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
     * @param road
     * @param adress
     * @param isOpen
     */

    public static String getSendInfoAreess(int road, String adress, boolean isOpen ) {
        String result="";
        //FE 55 11 00 17   这个是固定的
        String  fixedPosition = "FE 55 11 00 17";
        //一共有两种地址分别是 01和02


        //地址后面跟了12位，这里主要是要关或者要开其中一路
        String openAndClose = getOpenAndClose(isOpen, road);

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

    public static String getJson(Context context, String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        BufferedReader bufferedReader=null;
        InputStreamReader inputStreamReader =null;
        //使用IO流读取json文件内容
        try {
             bufferedReader = new BufferedReader(inputStreamReader=new InputStreamReader(
                    assetManager.open(fileName),"utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStreamReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }


    public static  List<AdviceBean>  getListInfo(Context context,String fileName){
        try {
            adviceBeans.clear();
            String json = NumUtil.getJson(context, fileName);
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("result");
            if (jsonArray!=null && jsonArray.length()>0){
                for (int i = 0; i <jsonArray.length() ; i++) {
                    AdviceBean adviceBean = new AdviceBean();
                    JSONObject object = jsonArray.optJSONObject(i);
                    //获取所有的id
                    String id = object.optString("id");
                    //获取address
                    JSONObject jsonObject_adress = object.optJSONObject("address");
                    String name = jsonObject_adress.optString("name");
                    String addres = jsonObject_adress.optString("addres");
                    String area = jsonObject_adress.optString("area");
                    String road = jsonObject_adress.optString("road");
                    String typeId = jsonObject_adress.optString("typeId");
                    boolean isOpen = jsonObject_adress.optBoolean("isOpen");
                    adviceBean.setTypeId(typeId);
                    adviceBean.setOpen(isOpen);
                    adviceBean.setAdress(addres);
                    adviceBean.setName(name);
                    adviceBean.setArea(area);
                    adviceBean.setRoad(road);
                    //获取电脑 computer
                    JSONArray jsonArray_computer = object.optJSONArray("computer");

                    if (jsonArray_computer!=null && jsonArray_computer.length()>0){
                        List<AdviceBean.Computer> computers = new ArrayList<>();
                        for (int j = 0; j < jsonArray_computer.length(); j++) {
                            AdviceBean.Computer computer = new AdviceBean.Computer();
                            JSONObject jsonObject_computer = jsonArray_computer.optJSONObject(j);
                            String url = jsonObject_computer.optString("url");
                            computer.setUrl(url);
                            computers.add(computer);
                        }
                        adviceBean.setComputerList(computers);
                    }


                    adviceBeans.add(adviceBean);
                }
                return adviceBeans;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
