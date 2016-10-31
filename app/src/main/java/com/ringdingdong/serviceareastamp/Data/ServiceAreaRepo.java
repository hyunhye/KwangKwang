package com.ringdingdong.serviceareastamp.Data;

/**
 * Created by Administrator on 2016-09-16.
 */
public class ServiceAreaRepo {
    private String phone;
    private String convenience;
    private String gasolinePrice;
    private String lpgPrice;
    private String batchMenu;
    private String avg_total_score;
    private String address;
    private String sarea_pic;

    public String getSarea_pic() {
        return sarea_pic;
    }

    public void setSarea_pic(String sarea_pic) {
        this.sarea_pic = sarea_pic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConvenience() {
        return convenience;
    }

    public void setConvenience(String convenience) {
        this.convenience = convenience;
    }

    public String getGasolinePrice() {
        return gasolinePrice;
    }

    public void setGasolinePrice(String gasolinePrice) {
        this.gasolinePrice = gasolinePrice;
    }

    public String getLpgPrice() {
        return lpgPrice;
    }

    public void setLpgPrice(String lpgPrice) {
        this.lpgPrice = lpgPrice;
    }

    public String getBatchMenu() {
        return batchMenu;
    }

    public void setBatchMenu(String batchMenu) {
        this.batchMenu = batchMenu;
    }

    public String getAvg_total_score() {
        return avg_total_score;
    }

    public void setAvg_total_score(String avg_total_score) {
        this.avg_total_score = avg_total_score;
    }

}
