package com.ringdingdong.serviceareastamp.Service;

import com.ringdingdong.serviceareastamp.Data.ServiceAreaFacilityAssessmentRepo;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaStampRepo;
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
public interface ServiceAreaStampService {

    @POST("sarea/input/stamp")
    Call<ServiceAreaStampRepo> createStamp(@Body ServiceAreaStampRepo stamp);
}
