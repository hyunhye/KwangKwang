package com.ringdingdong.serviceareastamp.Activity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ringdingdong.serviceareastamp.Data.AllServiceAreaRepo;
import com.ringdingdong.serviceareastamp.Data.ChildListExpressStampItem;
import com.ringdingdong.serviceareastamp.Adapter.CustomExpandableListViewExpressStampAdapter;
import com.ringdingdong.serviceareastamp.Data.EachServiceAreaRepo;
import com.ringdingdong.serviceareastamp.Data.MyPageRepo;
import com.ringdingdong.serviceareastamp.R;
import com.ringdingdong.serviceareastamp.Service.EachServiceAreaService;
import com.ringdingdong.serviceareastamp.Service.MyPageService;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyPageActivity extends AppCompatActivity {

    public ExpandableListView expandableListView;
    public CustomExpandableListViewExpressStampAdapter mCustomExpListViewAdapter;
    public ArrayList<String> parentList;
    public ArrayList<ChildListExpressStampItem> express;
    public HashMap<String, ArrayList<ChildListExpressStampItem>> childList;


    private TextView textView_my_page_name, textView_my_page_stamp_count;
    private ImageView imageView_my_page_profile_image;

    private ActionBar actionBar;
    String user_name;
    String user_id;
    String user_profile_image;

    private String stamp_list;
    private String[] stamp_values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        SharedPreferences pref = getSharedPreferences("pref", 0);
        try {
            user_name = pref.getString("user_name", "");
            user_id = pref.getString("user_id", "");
            user_profile_image = pref.getString("user_profile_image", "");
        } catch (NullPointerException e) {
            user_name = "홍길동";
            user_id = "0";
        }
        actionBar = getSupportActionBar();
        actionBar.setTitle(user_name);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        textView_my_page_name = (TextView) findViewById(R.id.textView_my_page_name);
        textView_my_page_stamp_count = (TextView) findViewById(R.id.textView_my_page_stamp_count);
        imageView_my_page_profile_image = (ImageView) findViewById(R.id.imageView_my_page_profile_image);

        //textView_my_page_name.setText(user_name);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-52-78-118-118.ap-northeast-2.compute.amazonaws.com:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyPageService service = retrofit.create(MyPageService.class);
        Call<List<MyPageRepo>> repos = service.listRepos(user_id);

        // Fetch and print a list of the contributors to the library.
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        List<MyPageRepo> user = null;
        try {
            user = repos.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (MyPageRepo u : user) {
            textView_my_page_name.setText(u.getNickname());
            textView_my_page_stamp_count.setText(u.getStamp_count());

            stamp_list = u.getStamp_list();
        }
        try {
            URL url = new URL(user_profile_image);
            URLConnection conn = url.openConnection();
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            imageView_my_page_profile_image.setImageBitmap(bm);
        } catch (Exception e) {
        }

        Log.d("hyunhye_stamp_list", stamp_list);
        stamp_values = stamp_list.split(",");

        // 휴게소 리스트
        parentList = new ArrayList<String>();

        // Default
        parentList.add("경기도");
        parentList.add("강원도");
        parentList.add("경상도");
        parentList.add("전라도");
        parentList.add("충청도");

        childList = new HashMap<String, ArrayList<ChildListExpressStampItem>>();

        for (String parent : parentList) {  // Retrofit
            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl("http://ec2-52-78-118-118.ap-northeast-2.compute.amazonaws.com:3000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            EachServiceAreaService service2 = retrofit2.create(EachServiceAreaService.class);
            Call<List<EachServiceAreaRepo>> repos2 = service2.listRepos(parent);

            // Fetch and print a list of the contributors to the library.
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            List<EachServiceAreaRepo> each_service_area = null;
            try {
                each_service_area = repos2.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            express = new ArrayList<ChildListExpressStampItem>();
            int j;
            for (EachServiceAreaRepo service_area : each_service_area) {
                boolean check_stamp = false;
                for (j = 0; j < stamp_values.length; j++) {
                    Log.d("hyunhye_stamp",stamp_values[j]+""+service_area.getUnitCode());
                    if (stamp_values[j].equals(service_area.getUnitCode())) {
                        check_stamp = true;
                        break;
                    }
                }
                ChildListExpressStampItem item = new ChildListExpressStampItem(service_area.getUnitName(), check_stamp);
                express.add(item);

            } // end of each_service_area

            childList.put(parent, express);

        } // end of parentList

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        mCustomExpListViewAdapter = new CustomExpandableListViewExpressStampAdapter(getApplicationContext(), parentList, childList);
        expandableListView.setAdapter(mCustomExpListViewAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
