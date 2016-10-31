package com.ringdingdong.serviceareastamp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ringdingdong.serviceareastamp.Adapter.ListViewNearByAdapter;
import com.ringdingdong.serviceareastamp.Data.AllServiceAreaRepo;
import com.ringdingdong.serviceareastamp.Data.ListViewNearByItem;
import com.ringdingdong.serviceareastamp.Data.SearchResultRepo;
import com.ringdingdong.serviceareastamp.R;
import com.ringdingdong.serviceareastamp.Service.AllServiceAreaService;
import com.ringdingdong.serviceareastamp.Service.SearchResultService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchResultActivity extends AppCompatActivity {
    ListView listview ;
    ListViewNearByAdapter adapter;

    private String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        adapter = new ListViewNearByAdapter();

        listview = (ListView) findViewById(R.id.listview_search_result);
        listview.setAdapter(adapter);

        Intent intent = getIntent();
        keyword = intent.getStringExtra("keyword");

        // Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-52-78-118-118.ap-northeast-2.compute.amazonaws.com:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchResultService service = retrofit.create(SearchResultService.class);

        Call<List<SearchResultRepo>> repos = service.listRepos(keyword);


        // Fetch and print a list of the contributors to the library.
        if(android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        List<SearchResultRepo> results = null;
        try {
            results = repos.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (SearchResultRepo result : results) {
            String address = result.getAddress();
            //address = getAddress(getContext(),service_area.getyValue(),service_area.getxValue());
            String score = "0.0";
            try {
                score = result.getAvg_total_score();
            } catch(NullPointerException e){
                score = "0.0";
            }
            adapter.addItem(result.getSarea_pic(), result.getUnitName(), address,score,result.getUnitCode()) ;
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewNearByItem item = (ListViewNearByItem) parent.getItemAtPosition(position) ;

                String name = item.getName() ;
                String code = item.getCode();

                // TODO : use item data.
                Intent intent = new Intent(SearchResultActivity.this, NearByTabActivity.class);
                intent.putExtra("service_area_name",name);
                SharedPreferences pref = SearchResultActivity.this.getSharedPreferences("pref", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("service_area_code", code);
                editor.putString("service_area_name", name);
                editor.commit();
                startActivity(intent);
            }
        }) ;
    }
}
