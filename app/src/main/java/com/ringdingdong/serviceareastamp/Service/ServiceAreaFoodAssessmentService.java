package com.ringdingdong.serviceareastamp.Service;

import com.ringdingdong.serviceareastamp.Data.ServiceAreaFoodAssessmentRepo;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaUserFacilityAssessmentRepo;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaUserFoodAssessmentRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016-08-02.
 */
public interface ServiceAreaFoodAssessmentService {
    @GET("sarea/detail/food2/{code}")
    Call<List<ServiceAreaFoodAssessmentRepo>> listRepos(@Path("code") String code);
    @POST("/sarea/input/food_check")
    Call<ServiceAreaUserFoodAssessmentRepo> createFoodAssesment(@Body ServiceAreaUserFoodAssessmentRepo assessment);
}
