package com.ringdingdong.serviceareastamp.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ringdingdong.serviceareastamp.Data.ServiceAreaStampRepo;
import com.ringdingdong.serviceareastamp.Data.ServiceAreaUserFacilityAssessmentRepo;
import com.ringdingdong.serviceareastamp.Fragment.ServiceAreaAssessmentFragment;
import com.ringdingdong.serviceareastamp.Fragment.ServiceAreaIntroFragment;
import com.ringdingdong.serviceareastamp.Fragment.ServiceAreaPostScriptFragment;
import com.ringdingdong.serviceareastamp.R;
import com.ringdingdong.serviceareastamp.Service.ServiceAreaFacilityAssessmentService;
import com.ringdingdong.serviceareastamp.Service.ServiceAreaStampService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NearByTabActivity extends AppCompatActivity implements ServiceAreaIntroFragment.OnFragmentInteractionListener, ServiceAreaAssessmentFragment.OnFragmentInteractionListener, ServiceAreaPostScriptFragment.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    String service_area_name;
    String stamp_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_tab);

        // get service area name
        Intent intent = getIntent();
        service_area_name = intent.getStringExtra("service_area_name");
        SharedPreferences pref = getSharedPreferences("pref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("service_area_name", service_area_name);
        editor.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(service_area_name);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        stamp_check = pref.getString(service_area_name + "_stamp", "0");
        if (stamp_check.equals("1")) {
            fab.setImageResource(R.drawable.stamp2);
        } else {
            fab.setImageResource(R.drawable.stamp3);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (stamp_check.equals("1")) {
                    new AlertDialog.Builder(NearByTabActivity.this)
                            .setIcon(R.drawable.red_splash)
                            .setTitle(service_area_name)
                            .setMessage("이미 " + service_area_name + "에 스탬프를 찍으셨습니다.")
                            .setPositiveButton("확인", null)
                            .show();
                } else {*/
                    Intent intent = new Intent(NearByTabActivity.this, StampActivity.class);
                    startActivity(intent);
                //}
            }
        });

    }

    protected void onResume(){
        super.onResume();
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        SharedPreferences pref = getSharedPreferences("pref", 0);
        stamp_check = pref.getString(service_area_name + "_stamp", "0");
        if (stamp_check.equals("1")) {
            fab.setImageResource(R.drawable.stamp2);
        } else {
            fab.setImageResource(R.drawable.stamp3);
        }
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

        switch (id) {
            case R.id.my_page:
                Intent intent = new Intent(NearByTabActivity.this, MyPageActivity.class);
                startActivity(intent);
                break;
            case R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_service_area_intro, container, false);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    ServiceAreaIntroFragment serviceAreaIntroFragment = new ServiceAreaIntroFragment();
                    return serviceAreaIntroFragment;
                case 1:
                    ServiceAreaAssessmentFragment serviceAreaAssessmentFragment = new ServiceAreaAssessmentFragment();
                    return serviceAreaAssessmentFragment;
                case 2:
                    ServiceAreaPostScriptFragment serviceAreaPostScriptFragment = new ServiceAreaPostScriptFragment();
                    return serviceAreaPostScriptFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "소개";
                case 1:
                    return "평가";
                case 2:
                    return "후기";
            }
            return null;
        }
    }

}
