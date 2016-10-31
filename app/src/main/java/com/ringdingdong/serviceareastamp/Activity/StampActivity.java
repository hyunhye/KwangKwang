package com.ringdingdong.serviceareastamp.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.ringdingdong.serviceareastamp.Data.ServiceAreaStampRepo;
import com.ringdingdong.serviceareastamp.R;
import com.ringdingdong.serviceareastamp.Service.ServiceAreaStampService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StampActivity extends AppCompatActivity {

    ImageButton imageButton_stamp;

    ActionBar actionBar;

    SharedPreferences pref;
    String service_area_code;
    String service_area_name;
    String user_id;
    String stamp_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);

        actionBar = getSupportActionBar();
        actionBar.hide();

        imageButton_stamp = (ImageButton) findViewById(R.id.imageButton_stamp);

        pref = getSharedPreferences("pref", 0);
        service_area_code = pref.getString("service_area_code", "");
        service_area_name = pref.getString("service_area_name", "");
        user_id = pref.getString("user_id", "");

        stamp_check = pref.getString(service_area_name+"_stamp", "0");
        if(stamp_check.equals("1")) {
            imageButton_stamp.setImageResource(R.drawable.stamp_ok);
        }else{
            imageButton_stamp.setImageResource(R.drawable.stamp_not);
        }

        imageButton_stamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton_stamp.setImageResource(R.drawable.stamp_ok);
                if(stamp_check.equals("1")) {
                    new AlertDialog.Builder(StampActivity.this)
                            .setIcon(R.drawable.red_splash)
                            .setTitle(service_area_name)
                            .setMessage("이미 " + service_area_name + "에 스탬프를 찍으셨습니다.")
                            .setPositiveButton("★평가하기★", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(StampActivity.this, AssessmentTabActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .setNegativeButton("후기쓰기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(StampActivity.this, PostScriptActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .show();
                }
                // Retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://ec2-52-78-118-118.ap-northeast-2.compute.amazonaws.com:3000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ServiceAreaStampService service = retrofit.create(ServiceAreaStampService.class);

                // tag1, tag2, tag3, total_score, s_score, l_score, f_score, r_score, p_score, w_score
                ServiceAreaStampRepo stamp = new ServiceAreaStampRepo(
                        "1",
                        service_area_code,
                        user_id);
                Call<ServiceAreaStampRepo> call =  service.createStamp(stamp);

                call.enqueue(new Callback<ServiceAreaStampRepo>() {
                    @Override
                    public void onResponse(Call<ServiceAreaStampRepo> call, Response<ServiceAreaStampRepo> response) {

                    }

                    @Override
                    public void onFailure(Call<ServiceAreaStampRepo> call, Throwable t) {

                    }
                });

                SharedPreferences.Editor editor = pref.edit();
                editor.putString(service_area_name+"_stamp", "1");
                editor.commit();
                new AlertDialog.Builder(StampActivity.this)
                        .setIcon(R.drawable.red_splash)
                        .setTitle(service_area_name)
                        .setMessage(service_area_name+"에 스탬프를 찍으셨습니다.")
                        .setPositiveButton("★평가하기★", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(StampActivity.this, AssessmentTabActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNeutralButton("확인",  new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("후기쓰기", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(StampActivity.this, PostScriptActivity.class);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });
    }
}
