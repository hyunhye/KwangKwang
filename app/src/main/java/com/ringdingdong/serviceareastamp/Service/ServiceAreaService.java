package com.ringdingdong.serviceareastamp.Service;

import com.ringdingdong.serviceareastamp.Data.ServiceAreaRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016-08-02.
 */
public interface ServiceAreaService {
    @GET("sarea/detail/info/{code}")
    Call<List<ServiceAreaRepo>> listRepos(@Path("code") String code);
}
