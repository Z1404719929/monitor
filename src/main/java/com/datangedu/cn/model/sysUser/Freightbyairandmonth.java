package com.datangedu.cn.model.sysUser;

public class Freightbyairandmonth {
    private String airportname;

    private Integer month;

    private Double value;

    public String getAirportname() {
        return airportname;
    }

    public void setAirportname(String airportname) {
        this.airportname = airportname == null ? null : airportname.trim();
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}