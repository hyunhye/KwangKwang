package com.ringdingdong.serviceareastamp.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.ringdingdong.serviceareastamp.Data.LoginRepo;
import com.ringdingdong.serviceareastamp.R;
import com.ringdingdong.serviceareastamp.Service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private static volatile Activity currentActivity = null;
    private static Context mContext = null;

    private SessionCallback callback;
    private ActionBar actionBar;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.hide();


        startActivity(new Intent(this,SplashActivity.class));

        currentActivity = this;
        mContext = getApplicationContext();

        // kakao
        try{
            KakaoSDK.init(new KakaoSDKAdapter());
        } catch (KakaoSDK.AlreadyInitializedException e){}
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    // kakao
    private static class KakaoSDKAdapter extends KakaoAdapter {
        /**
         * Session Config에 대해서는 default값들이 존재한다.
         * 필요한 상황에서만 override해서 사용하면 됨.
         * @return Session의 설정값.
         */
        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[] {AuthType.KAKAO_LOGIN_ALL};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Activity getTopActivity() {
                    return currentActivity;
                }

                @Override
                public Context getApplicationContext() {
                    return mContext;
                }
            };
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            UserManagement.requestMe(new MeResponseCallback() {

                @Override
                public void onFailure(ErrorResult errorResult) {
                    String message = "failed to get user info. msg=" + errorResult;
                    Logger.d(message);

                    ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                    if (result == ErrorCode.CLIENT_ERROR_CODE) {
                        finish();
                    } else {
                        redirectMainActivity();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                }

                @Override
                public void onNotSignedUp() {
                }

                @Override
                public void onSuccess(UserProfile userProfile) {
                    //로그인에 성공하면 로그인한 사용자의 일련번호, 닉네임, 이미지url등을 리턴합니다.
                    //사용자 ID는 보안상의 문제로 제공하지 않고 일련번호는 제공합니다.
                    pref = getSharedPreferences("pref", 0);
                    String first_login = pref.getString("first_login", "0");
                    if(first_login.equals("0")){
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("first_login", "1");
                        editor.putString("user_name", userProfile.getNickname());
                        editor.putString("user_id",Long.toString(userProfile.getId()));
                        editor.putString("user_profile_image",userProfile.getThumbnailImagePath());
                        editor.commit();

                        // Retrofit
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://ec2-52-78-118-118.ap-northeast-2.compute.amazonaws.com:3000/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        LoginService service = retrofit.create(LoginService.class);

                        // tag1, tag2, tag3, total_score, s_score, l_score, f_score, r_score, p_score, w_score
                        LoginRepo user = new LoginRepo(Long.toString(userProfile.getId()),userProfile.getNickname(),userProfile.getThumbnailImagePath());
                        Call<LoginRepo> call =  service.createLogin(user);

                        call.enqueue(new Callback<LoginRepo>() {
                            @Override
                            public void onResponse(Call<LoginRepo> call, Response<LoginRepo> response) {

                            }

                            @Override
                            public void onFailure(Call<LoginRepo> call, Throwable t) {

                            }
                        });
                    }
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }
    private void redirectMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    protected void redirectSignupActivity() {
        final Intent intent = new Intent(this, KakaoSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
    // end of kakao
}



