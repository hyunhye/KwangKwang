package com.ringdingdong.serviceareastamp.Service;

import com.ringdingdong.serviceareastamp.Data.EachServiceAreaRepo;
import com.ringdingdong.serviceareastamp.Data.MyPageRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016-08-02.
 */
public interface MyPageService {
    @GET("/sarea/user/{userid}")
    Call<List<MyPageRepo>> listRepos(@Path("userid") String userid);
}
