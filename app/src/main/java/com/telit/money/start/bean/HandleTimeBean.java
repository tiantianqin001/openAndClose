package com.telit.money.start.bean;

import java.io.Serializable;
import java.util.List;


public class HandleTimeBean implements Serializable {

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    /**
     * code : 1000
     * message : 获取成功
     * data : [{"groupId":"22619","deviceKey":"22619","deviceAddr":21038816,"nodeID":1,"nodeType":3,"deviceDisabled":false,"deviceName":"温湿度","lng":0,"lat":0,"deviceStatus":2,"realTimeData":[{"dataName":"温度(℃)","dataValue":"27.70","isAlarm":false,"alarmMsg":"","alarm":false},{"dataName":"湿度(%RH)","dataValue":"47.60","isAlarm":false,"alarmMsg":"","alarm":false}]},{"groupId":"22619","deviceKey":"22619","deviceAddr":21038816,"nodeID":2,"nodeType":2,"deviceDisabled":false,"deviceName":"CO2","lng":0,"lat":0,"deviceStatus":2,"realTimeData":[{"dataName":"CO2(ppm)","dataValue":"768.00","isAlarm":false,"alarmMsg":"","alarm":false}]}]
     */

    private int code;
    private String message;
    private List<DataBean> data;


    public static class DataBean implements Serializable {
        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getDeviceKey() {
            return deviceKey;
        }

        public void setDeviceKey(String deviceKey) {
            this.deviceKey = deviceKey;
        }

        public int getDeviceAddr() {
            return deviceAddr;
        }

        public void setDeviceAddr(int deviceAddr) {
            this.deviceAddr = deviceAddr;
        }

        public int getNodeID() {
            return nodeID;
        }

        public void setNodeID(int nodeID) {
            this.nodeID = nodeID;
        }

        public int getNodeType() {
            return nodeType;
        }

        public void setNodeType(int nodeType) {
            this.nodeType = nodeType;
        }

        public boolean isDeviceDisabled() {
            return deviceDisabled;
        }

        public void setDeviceDisabled(boolean deviceDisabled) {
            this.deviceDisabled = deviceDisabled;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public int getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(int deviceStatus) {
            this.deviceStatus = deviceStatus;
        }

        public List<RealTimeDataBean> getRealTimeData() {
            return realTimeData;
        }

        public void setRealTimeData(List<RealTimeDataBean> realTimeData) {
            this.realTimeData = realTimeData;
        }

        /**
         * groupId : 22619
         * deviceKey : 22619
         * deviceAddr : 21038816
         * nodeID : 1
         * nodeType : 3
         * deviceDisabled : false
         * deviceName : 温湿度
         * lng : 0.0
         * lat : 0.0
         * deviceStatus : 2
         * realTimeData : [{"dataName":"温度(℃)","dataValue":"27.70","isAlarm":false,"alarmMsg":"","alarm":false},{"dataName":"湿度(%RH)","dataValue":"47.60","isAlarm":false,"alarmMsg":"","alarm":false}]
         */

        private String groupId;
        private String deviceKey;
        private int deviceAddr;
        private int nodeID;
        private int nodeType;
        private boolean deviceDisabled;
        private String deviceName;
        private double lng;
        private double lat;
        private int deviceStatus;
        private List<RealTimeDataBean> realTimeData;


        public static class RealTimeDataBean implements Serializable {
            public String getDataName() {
                return dataName;
            }

            public void setDataName(String dataName) {
                this.dataName = dataName;
            }

            public String getDataValue() {
                return dataValue;
            }

            public void setDataValue(String dataValue) {
                this.dataValue = dataValue;
            }

            public boolean isAlarm() {
                return isAlarm;
            }

            public void setAlarm(boolean alarm) {
                isAlarm = alarm;
            }

            public String getAlarmMsg() {
                return alarmMsg;
            }

            public void setAlarmMsg(String alarmMsg) {
                this.alarmMsg = alarmMsg;
            }

            /**
             * dataName : 温度(℃)
             * dataValue : 27.70
             * isAlarm : false
             * alarmMsg :
             * alarm : false
             */

            private String dataName;
            private String dataValue;
            private boolean isAlarm;
            private String alarmMsg;
            private boolean alarm;
        }
    }
}
