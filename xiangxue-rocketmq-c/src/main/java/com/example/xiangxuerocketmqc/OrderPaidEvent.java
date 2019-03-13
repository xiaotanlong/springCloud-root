package com.example.xiangxuerocketmqc;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2019/3/12 15:52
 */
public class OrderPaidEvent implements Serializable {
    public OrderPaidEvent(String orderId, BigDecimal paidMoney){
        this.orderId = orderId;
        this.paidMoney = paidMoney;
    }
    private String orderId;

    private BigDecimal paidMoney;

    @Override
    public String toString() {
        return super.toString();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(BigDecimal paidMoney) {
        this.paidMoney = paidMoney;
    }
}
