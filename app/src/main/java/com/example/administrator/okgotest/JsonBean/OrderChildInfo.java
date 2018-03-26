package com.example.administrator.okgotest.JsonBean;

import java.io.Serializable;

public class OrderChildInfo implements Serializable {

    //盒子押金
    //@NotBlank(message="订单名称不能为空")
    private String ordername;
    private String ordernumber;
    private String orderunitprice;
    private String ordertotalprice;
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

    public String getOrderunitprice() {
        return orderunitprice;
    }

    public void setOrderunitprice(String orderunitprice) {
        this.orderunitprice = orderunitprice;
    }

    public String getOrdertotalprice() {
        return ordertotalprice;
    }

    public void setOrdertotalprice(String ordertotalprice) {
        this.ordertotalprice = ordertotalprice;
    }

    public String getOrderunitname() {
        return orderunitname;
    }

    public void setOrderunitname(String orderunitname) {
        this.orderunitname = orderunitname;
    }

    //@NotBlank(message="订单内容不能为空")

}
