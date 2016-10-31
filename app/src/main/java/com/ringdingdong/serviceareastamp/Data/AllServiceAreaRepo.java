package com.ringdingdong.serviceareastamp.Data;

/**
 * Created by Administrator on 2016-08-02.
 */
public class AllServiceAreaRepo {

    String unitCode;
    String unitName;
    double xValue;
    double yValue;
    String avg_total_score;
    String sarea_pic;
    String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getxValue() {
        return xValue;
    }

    public void setxValue(double xValue) {
        this.xValue = xValue;
    }

    public double getyValue() {
        return yValue;
    }

    public void setyValue(double yValue) {
        this.yValue = yValue;
    }

    public String getAvg_total_score() {
        return avg_total_score;
    }

    public void setAvg_total_score(String avg_total_score) {
        this.avg_total_score = avg_total_score;
    }

    public String getSarea_pic() {
        return sarea_pic;
    }

    public void setSarea_pic(String sarea_pic) {
        this.sarea_pic = sarea_pic;
    }


}