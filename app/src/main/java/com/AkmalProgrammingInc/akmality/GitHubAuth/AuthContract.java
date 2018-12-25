package com.AkmalProgrammingInc.akmality.GitHubAuth;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthContract {

    public interface IAuthView{
        void authorizeUser(String client_id, String redirect_url);
        String getToken();
        void saveToken(String token);
        void showMainActivity(String token);
        void showError();
        void showToast(String message);
    }

    public interface IAuthPresenter{
        void attachView(IAuthView view);
        void detachView();
        void onLoginClicked();
        void authSuccess(String code);
        void authError();
        String getRedirectUrl();
    }

    public interface IGithubModel{
        public interface GetAccesTokenCallback{
            void onGetTokenSuccess(String token);
            void onGetTokenFailure(String errorMessage);
        }

        void getAccessToken(String clientId, String clientSecret, String code, GetAccesTokenCallback callback);
    }

    public interface GithubClient{

        @Headers("Accept: application/json")
        @POST("login/oauth/access_token")
        @FormUrlEncoded
        Call<AccessToken> getAccessToken(
                @Field("client_id") String client_id,
                @Field("client_secret") String client_secret,
                @Field("code") String code
        );

    }
}
