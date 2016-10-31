package com.ringdingdong.serviceareastamp.Data;

/**
 * Created by Administrator on 2016-09-17.
 */
public class ServiceAreaStampRepo {
    String stamp,unitCode, userid;

    public ServiceAreaStampRepo(String stamp, String unitCode, String userid) {
        this.stamp = stamp;
        this.unitCode = unitCode;
        this.userid = userid;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
