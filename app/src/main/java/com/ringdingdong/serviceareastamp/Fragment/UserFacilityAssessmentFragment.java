package com.ringdingdong.serviceareastamp.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.ringdingdong.serviceareastamp.Data.ServiceAreaUserFacilityAssessmentRepo;
import com.ringdingdong.serviceareastamp.R;
import com.ringdingdong.serviceareastamp.Service.ServiceAreaFacilityAssessmentService;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserFacilityAssessmentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserFacilityAssessmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFacilityAssessmentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UserFacilityAssessmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFacilityAssessmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFacilityAssessmentFragment newInstance(String param1, String param2) {
        UserFacilityAssessmentFragment fragment = new UserFacilityAssessmentFragment();
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


    private Button button_facility_assessment_ok;
    private RatingBar ratingBar_service_area_assessment_foodcort, ratingBar_service_area_assessment_park,ratingBar_service_area_assessment_gas,ratingBar_service_area_assessment_toilet;
    private RatingBar ratingBar_service_area_assessment_repaircenter, ratingBar_service_area_assessment_wifi;

    private String service_area_code;
    private String user_name;
    private String user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_user_facility_assessment, container, false);


        button_facility_assessment_ok = (Button) view.findViewById(R.id.button_facility_assessment_ok);
        ratingBar_service_area_assessment_foodcort = (RatingBar) view.findViewById(R.id.ratingBar_service_area_assessment_foodcort);
        ratingBar_service_area_assessment_park = (RatingBar) view.findViewById(R.id.ratingBar_service_area_assessment_park);
        ratingBar_service_area_assessment_gas = (RatingBar) view.findViewById(R.id.ratingBar_service_area_assessment_gas);
        ratingBar_service_area_assessment_toilet = (RatingBar) view.findViewById(R.id.ratingBar_service_area_assessment_toilet);
        ratingBar_service_area_assessment_repaircenter = (RatingBar) view.findViewById(R.id.ratingBar_service_area_assessment_repaircenter);
        ratingBar_service_area_assessment_wifi = (RatingBar) view.findViewById(R.id.ratingBar_service_area_assessment_wifi);

        button_facility_assessment_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://ec2-52-78-118-118.ap-northeast-2.compute.amazonaws.com:3000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ServiceAreaFacilityAssessmentService service = retrofit.create(ServiceAreaFacilityAssessmentService.class);
                SharedPreferences pref = getActivity().getSharedPreferences("pref", 0);
                try{
                    service_area_code = pref.getString("service_area_code", "");
                    user_name = pref.getString("user_name", "");
                    user_id = pref.getString("user_id", "");
                }catch(NullPointerException e){
                    user_name = "홍길동";
                    user_id = "0";
                }

                // tag1, tag2, tag3, total_score, s_score, l_score, f_score, r_score, p_score, w_score
                ServiceAreaUserFacilityAssessmentRepo user_assessment = new ServiceAreaUserFacilityAssessmentRepo(
                        Float.toString(ratingBar_service_area_assessment_foodcort.getRating()),
                        Float.toString(ratingBar_service_area_assessment_park.getRating()),
                        Float.toString(ratingBar_service_area_assessment_gas.getRating()),
                        Float.toString(ratingBar_service_area_assessment_toilet.getRating()),
                        Float.toString(ratingBar_service_area_assessment_repaircenter.getRating()),
                        Float.toString(ratingBar_service_area_assessment_wifi.getRating()),
                        service_area_code,
                        user_id);
                Call<ServiceAreaUserFacilityAssessmentRepo> call =  service.createFacilityAssesment(user_assessment);

                call.enqueue(new Callback<ServiceAreaUserFacilityAssessmentRepo>() {
                    @Override
                    public void onResponse(Call<ServiceAreaUserFacilityAssessmentRepo> call, Response<ServiceAreaUserFacilityAssessmentRepo> response) {

                    }

                    @Override
                    public void onFailure(Call<ServiceAreaUserFacilityAssessmentRepo> call, Throwable t) {

                    }
                });

                getActivity().finish();
            }
        });

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
