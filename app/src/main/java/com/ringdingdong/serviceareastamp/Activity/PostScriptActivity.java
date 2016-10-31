package com.ringdingdong.serviceareastamp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.ringdingdong.serviceareastamp.Data.ServiceAreaPostScriptRepo;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaStampRepo;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaUserPostScript;
import com.ringdingdong.serviceareastamp.R;
import com.ringdingdong.serviceareastamp.Service.ServiceAreaFacilityAssessmentService;
import com.ringdingdong.serviceareastamp.Service.ServiceAreaPostScriptService;
import com.ringdingdong.serviceareastamp.Service.ServiceAreaStampService;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostScriptActivity extends AppCompatActivity {

    private Button button_post_script_ok;
    private EditText editText_asssessment_tag1, editText_asssessment_tag2, editText_asssessment_tag3, editText_asssessment_post_script;
    private RatingBar ratingBar_service_area_assessment_all;

    private String service_area_code;
    private String user_name;
    private String user_id;

    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_script);

        actionBar = getSupportActionBar();
        SharedPreferences pref = getSharedPreferences("pref", 0);
        String service_area_name = pref.getString("service_area_name", "");
        actionBar.setTitle(service_area_name + "휴게소 후기쓰기");


        button_post_script_ok = (Button) findViewById(R.id.button_post_script_ok);
        editText_asssessment_tag1 = (EditText) findViewById(R.id.editText_asssessment_tag1);
        editText_asssessment_tag2 = (EditText) findViewById(R.id.editText_asssessment_tag2);
        editText_asssessment_tag3 = (EditText) findViewById(R.id.editText_asssessment_tag3);
        editText_asssessment_post_script = (EditText) findViewById(R.id.editText_asssessment_post_script);
        ratingBar_service_area_assessment_all = (RatingBar) findViewById(R.id.ratingBar_service_area_assessment_all);

        button_post_script_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://ec2-52-78-118-118.ap-northeast-2.compute.amazonaws.com:3000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ServiceAreaPostScriptService service = retrofit.create(ServiceAreaPostScriptService.class);
                SharedPreferences pref = getSharedPreferences("pref", 0);
                try{
                    service_area_code = pref.getString("service_area_code", "");
                    user_name = pref.getString("user_name", "");
                    user_id = pref.getString("user_id", "");
                }catch(NullPointerException e){
                    user_name = "홍길동";
                    user_id = "0";
                }

                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strCurDate = CurDateFormat.format(date);

                // tag1, tag2, tag3, total_score, s_score, l_score, f_score, r_score, p_score, w_score
                ServiceAreaUserPostScript user_assessment = new ServiceAreaUserPostScript(
                        service_area_code,
                        user_id,
                        user_name,
                        strCurDate,
                        editText_asssessment_post_script.getText().toString(),
                        editText_asssessment_tag1.getText().toString(),
                        editText_asssessment_tag2.getText().toString(),
                        editText_asssessment_tag3.getText().toString(),
                        Float.toString(ratingBar_service_area_assessment_all.getRating()));
                Call<ServiceAreaUserPostScript> call =  service.createUserPostScript(user_assessment);

                call.enqueue(new Callback<ServiceAreaUserPostScript>() {
                    @Override
                    public void onResponse(Call<ServiceAreaUserPostScript> call, Response<ServiceAreaUserPostScript> response) {

                    }

                    @Override
                    public void onFailure(Call<ServiceAreaUserPostScript> call, Throwable t) {

                    }
                });

                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.my_page:
                Intent intent = new Intent(PostScriptActivity.this, MyPageActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
