package com.wuweiyangxian.bean;

import java.util.List;

public class PayDetailBean {
    private String time;
    private String expenditure;
    private String income;
    private List<PayChildBean> list;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public List<PayChildBean> getList() {
        return list;
    }

    public void setList(List<PayChildBean> list) {
        this.list = list;
    }

    public static class PayChildBean{
        private String name;
        private String price;
        private String time;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
