package com.ringdingdong.serviceareastamp.Data;

/**
 * Created by Administrator on 2016-09-18.
 */
public class LoginRepo {
    String userid, nickname, user_pic;

    public LoginRepo(String userid, String nickname, String user_pic) {
        this.userid = userid;
        this.nickname = nickname;
        this.user_pic = user_pic;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
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
}
