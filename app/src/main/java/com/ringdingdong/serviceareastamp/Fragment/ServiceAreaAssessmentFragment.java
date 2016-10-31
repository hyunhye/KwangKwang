package com.ringdingdong.serviceareastamp.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ringdingdong.serviceareastamp.R;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaFacilityAssessmentRepo;
import com.ringdingdong.serviceareastamp.Service.ServiceAreaFacilityAssessmentService;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaFoodAssessmentRepo;
import com.ringdingdong.serviceareastamp.Service.ServiceAreaFoodAssessmentService;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ServiceAreaAssessmentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ServiceAreaAssessmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceAreaAssessmentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ServiceAreaAssessmentFragment() {
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
    public static ServiceAreaAssessmentFragment newInstance(String param1, String param2) {
        ServiceAreaAssessmentFragment fragment = new ServiceAreaAssessmentFragment();
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
        View view = inflater.inflate(R.layout.fragment_service_area_assessment, container, false);

        // Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-52-78-118-118.ap-northeast-2.compute.amazonaws.com:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceAreaFacilityAssessmentService service = retrofit.create(ServiceAreaFacilityAssessmentService.class);

        SharedPreferences pref = getActivity().getSharedPreferences("pref", 0);
        String service_area_code = pref.getString("service_area_code", "");
        String service_area_avg_total_score = pref.getString("service_area_avg_total_score", "0.00");

        Call<List<ServiceAreaFacilityAssessmentRepo>> repos = service.listRepos(service_area_code);


        // Fetch and print a list of the contributors to the library.
        if(android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        List<ServiceAreaFacilityAssessmentRepo> service_area_facility_assesments = null;
        try {
            service_area_facility_assesments = repos.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView textView_service_area_assessment_number_persons = (TextView) view.findViewById(R.id.textView_service_area_assessment_number_persons);
        TextView textView_service_area_assessment_grade_number = (TextView) view.findViewById(R.id.textView_service_area_assessment_grade_number);
        RatingBar ratingBar_service_area_assessment_grade_number = (RatingBar) view.findViewById(R.id.ratingBar_service_area_assessment_grade_number);
        textView_service_area_assessment_grade_number.setText(service_area_avg_total_score);
        ratingBar_service_area_assessment_grade_number.setRating(Float.parseFloat(service_area_avg_total_score));

        RatingBar ratingBar_service_area_assessment_foodcort = (RatingBar) view.findViewById(R.id.ratingBar_service_area_assessment_foodcort);
        RatingBar ratingBar_service_area_assessment_park = (RatingBar) view.findViewById(R.id.ratingBar_service_area_assessment_park);
        RatingBar ratingBar_service_area_assessment_gas = (RatingBar) view.findViewById(R.id.ratingBar_service_area_assessment_gas);
        RatingBar ratingBar_service_area_assessment_toilet = (RatingBar) view.findViewById(R.id.ratingBar_service_area_assessment_toilet);
        RatingBar ratingBar_service_area_assessment_repaircenter = (RatingBar) view.findViewById(R.id.ratingBar_service_area_assessment_repaircenter);
        RatingBar ratingBar_service_area_assessment_wifi = (RatingBar) view.findViewById(R.id.ratingBar_service_area_assessment_wifi);


        TextView textView_service_area_assessment_foodcort_grade = (TextView) view.findViewById(R.id.textView_service_area_assessment_foodcort_grade);
        TextView textView_service_area_assessment_park_grade = (TextView) view.findViewById(R.id.textView_service_area_assessment_park_grade);
        TextView textView_service_area_assessment_gas_grade = (TextView) view.findViewById(R.id.textView_service_area_assessment_gas_grade);
        TextView textView_service_area_assessment_toilet_grade = (TextView) view.findViewById(R.id.textView_service_area_assessment_toilet_grade);
        TextView textView_service_area_assessment_repaircenter_grade = (TextView) view.findViewById(R.id.textView_service_area_assessment_repaircenter_grade);
        TextView textView_service_area_assessment_wifi_grade = (TextView) view.findViewById(R.id.textView_service_area_assessment_wifi_grade);


        for (ServiceAreaFacilityAssessmentRepo service_area_facility_assesment : service_area_facility_assesments) {
            textView_service_area_assessment_number_persons.setText(service_area_facility_assesment.getCount() + "명 평가");

            DecimalFormat form = new DecimalFormat("#.00");
            ratingBar_service_area_assessment_foodcort.setRating(Float.parseFloat(form.format(Float.parseFloat(service_area_facility_assesment.getFs()))));
            textView_service_area_assessment_foodcort_grade.setText(form.format(Float.parseFloat(service_area_facility_assesment.getFs())));
            ratingBar_service_area_assessment_park.setRating(Float.parseFloat(form.format(Float.parseFloat(service_area_facility_assesment.getPs()))));
            textView_service_area_assessment_park_grade.setText(form.format(Float.parseFloat(service_area_facility_assesment.getPs())));
            ratingBar_service_area_assessment_gas.setRating(Float.parseFloat(form.format(Float.parseFloat(service_area_facility_assesment.getLs()))));
            textView_service_area_assessment_gas_grade.setText(form.format(Float.parseFloat(service_area_facility_assesment.getLs())));
            ratingBar_service_area_assessment_toilet.setRating(Float.parseFloat(form.format(Float.parseFloat(service_area_facility_assesment.getRs()))));
            textView_service_area_assessment_toilet_grade.setText(form.format(Float.parseFloat(service_area_facility_assesment.getRs())));
            ratingBar_service_area_assessment_repaircenter.setRating(Float.parseFloat(form.format(Float.parseFloat(service_area_facility_assesment.getSs()))));
            textView_service_area_assessment_repaircenter_grade.setText(form.format(Float.parseFloat(service_area_facility_assesment.getSs())));
            ratingBar_service_area_assessment_wifi.setRating(Float.parseFloat(form.format(Float.parseFloat(service_area_facility_assesment.getWs()))));
            textView_service_area_assessment_wifi_grade.setText(form.format(Float.parseFloat(service_area_facility_assesment.getWs())));
        }

        ServiceAreaFoodAssessmentService service2 = retrofit.create(ServiceAreaFoodAssessmentService.class);
        Call<List<ServiceAreaFoodAssessmentRepo>> repos2 = service2.listRepos(service_area_code);


        // Fetch and print a list of the contributors to the library.
        if(android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        List<ServiceAreaFoodAssessmentRepo> service_area_food_assesments = null;
        try {
            service_area_food_assesments = repos2.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RatingBar ratingBar_service_area_food_assessment_indoor_first = (RatingBar) view.findViewById(R.id.ratingBar_service_area_food_assessment_indoor_first);
        RatingBar ratingBar_service_area_food_assessment_indoor_second = (RatingBar) view.findViewById(R.id.ratingBar_service_area_food_assessment_indoor_second);
        RatingBar ratingBar_service_area_food_assessment_indoor_third = (RatingBar) view.findViewById(R.id.ratingBar_service_area_food_assessment_indoor_third);
        RatingBar ratingBar_service_area_food_assessment_indoor_fourth = (RatingBar) view.findViewById(R.id.ratingBar_service_area_food_assessment_indoor_fourth);
        RatingBar ratingBar_service_area_food_assessment_indoor_fifth = (RatingBar) view.findViewById(R.id.ratingBar_service_area_food_assessment_indoor_fifth);

        TextView textView_service_area_food_assessment_indoor_first = (TextView) view.findViewById(R.id.textView_service_area_food_assessment_indoor_first);
        TextView textView_service_area_food_assessment_indoor_second = (TextView) view.findViewById(R.id.textView_service_area_food_assessment_indoor_second);
        TextView textView_service_area_food_assessment_indoor_third = (TextView) view.findViewById(R.id.textView_service_area_food_assessment_indoor_third);
        TextView textView_service_area_food_assessment_indoor_fourth = (TextView) view.findViewById(R.id.textView_service_area_food_assessment_indoor_fourth);
        TextView textView_service_area_food_assessment_indoor_fifth = (TextView) view.findViewById(R.id.textView_service_area_food_assessment_indoor_fifth);

        TextView textView_service_area_food_assessment_indoor_first_grade = (TextView) view.findViewById(R.id.textView_service_area_food_assessment_indoor_first_grade);
        TextView textView_service_area_food_assessment_indoor_second_grade = (TextView) view.findViewById(R.id.textView_service_area_food_assessment_indoor_second_grade);
        TextView textView_service_area_food_assessment_indoor_third_grade = (TextView) view.findViewById(R.id.textView_service_area_food_assessment_indoor_third_grade);
        TextView textView_service_area_food_assessment_indoor_fourth_grade = (TextView) view.findViewById(R.id.textView_service_area_food_assessment_indoor_fourth_grade);
        TextView textView_service_area_food_assessment_indoor_fifth_grade = (TextView) view.findViewById(R.id.textView_service_area_food_assessment_indoor_fifth_grade);

        int count = 0;
        try{
            count = service_area_food_assesments.size();
        } catch (NullPointerException e){
            count = 0;
        }
        switch (count){
            case 0:
                break;
            case 1:
                setFoodAssessment(textView_service_area_food_assessment_indoor_first,textView_service_area_food_assessment_indoor_first_grade,ratingBar_service_area_food_assessment_indoor_first, service_area_food_assesments, 0);
                break;
            case 2:
                setFoodAssessment(textView_service_area_food_assessment_indoor_first,textView_service_area_food_assessment_indoor_first_grade,ratingBar_service_area_food_assessment_indoor_first, service_area_food_assesments, 0);
                setFoodAssessment(textView_service_area_food_assessment_indoor_second,textView_service_area_food_assessment_indoor_second_grade,ratingBar_service_area_food_assessment_indoor_second, service_area_food_assesments, 1);
                break;
            case 3:
                setFoodAssessment(textView_service_area_food_assessment_indoor_first,textView_service_area_food_assessment_indoor_first_grade,ratingBar_service_area_food_assessment_indoor_first, service_area_food_assesments, 0);
                setFoodAssessment(textView_service_area_food_assessment_indoor_second,textView_service_area_food_assessment_indoor_second_grade,ratingBar_service_area_food_assessment_indoor_second, service_area_food_assesments, 1);
                setFoodAssessment(textView_service_area_food_assessment_indoor_third,textView_service_area_food_assessment_indoor_third_grade,ratingBar_service_area_food_assessment_indoor_third, service_area_food_assesments, 2);
                break;
            case 4:
                setFoodAssessment(textView_service_area_food_assessment_indoor_first,textView_service_area_food_assessment_indoor_first_grade,ratingBar_service_area_food_assessment_indoor_first, service_area_food_assesments, 0);
                setFoodAssessment(textView_service_area_food_assessment_indoor_second,textView_service_area_food_assessment_indoor_second_grade,ratingBar_service_area_food_assessment_indoor_second, service_area_food_assesments, 1);
                setFoodAssessment(textView_service_area_food_assessment_indoor_third,textView_service_area_food_assessment_indoor_third_grade,ratingBar_service_area_food_assessment_indoor_third, service_area_food_assesments, 2);
                setFoodAssessment(textView_service_area_food_assessment_indoor_fourth,textView_service_area_food_assessment_indoor_fourth_grade,ratingBar_service_area_food_assessment_indoor_fourth, service_area_food_assesments, 3);
                break;
            case 5:
                setFoodAssessment(textView_service_area_food_assessment_indoor_first,textView_service_area_food_assessment_indoor_first_grade,ratingBar_service_area_food_assessment_indoor_first, service_area_food_assesments, 0);
                setFoodAssessment(textView_service_area_food_assessment_indoor_second,textView_service_area_food_assessment_indoor_second_grade,ratingBar_service_area_food_assessment_indoor_second, service_area_food_assesments, 1);
                setFoodAssessment(textView_service_area_food_assessment_indoor_third,textView_service_area_food_assessment_indoor_third_grade,ratingBar_service_area_food_assessment_indoor_third, service_area_food_assesments, 2);
                setFoodAssessment(textView_service_area_food_assessment_indoor_fourth,textView_service_area_food_assessment_indoor_fourth_grade,ratingBar_service_area_food_assessment_indoor_fourth, service_area_food_assesments, 3);
                setFoodAssessment(textView_service_area_food_assessment_indoor_fifth,textView_service_area_food_assessment_indoor_fifth_grade,ratingBar_service_area_food_assessment_indoor_fifth, service_area_food_assesments, 4);
                break;
        }


        return view;
    }

    private void setFoodAssessment(TextView food, TextView score, RatingBar score_rating,  List<ServiceAreaFoodAssessmentRepo> service_area_food_assessments, int count){
        food.setText(service_area_food_assessments.get(count).getFood());
        DecimalFormat form = new DecimalFormat("#.00");
        score.setText(form.format(Float.parseFloat(service_area_food_assessments.get(count).getScore())));

        try{
            score_rating.setRating(Float.parseFloat(form.format(Float.parseFloat(service_area_food_assessments.get(count).getScore()))));
        } catch (NullPointerException e){
            score_rating.setRating(Float.parseFloat(form.format(0.0)));
        }
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
