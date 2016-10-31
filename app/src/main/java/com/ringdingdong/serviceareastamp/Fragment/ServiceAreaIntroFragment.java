package com.ringdingdong.serviceareastamp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ringdingdong.serviceareastamp.Activity.AssessmentTabActivity;
import com.ringdingdong.serviceareastamp.Activity.PostScriptActivity;
import com.ringdingdong.serviceareastamp.R;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaRepo;
import com.ringdingdong.serviceareastamp.Service.ServiceAreaService;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ServiceAreaIntroFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ServiceAreaIntroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceAreaIntroFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ServiceAreaIntroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServiceAreaIntroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServiceAreaIntroFragment newInstance(String param1, String param2) {
        ServiceAreaIntroFragment fragment = new ServiceAreaIntroFragment();
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
        View view = inflater.inflate(R.layout.fragment_service_area_intro, container, false);

        Button button_service_area_assessment_write = (Button) view.findViewById(R.id.button_service_area_assessment_write);
        Button button_service_area_post_script_write = (Button) view.findViewById(R.id.button_service_area_post_script_write);
        TextView textView_service_area_intro_name = (TextView) view.findViewById(R.id.textView_service_area_intro_name);

        SharedPreferences pref = getActivity().getSharedPreferences("pref", 0);
        String service_area_code = pref.getString("service_area_code", "");
        String service_area_name = pref.getString("service_area_name", "");
        textView_service_area_intro_name.setText(service_area_name);

        button_service_area_assessment_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AssessmentTabActivity.class);
                startActivity(intent);
            }
        });
        button_service_area_post_script_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostScriptActivity.class);
                startActivity(intent);
            }
        });


        // Retrofit
        // get service area info
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-52-78-118-118.ap-northeast-2.compute.amazonaws.com:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceAreaService service = retrofit.create(ServiceAreaService.class);

        Call<List<ServiceAreaRepo>> repos = service.listRepos(service_area_code);


        // Fetch and print a list of the contributors to the library.
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        List<ServiceAreaRepo> service_area_infos = null;
        try {
            service_area_infos = repos.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String image = "";
        String address = "";
        String phone = "000-000-000";
        String convenience = "";
        String gasolinePrice = "";
        String lpgPrice = "";
        String batchMenu = "";
        String avg_total_score = "0.0";

        ImageView imageView_service_area_intro_image = (ImageView) view.findViewById(R.id.imageView_service_area_intro_image);
        RatingBar ratingBar_service_area_intro_star = (RatingBar) view.findViewById(R.id.ratingBar_service_area_intro_star);
        TextView textView_service_area_intro_address = (TextView) view.findViewById(R.id.textView_service_area_intro_address);
        TextView textView_service_area_intro_phone = (TextView) view.findViewById(R.id.textView_service_area_intro_phone);
        TextView textView_service_area_intro_facility = (TextView) view.findViewById(R.id.textView_service_area_intro_facility);
        TextView textView_service_area_intro_gas = (TextView) view.findViewById(R.id.textView_service_area_intro_gas);
        TextView textView_service_area_intro_food = (TextView) view.findViewById(R.id.textView_service_area_intro_food);
        TextView textView_service_area_intro_grade_number = (TextView) view.findViewById(R.id.textView_service_area_intro_grade_number);

        for (ServiceAreaRepo service_area_info : service_area_infos) {
            image = service_area_info.getSarea_pic();
            address = service_area_info.getAddress();
            phone = service_area_info.getPhone();
            convenience = service_area_info.getConvenience();
            gasolinePrice = service_area_info.getGasolinePrice();
            lpgPrice = service_area_info.getLpgPrice();
            batchMenu = service_area_info.getBatchMenu();
            try {
                avg_total_score = service_area_info.getAvg_total_score();
            } catch (NullPointerException e) {
                avg_total_score = "0.0";
            }
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("service_area_avg_total_score", avg_total_score);
            editor.commit();
        }

        try {
            URL url = new URL(image);
            URLConnection conn = url.openConnection();
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            imageView_service_area_intro_image.setImageBitmap(bm);
        } catch (Exception e) {
        }
        textView_service_area_intro_address.setText(address);
        textView_service_area_intro_phone.setText(phone);
        textView_service_area_intro_facility.setText(convenience);
        textView_service_area_intro_gas.setText("휘발유-" + gasolinePrice + "/\nLPG-" + lpgPrice + "/");
        textView_service_area_intro_food.setText(batchMenu);
        textView_service_area_intro_grade_number.setText(avg_total_score);
        try {
            ratingBar_service_area_intro_star.setRating(Float.parseFloat(avg_total_score));
        } catch (NullPointerException e) {
            ratingBar_service_area_intro_star.setRating(0);
        }

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
