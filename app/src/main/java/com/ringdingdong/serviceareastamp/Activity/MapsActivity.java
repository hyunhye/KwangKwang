package com.ringdingdong.serviceareastamp.Activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.ringdingdong.serviceareastamp.CurrentPosition;
import com.ringdingdong.serviceareastamp.Fragment.LocationFragment;
import com.ringdingdong.serviceareastamp.Fragment.NearByFragment;
import com.ringdingdong.serviceareastamp.Fragment.SearchFragment;
import com.ringdingdong.serviceareastamp.GCM.QuickstartPreferences;
import com.ringdingdong.serviceareastamp.GCM.RegistrationIntentService;
import com.ringdingdong.serviceareastamp.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MapsActivity extends AppCompatActivity {
    private BottomBar bottomBar;
    private ActionBar actionBar;
    int mCurrentFragmentIndex;

    public final static int FRAGMENT_MAP = 0;
    public final static int FRAGMENT_NEAR_BY = 1;
    public final static int FRAGMENT_SEARCH = 2;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Fragment newFragment = null;


    // GCM
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MapsActivity";
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private Button mRegistrationButton;
    private ProgressBar mRegistrationProgressBar;
    private TextView mInformationTextView;

    private static final int UART_PROFILE_CONNECTED = 20;
    private static final int UART_PROFILE_DISCONNECTED = 21;
    private int mState = UART_PROFILE_DISCONNECTED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // get PERMISSION
        checkPermissions();

        //GCM
        registBroadcastReceiver();

        // Background Service
        Intent intent = new Intent(this, CurrentPosition.class);
        startService(intent);

        /*
        mInformationTextView = (TextView) findViewById(R.id.informationTextView);
        mInformationTextView.setVisibility(View.GONE);
        // 토큰을 가져오는 동안 인디케이터를 보여줄 ProgressBar를 정의
        mRegistrationProgressBar = (ProgressBar) findViewById(R.id.registrationProgressBar);
        mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
        // 토큰을 가져오는 Button을 정의
        mRegistrationButton = (Button) findViewById(R.id.registrationButton);
        mRegistrationButton.setOnClickListener(new View.OnClickListener() {

             // 버튼을 클릭하면 토큰을 가져오는 getInstanceIdToken() 메소드를 실행한다.
             // @param view
             
            @Override
            public void onClick(View view) {
                getInstanceIdToken();
            }
        });
        */

        // actionBar setting
        actionBar = getSupportActionBar();

        // init
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        // Fragment  Manage
        fragmentManager = this.getSupportFragmentManager();

        bottomBar.setOnTabSelectListener(new tabSelectedListener());
    }

    protected  void onResume(){
        super.onResume();

        // GCM
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_READY));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_GENERATING));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));

        bottomBar.setOnTabSelectListener(new tabSelectedListener());
    }

    class tabSelectedListener implements OnTabSelectListener{
        @Override
        public void onTabSelected(@IdRes int tabId) {
            if (tabId == R.id.tab_map) {
                setActionbarHide();

                mCurrentFragmentIndex = FRAGMENT_MAP;
                fragmentReplace(mCurrentFragmentIndex);

            } else if (tabId == R.id.tab_nearby) {
                setActionbarShow();

                mCurrentFragmentIndex = FRAGMENT_NEAR_BY;
                fragmentReplace(mCurrentFragmentIndex);

            } else if (tabId == R.id.tab_search) {
                setActionbarShow();

                mCurrentFragmentIndex = FRAGMENT_SEARCH;
                fragmentReplace(mCurrentFragmentIndex);
            }
        }
    }
    private void setActionbarShow(){
        actionBar.setShowHideAnimationEnabled(true);
        actionBar.setHomeButtonEnabled(false);
        actionBar.show();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar);
    }
    private void setActionbarHide(){
        actionBar.setShowHideAnimationEnabled(true);
        actionBar.setHomeButtonEnabled(false);
        actionBar.hide();
    }

    public void fragmentReplace(int reqNewFragmentIndex) {
        newFragment = getFragment(reqNewFragmentIndex);

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contentContainer,newFragment);

        // Commit the transaction
        transaction.commit();
    }

    private Fragment getFragment(int idx) {
        Fragment newFragment = null;
        switch (idx) {
            case FRAGMENT_MAP:
                newFragment = new LocationFragment();
                break;
            case FRAGMENT_NEAR_BY:
                newFragment = new NearByFragment();
                break;
            case FRAGMENT_SEARCH:
                newFragment = new SearchFragment();
                break;
            default:
                break;
        }
        return newFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.my_page:
                Intent intent = new Intent(MapsActivity.this, MyPageActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mState == UART_PROFILE_CONNECTED) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }
        else {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_error_black_24dp)
                    .setTitle(R.string.popup_title)
                    .setMessage(R.string.popup_message)
                    .setPositiveButton(R.string.popup_yes, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                        }
                    })
                    .setNegativeButton(R.string.popup_no, null)
                    .show();
        }
    }

    // get PERMISSION
    private void checkPermissions() {
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    /**
     * Instance ID를 이용하여 디바이스 토큰을 가져오는 RegistrationIntentService를 실행한다.
     */
    public void getInstanceIdToken() {
        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }

    /**
     * LocalBroadcast 리시버를 정의한다. 토큰을 획득하기 위한 READY, GENERATING, COMPLETE 액션에 따라 UI에 변화를 준다.
     */
    public void registBroadcastReceiver(){
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();


                if(action.equals(QuickstartPreferences.REGISTRATION_READY)){
                    // 액션이 READY일 경우
                    mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                    mInformationTextView.setVisibility(View.GONE);
                } else if(action.equals(QuickstartPreferences.REGISTRATION_GENERATING)){
                    // 액션이 GENERATING일 경우
                    mRegistrationProgressBar.setVisibility(ProgressBar.VISIBLE);
                    mInformationTextView.setVisibility(View.VISIBLE);
                    mInformationTextView.setText(getString(R.string.registering_message_generating));
                } else if(action.equals(QuickstartPreferences.REGISTRATION_COMPLETE)){
                    // 액션이 COMPLETE일 경우
                    mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                    mRegistrationButton.setText(getString(R.string.registering_message_complete));
                    mRegistrationButton.setEnabled(false);
                    String token = intent.getStringExtra("token");
                    mInformationTextView.setText(token);
                }

            }
        };
    }
    /**
     * 앱이 화면에서 사라지면 등록된 LocalBoardcast를 모두 삭제한다.
     */
    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


    /**
     * Google Play Service를 사용할 수 있는 환경이지를 체크한다.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

}
