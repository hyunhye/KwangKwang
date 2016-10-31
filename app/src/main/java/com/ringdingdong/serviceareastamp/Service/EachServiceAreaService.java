package com.ringdingdong.serviceareastamp.Service;

import com.ringdingdong.serviceareastamp.Data.EachServiceAreaRepo;
import com.ringdingdong.serviceareastamp.Data.LoginRepo;
import com.ringdingdong.serviceareastamp.Data.MyPageRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016-08-02.
 */
public interface EachServiceAreaService {
    @GET("/sarea/search_region/{region}")
    Call<List<EachServiceAreaRepo>> listRepos(@Path("region") String region);
}
