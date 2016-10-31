package com.ringdingdong.serviceareastamp.Data;

/**
 * Created by Administrator on 2016-09-18.
 */
public class ServiceAreaUserPostScript {
    private String unitCode;
    private String userid;
    private String nickname;
    private String date;
    private String comment;
    private String tag1;
    private String tag2;
    private String tag3;
    private String total_score;


    public ServiceAreaUserPostScript(String unitCode, String userid, String nickname, String date, String comment, String tag1, String tag2, String tag3, String total_score) {
        this.unitCode = unitCode;
        this.userid = userid;
        this.nickname = nickname;
        this.date = date;
        this.comment = comment;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.total_score = total_score;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

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
