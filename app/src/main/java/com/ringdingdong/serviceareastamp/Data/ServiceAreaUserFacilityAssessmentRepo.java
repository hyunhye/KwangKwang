package com.ringdingdong.serviceareastamp.Data;

/**
 * Created by Administrator on 2016-09-17.
 */
public class ServiceAreaUserFacilityAssessmentRepo {
    private String s_score;
    private String l_score;
    private String f_score;
    private String r_score;
    private String p_score;
    private String w_score;
    private String unitCode;
    private String userid;

    public ServiceAreaUserFacilityAssessmentRepo(String s_score, String l_score, String f_score, String r_score, String p_score, String w_score, String unitCode, String userid) {
        this.s_score = s_score;
        this.l_score = l_score;
        this.f_score = f_score;
        this.r_score = r_score;
        this.p_score = p_score;
        this.w_score = w_score;
        this.unitCode = unitCode;
        this.userid = userid;
    }

    public String getS_score() {
        return s_score;
    }

    public void setS_score(String s_score) {
        this.s_score = s_score;
    }

    public String getL_score() {
        return l_score;
    }

    public void setL_score(String l_score) {
        this.l_score = l_score;
    }

    public String getF_score() {
        return f_score;
    }

    public void setF_score(String f_score) {
        this.f_score = f_score;
    }

    public String getR_score() {
        return r_score;
    }

    public void setR_score(String r_score) {
        this.r_score = r_score;
    }

    public String getP_score() {
        return p_score;
    }

    public void setP_score(String p_score) {
        this.p_score = p_score;
    }

    public String getW_score() {
        return w_score;
    }

    public void setW_score(String w_score) {
        this.w_score = w_score;
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
