package com.datangedu.cn.model.sysUser;

public class AirFreight {
    private Integer index;

    private String airportname;

    private String longitude;

    private String latitude;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getAirportname() {
        return airportname;
    }

    public void setAirportname(String airportname) {
        this.airportname = airportname == null ? null : airportname.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }
}