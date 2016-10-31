package com.ringdingdong.serviceareastamp.Data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016-09-11.
 */
public class CurrentPositionRepo {
    @SerializedName("x")
    double x;
    @SerializedName("y")
    double y;

    public CurrentPositionRepo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}
