package com.ringdingdong.serviceareastamp.Data;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016-08-23.
 */
public class ListViewFoodPostScriptItem {
    public Drawable getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(Drawable profileimage) {
        this.profileimage = profileimage;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private Drawable profileimage ;
    private String memo ;
    private String name;
    private String star ;
    private String date ;


}
