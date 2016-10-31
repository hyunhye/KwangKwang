package com.ringdingdong.serviceareastamp.Service;

import com.ringdingdong.serviceareastamp.Data.LoginRepo;
import com.ringdingdong.serviceareastamp.Data.MyPageRepo;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaStampRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016-08-02.
 */
public interface LoginService {
    @GET("/sarea/user/{code}")
    Call<List<MyPageRepo>> listRepos(@Path("code") String code);

    @POST("/sarea/signup")
    Call<LoginRepo> createLogin(@Body LoginRepo login);
}
