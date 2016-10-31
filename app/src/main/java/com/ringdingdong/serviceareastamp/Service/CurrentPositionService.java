package com.ringdingdong.serviceareastamp.Service;

import com.ringdingdong.serviceareastamp.Data.CurrentPositionRepo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016-08-02.
 */
public interface CurrentPositionService {
    @POST("current_position")
    Call<CurrentPositionRepo> createCurrentPosition(@Body CurrentPositionRepo position);
}
