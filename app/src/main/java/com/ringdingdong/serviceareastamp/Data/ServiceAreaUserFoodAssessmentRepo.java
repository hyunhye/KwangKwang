package com.ringdingdong.serviceareastamp.Data;

/**
 * Created by Administrator on 2016-09-17.
 */
public class ServiceAreaUserFoodAssessmentRepo {
    private String food;
    private String score;
    private String unitCode;
    private String userid;

    public ServiceAreaUserFoodAssessmentRepo(String food, String score, String unitCode, String userid) {
        this.food = food;
        this.score = score;
        this.unitCode = unitCode;
        this.userid = userid;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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
