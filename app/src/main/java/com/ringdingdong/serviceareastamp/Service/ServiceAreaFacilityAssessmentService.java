package com.ringdingdong.serviceareastamp.Service;

import com.ringdingdong.serviceareastamp.Data.ServiceAreaFacilityAssessmentRepo;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaUserFacilityAssessmentRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016-08-02.
 */
public interface ServiceAreaFacilityAssessmentService {
    @GET("sarea/detail/con/{code}")
    Call<List<ServiceAreaFacilityAssessmentRepo>> listRepos(@Path("code") String code);

    @POST("/sarea/input/con_check")
    Call<ServiceAreaUserFacilityAssessmentRepo> createFacilityAssesment(@Body ServiceAreaUserFacilityAssessmentRepo assessment);
}
