package com.example.administrator.okgotest.JsonBean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/27.
 */

public class TestBean {


    /**
     * code : 106
     * message : 获取数据成功
     * number : 35
     * content : [{"deviceType":"1","sbp":"109","dbp":"64","heartRate":"72","familyname":"18201335822","weight":"","measureTime":"2017-10-27 16:13:00"},{"deviceType":"1","sbp":"120","dbp":"80","heartRate":"80","familyname":"18201335822","weight":"  ","measureTime":"2017-10-27 16:11:44"},{"deviceType":"1","sbp":"99","dbp":"60","heartRate":"75","familyname":"18201335822","weight":"","measureTime":"2017-10-27 15:10:00"},{"deviceType":"1","sbp":"102","dbp":"58","heartRate":"67","familyname":"18201335822","weight":"","measureTime":"2017-10-27 15:29:00"},{"deviceType":"1","sbp":"119","dbp":"78","heartRate":"63","familyname":"18201335822","weight":"","measureTime":"2017-10-24 17:12:00"},{"deviceType":"1","sbp":"108","dbp":"62","heartRate":"67","familyname":"18201335822","weight":"","measureTime":"2017-10-27 16:00:00"},{"deviceType":"1","sbp":"107","dbp":"63","heartRate":"71","familyname":"18201335822","weight":"","measureTime":"2017-10-27 16:03:00"},{"deviceType":"1","sbp":"120","dbp":"80","heartRate":"80","familyname":"18201335822","weight":"  ","measureTime":"2017-10-27 16:09:00"},{"deviceType":"1","sbp":"108","dbp":"62","heartRate":"67","familyname":"18201335822","weight":"","measureTime":"2017-10-27 16:00:00"},{"deviceType":"1","sbp":"107","dbp":"63","heartRate":"71","familyname":"18201335822","weight":"","measureTime":"2017-10-27 16:03:00"},{"deviceType":"1","sbp":"120","dbp":"80","heartRate":"80","familyname":"18201335822","weight":"60 ","measureTime":"2017-10-27 16:05:19"},{"deviceType":"1","sbp":"120","dbp":"80","heartRate":"80","familyname":"18201335822","weight":"  ","measureTime":"2017-10-27 16:05:07"},{"deviceType":"1","sbp":"107","dbp":"63","heartRate":"71","familyname":"18201335822","weight":"","measureTime":"2017-10-27 16:03:00"},{"deviceType":"1","sbp":"108","dbp":"62","heartRate":"67","familyname":"18201335822","weight":"","measureTime":"2017-10-27 16:00:00"},{"deviceType":"1","sbp":"120","dbp":"80","heartRate":"80","familyname":"18201335822","weight":"  ","measureTime":"2017-10-27 16:00:08"},{"deviceType":"2","sbp":"121","dbp":"121","heartRate":"123","familyname":"18201335822","weight":"","measureTime":"2017-10-27 12:00:00"},{"deviceType":"1","sbp":"120","dbp":"80","heartRate":"80","familyname":"18201335822","weight":"63","measureTime":"2017-10-27 15:23:41"},{"deviceType":"1","sbp":"120","dbp":"80","heartRate":"80","familyname":"18201335822","weight":"60","measureTime":"2017-10-27 15:23:14"},{"deviceType":"1","sbp":"123","dbp":"123","heartRate":"32","familyname":"18201335822","weight":"60","measureTime":"2017-10-27 12:00:00"},{"deviceType":"1","sbp":"119","dbp":"78","heartRate":"63","familyname":"18201335822","weight":"","measureTime":"2017-10-24 17:12:00"},{"deviceType":"1","sbp":"0","dbp":"0","heartRate":"0","familyname":"18201335822","weight":"","measureTime":"2017-10-25 16:43:00"},{"deviceType":"1","sbp":"138","dbp":"95","heartRate":"67","familyname":"18201335822","weight":"","measureTime":"2017-10-25 16:40:00"},{"deviceType":"1","sbp":"149","dbp":"95","heartRate":"65","familyname":"18201335822","weight":"","measureTime":"2017-10-25 16:37:00"},{"deviceType":"1","sbp":"0","dbp":"0","heartRate":"0","familyname":"18201335822","weight":"","measureTime":"2017-10-25 16:36:00"},{"deviceType":"1","sbp":"0","dbp":"0","heartRate":"0","familyname":"18201335822","weight":"","measureTime":"2017-10-25 16:24:00"},{"deviceType":"1","sbp":"120","dbp":"80","heartRate":"80","familyname":"18201335822","weight":"","measureTime":"2017-10-24 17:13:54"},{"deviceType":"1","sbp":"126","dbp":"83","heartRate":"81","familyname":"18201335822","weight":"","measureTime":"2017-10-24 17:15:09"},{"deviceType":"1","sbp":"119","dbp":"78","heartRate":"63","familyname":"18201335822","weight":"","measureTime":"2017-10-24 17:12:00"},{"deviceType":"1","sbp":"0","dbp":"0","heartRate":"0","familyname":"18201335822","weight":"","measureTime":"2017-10-24 17:12:00"},{"deviceType":"1","sbp":"0","dbp":"0","heartRate":"0","familyname":"18201335822","weight":"","measureTime":"2017-10-18 17:06:00"},{"deviceType":"1","sbp":"0","dbp":"0","heartRate":"0","familyname":"18201335822","weight":"","measureTime":"2017-10-18 17:04:00"},{"deviceType":"1","sbp":"0","dbp":"0","heartRate":"0","familyname":"18201335822","weight":"","measureTime":"2017-10-18 16:47:00"},{"deviceType":"1","sbp":"180","dbp":"58","heartRate":"87","familyname":"18201335822","weight":"","measureTime":"2017-10-18 16:40:00"},{"deviceType":"1","sbp":"120","dbp":"72","heartRate":"78","familyname":"18201335822","weight":"","measureTime":"2017-10-18 09:52:00"},{"deviceType":"1","sbp":"123","dbp":"68","heartRate":"77","familyname":"18201335822","weight":"","measureTime":"2017-10-18 09:34:00"}]
     */

    private String code;
    private String message;
    private String number;
    private List<ContentBean> content;

    public TestBean(List<ContentBean> content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * deviceType : 1
         * sbp : 109
         * dbp : 64
         * heartRate : 72
         * familyname : 18201335822
         * weight :
         * measureTime : 2017-10-27 16:13:00
         */

        private String deviceType;
        private String sbp;
        private String dbp;
        private String heartRate;
        private String familyname;
        private String weight;
        private String measureTime;

        public ContentBean(String deviceType, String familyname) {
            this.deviceType = deviceType;
            this.familyname = familyname;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getSbp() {
            return sbp;
        }

        public void setSbp(String sbp) {
            this.sbp = sbp;
        }

        public String getDbp() {
            return dbp;
        }

        public void setDbp(String dbp) {
            this.dbp = dbp;
        }

        public String getHeartRate() {
            return heartRate;
        }

        public void setHeartRate(String heartRate) {
            this.heartRate = heartRate;
        }

        public String getFamilyname() {
            return familyname;
        }

        public void setFamilyname(String familyname) {
            this.familyname = familyname;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getMeasureTime() {
            return measureTime;
        }

        public void setMeasureTime(String measureTime) {
            this.measureTime = measureTime;
        }
    }
}
