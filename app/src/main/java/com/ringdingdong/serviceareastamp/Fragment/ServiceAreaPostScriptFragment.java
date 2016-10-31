package com.ringdingdong.serviceareastamp.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ringdingdong.serviceareastamp.Adapter.ListViewFacilityPostScriptAdapter;
import com.ringdingdong.serviceareastamp.Data.ListViewFacilityPostScriptItem;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaFacilityAssessmentRepo;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaPostScriptRepo;
import com.ringdingdong.serviceareastamp.R;
import com.ringdingdong.serviceareastamp.Service.ServiceAreaFacilityAssessmentService;
import com.ringdingdong.serviceareastamp.Service.ServiceAreaPostScriptService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceAreaPostScriptFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



    public ServiceAreaPostScriptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServiceAreaAssessmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServiceAreaPostScriptFragment newInstance(String param1, String param2) {
        ServiceAreaPostScriptFragment fragment = new ServiceAreaPostScriptFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_facility_post_script, container, false);

        ListView listview ;
        ListViewFacilityPostScriptAdapter adapter;

        adapter = new ListViewFacilityPostScriptAdapter();

        listview = (ListView) view.findViewById(R.id.listView_facility_post_script);
        listview.setAdapter(adapter);

        // Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-52-78-118-118.ap-northeast-2.compute.amazonaws.com:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceAreaPostScriptService service = retrofit.create(ServiceAreaPostScriptService.class);

        SharedPreferences pref = getActivity().getSharedPreferences("pref", 0);
        String service_area_code = pref.getString("service_area_code", "");

        Call<List<ServiceAreaPostScriptRepo>> repos = service.listRepos(service_area_code);


        // Fetch and print a list of the contributors to the library.
        if(android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        List<ServiceAreaPostScriptRepo> service_area_post_scripts = null;
        try {
            service_area_post_scripts = repos.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (ServiceAreaPostScriptRepo service_area_post_script : service_area_post_scripts) {
            adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_account_circle_black_24dp),
                    service_area_post_script.getComment1(), service_area_post_script.getNickname1(),
                    service_area_post_script.getTag1(),service_area_post_script.getTag2(),service_area_post_script.getTag3(),service_area_post_script.getDate1()) ;
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewFacilityPostScriptItem item = (ListViewFacilityPostScriptItem) parent.getItemAtPosition(position) ;

                String name = item.getName() ;
                // TODO : use item data.
            }
        }) ;

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

