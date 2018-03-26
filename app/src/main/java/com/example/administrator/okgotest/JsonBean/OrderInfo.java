package com.example.administrator.okgotest.JsonBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */

public class OrderInfo {

    /**
     * orderno : 45a888facb6a4605978879c784b2a67e
     * orderbody : 租穿戴式设备押金 1000元/个,服务费 100元/月,共5个月
     * ordersubject : 蓝牙式穿戴设备
     * orderamount : 1.50
     * orderstatue : TRADE_CLOSED
     * ordertime : 2018-01-29 13:18:46
     * refundFee : 1.50
     * gmtRefund : 2018-01-29 13:20:26
     * orderchilds : [{"ordername":"租穿戴式设备押金","ordernumber":"1","orderunitprivce":"1.00","ordertotalprivce":"1.00","orderunitname":"个"},{"ordername":"服务费","ordernumber":"5","orderunitprivce":"0.10","ordertotalprivce":"0.50","orderunitname":"月"}]
     */

    private String orderno;
    private String orderbody;
    private String ordersubject;
    private String orderamount;
    private String orderstatue;
    private String ordertime;
    private String refundFee;
    private String gmtRefund;
    private List<OrderchildsBean> orderchilds;

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getOrderbody() {
        return orderbody;
    }

    public void setOrderbody(String orderbody) {
        this.orderbody = orderbody;
    }

    public String getOrdersubject() {
        return ordersubject;
    }

    public void setOrdersubject(String ordersubject) {
        this.ordersubject = ordersubject;
    }

    public String getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(String orderamount) {
        this.orderamount = orderamount;
    }

    public String getOrderstatue() {
        return orderstatue;
    }

    public void setOrderstatue(String orderstatue) {
        this.orderstatue = orderstatue;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(String gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public List<OrderchildsBean> getOrderchilds() {
        return orderchilds;
    }

    public void setOrderchilds(List<OrderchildsBean> orderchilds) {
        this.orderchilds = orderchilds;
    }

    public static class OrderchildsBean {
        /**
         * ordername : 租穿戴式设备押金
         * ordernumber : 1
         * orderunitprivce : 1.00
         * ordertotalprivce : 1.00
         * orderunitname : 个
         */

        private String ordername;
        private String ordernumber;
        private String orderunitprivce;
        private String ordertotalprivce;
        private String orderunitname;

        public String getOrdername() {
            return ordername;
        }

        public void setOrdername(String ordername) {
            this.ordername = ordername;
        }

        public String getOrdernumber() {
            return ordernumber;
        }

        public void setOrdernumber(String ordernumber) {
            this.ordernumber = ordernumber;
        }

        public String getOrderunitprivce() {
            return orderunitprivce;
        }

        public void setOrderunitprivce(String orderunitprivce) {
            this.orderunitprivce = orderunitprivce;
        }

        public String getOrdertotalprivce() {
            return ordertotalprivce;
        }

        public void setOrdertotalprivce(String ordertotalprivce) {
            this.ordertotalprivce = ordertotalprivce;
        }

        public String getOrderunitname() {
            return orderunitname;
        }

        public void setOrderunitname(String orderunitname) {
            this.orderunitname = orderunitname;
        }
    }
}
