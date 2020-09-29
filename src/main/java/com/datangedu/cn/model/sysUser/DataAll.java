package com.datangedu.cn.model.sysUser;

public class DataAll {
    private String airportname;

    private Integer year;

    private Integer month;

    private String freightvalue;
    
    private String passengervalue;

    private String longitude;

    private String latitude;

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

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getfreightvalue() {
        return freightvalue;
    }

    public void setfreightvalue(String value) {
        this.freightvalue = value;
    }
    
    public String getpassengervalue() {
        return passengervalue;
    }

    public void setpassengervalue(String value) {
        this.passengervalue = value;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}