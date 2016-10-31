package com.ringdingdong.serviceareastamp.Data;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016-08-23.
 */
public class ListViewNearByItem {
    private String imageURL ;


    private String name ;
    private String distance;
    private String address ;
    private String star ;
    private String code;
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }


}
