package com.ringdingdong.serviceareastamp.Data;

/**
 * Created by Administrator on 2016-08-24.
 */
public class ChildListExpressStampItem {
    public String getServiceAreaName() {
        return serviceAreaName;
    }

    public void setServiceAreaName(String serviceAreaName) {
        this.serviceAreaName = serviceAreaName;
    }

    public boolean isServiceAreaCheck() {
        return serviceAreaCheck;
    }

    public void setServiceAreaCheck(boolean serviceAreaCheck) {
        this.serviceAreaCheck = serviceAreaCheck;
    }

    public ChildListExpressStampItem(String serviceAreaName, boolean serviceAreaCheck) {
        this.serviceAreaName = serviceAreaName;
        this.serviceAreaCheck = serviceAreaCheck;
    }

    private String serviceAreaName;
    private boolean serviceAreaCheck;
}
