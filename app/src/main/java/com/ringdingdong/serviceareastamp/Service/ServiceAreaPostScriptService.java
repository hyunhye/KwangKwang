package com.ringdingdong.serviceareastamp.Service;

import com.ringdingdong.serviceareastamp.Data.ServiceAreaFacilityAssessmentRepo;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaPostScriptRepo;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaUserFacilityAssessmentRepo;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaUserPostScript;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016-08-02.
 */
public interface ServiceAreaPostScriptService {
    @GET("/sarea/detail/review/{code}")
    Call<List<ServiceAreaPostScriptRepo>> listRepos(@Path("code") String code);

    @POST("/sarea/input/review")
    Call<ServiceAreaUserPostScript> createUserPostScript(@Body ServiceAreaUserPostScript posts);
}
