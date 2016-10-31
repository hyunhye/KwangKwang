package com.ringdingdong.serviceareastamp.Service;

import com.ringdingdong.serviceareastamp.Data.AllServiceAreaRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016-08-02.
 */
public interface AllServiceAreaService {
    @GET("sarea/lists")
    Call<List<AllServiceAreaRepo>> listRepos();
}
