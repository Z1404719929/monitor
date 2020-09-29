package com.datangedu.cn.model.sysUser;

public class Alldata {
    private String airportname;

    private Integer year;

    private Double longitude;

    private Double latitude;

    private Integer month;

    private Double freightvalue;

    private Double passengervalue;

    public String getAirportname() {
        return airportname;
    }

    public void setAirportname(String airportname) {
        this.airportname = airportname == null ? null : airportname.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getFreightvalue() {
        return freightvalue;
    }

    public void setFreightvalue(Double freightvalue) {
        this.freightvalue = freightvalue;
    }

    public Double getPassengervalue() {
        return passengervalue;
    }

    public void setPassengervalue(Double passengervalue) {
        this.passengervalue = passengervalue;
    }
}