package com.AkmalProgrammingInc.akmality.MainActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainActivityContract {

    public interface IMainModel{
        public interface UserInfoCallback{
            void onUserInfoResponse(HashMap<String, String> user);
            void onUserInfoFailure(String errorMessage);
        }

        void getUserInfo(String token, UserInfoCallback callback);
    }

    public interface IMainPresenter{
        void detachView();
        void loadUserInfo(String token);
    }

    public interface IMainView{
        void showToast(String text);
        void setUserInfo(String login, String url, String avatar_url);
    }

    public interface UserInfoApi{

        @GET("user")
        Call<Object> getUserInfo(@Query("access_token") String access_token);

    }
}
