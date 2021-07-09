package com.telit.money.start.bean;

import java.io.Serializable;
import java.util.List;


public class LoginBean implements Serializable {

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    /**
     * code : 1000
     * message : 登录成功
     * data : {"userId":"38","userName":"jnrstest","projectName":null,"authList":["DataEdit"]}
     */

    private int code;
    private String message;
    private DataBean data;


    public static class DataBean implements Serializable {
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getProjectName() {
            return projectName;
        }

        public void setProjectName(Object projectName) {
            this.projectName = projectName;
        }

        public List<String> getAuthList() {
            return authList;
        }

        public void setAuthList(List<String> authList) {
            this.authList = authList;
        }

        /**
         * userId : 38
         * userName : jnrstest
         * projectName : null
         * authList : ["DataEdit"]
         */

        private String userId;
        private String userName;
        private Object projectName;
        private List<String> authList;
    }
}
