package com.ringdingdong.serviceareastamp.Data;

/**
 * Created by Administrator on 2016-09-18.
 */
public class MyPageRepo {
    private String nickname, stamp_count, stamp_list;

    public MyPageRepo(String nickname, String stamp_count, String stamp_list) {
        this.nickname = nickname;
        this.stamp_count = stamp_count;
        this.stamp_list = stamp_list;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStamp_count() {
        return stamp_count;
    }

    public void setStamp_count(String stamp_count) {
        this.stamp_count = stamp_count;
    }

    public String getStamp_list() {
        return stamp_list;
    }

    public void setStamp_list(String stamp_list) {
        this.stamp_list = stamp_list;
    }
}
