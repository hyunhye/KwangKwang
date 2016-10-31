package com.ringdingdong.serviceareastamp.Data;

/**
 * Created by Administrator on 2016-09-18.
 */
public class EachServiceAreaRepo {
    private String  unitCode, unitName, region;

    public EachServiceAreaRepo(String unitCode, String unitName, String region) {
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.region = region;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
