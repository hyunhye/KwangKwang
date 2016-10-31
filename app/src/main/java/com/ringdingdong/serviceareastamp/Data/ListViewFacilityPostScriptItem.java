package com.ringdingdong.serviceareastamp.Data;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016-08-23.
 */
public class ListViewFacilityPostScriptItem {
    private Drawable profileimage;
    private String memo;
    private String name;
    private String tag1;
    private String tag2;
    private String tag3;
    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {this.tag1 = tag1;}

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

}
