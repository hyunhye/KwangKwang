package com.ringdingdong.serviceareastamp.Service;

import com.ringdingdong.serviceareastamp.Data.AllServiceAreaRepo;
import com.ringdingdong.serviceareastamp.Data.SearchResultRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016-08-02.
 */
public interface SearchResultService {
    @GET("/sarea/search/{keyword}")
    Call<List<SearchResultRepo>> listRepos(@Path("keyword") String keyword);
}
