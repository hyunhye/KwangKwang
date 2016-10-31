package com.ringdingdong.serviceareastamp.Service;

import com.ringdingdong.serviceareastamp.Data.SearchResultRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016-08-02.
 */
public interface FilterResultService {
    @GET("/sarea/search/{cnum}/{conveniences}")
    Call<List<SearchResultRepo>> listRepos(@Path("cnum") String cnum, @Path("conveniences") String conveniences);
}
