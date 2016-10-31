package com.ringdingdong.serviceareastamp.Data;

/**
 * Created by Administrator on 2016-09-16.
 */
public class ServiceAreaFoodAssessmentRepo {
    private String food_code;
    private String unitCode;
    private String food;
    private String food_score;
    private String count;
    private String result;

    public String getFood_code() {
        return food_code;
    }

    public void setFood_code(String food_code) {
        this.food_code = food_code;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getScore() {
        return food_score;
    }

    public void setScore(String score) {
        this.food_score = score;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
