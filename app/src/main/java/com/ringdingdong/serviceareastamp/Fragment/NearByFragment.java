package com.ringdingdong.serviceareastamp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ringdingdong.serviceareastamp.Activity.NearByTabActivity;
import com.ringdingdong.serviceareastamp.Adapter.ListViewNearByAdapter;
import com.ringdingdong.serviceareastamp.Data.ListViewNearByItem;
import com.ringdingdong.serviceareastamp.Service.AllServiceAreaService;
import com.ringdingdong.serviceareastamp.R;
import com.ringdingdong.serviceareastamp.Data.AllServiceAreaRepo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NearByFragment extends Fragment {
    ListView listview ;
    ListViewNearByAdapter adapter;

    private static String nowAddress ="현재 위치를 확인 할 수 없습니다.";
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_near_by, container, false);
        adapter = new ListViewNearByAdapter();

        listview = (ListView) v.findViewById(R.id.listview_nearby);
        listview.setAdapter(adapter);


        // Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-52-78-118-118.ap-northeast-2.compute.amazonaws.com:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllServiceAreaService service = retrofit.create(AllServiceAreaService.class);

        Call<List<AllServiceAreaRepo>> repos = service.listRepos();


        // Fetch and print a list of the contributors to the library.
        if(android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        List<AllServiceAreaRepo> service_area_all_lists = null;
        try {
            service_area_all_lists = repos.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (AllServiceAreaRepo service_area : service_area_all_lists) {
            String address = service_area.getAddress();
            //address = getAddress(getContext(),service_area.getyValue(),service_area.getxValue());
            String score = "0.0";
            try {
                score = service_area.getAvg_total_score();
            } catch(NullPointerException e){
                score = "0.0";
            }

            adapter.addItem(service_area.getSarea_pic(), service_area.getUnitName(), address,score,service_area.getUnitCode()) ;
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewNearByItem item = (ListViewNearByItem) parent.getItemAtPosition(position) ;

                String name = item.getName() ;
                String code = item.getCode();

                // TODO : use item data.
                Intent intent = new Intent(getActivity(), NearByTabActivity.class);
                intent.putExtra("service_area_name",name);
                SharedPreferences pref = getActivity().getSharedPreferences("pref", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("service_area_code", code);
                editor.putString("service_area_name", name);
                editor.commit();
                startActivity(intent);
            }
        }) ;
        return v;
    }

    public static String getAddress(Context mContext, double lat, double lng) {

        Geocoder geocoder = new Geocoder(mContext, Locale.KOREA);
        List<Address> address;
        try {
            if (geocoder != null) {
                address = geocoder.getFromLocation(lat, lng, 1);
                if (address != null && address.size() > 0) {
                    // 주소 받아오기
                    String currentLocationAddress = address.get(0).getAddressLine(0).toString();
                    nowAddress = currentLocationAddress;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nowAddress;
    }
}
