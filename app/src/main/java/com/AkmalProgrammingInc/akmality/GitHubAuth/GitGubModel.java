package com.AkmalProgrammingInc.akmality.GitHubAuth;

import com.AkmalProgrammingInc.akmality.Application;
import com.AkmalProgrammingInc.akmality.GithubClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GitGubModel implements AuthContract.IGithubModel {

    @Override
    public void getAccessToken(String client_id, String clientSecret, String code, final GetAccesTokenCallback callback) {

        GithubClient client = Application.getRetrofitGithub()
                .create(GithubClient.class);

        Call<AccessToken> accessTokenCall = client.getAccessToken(
                client_id,
                clientSecret,
                code
        );

        accessTokenCall.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.body() != null) {
                    callback.onGetTokenSuccess(response.body().getAccessToken());
                }else{
                    callback.onGetTokenFailure("Empty body");
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                callback.onGetTokenFailure(t.toString());
            }
        });

    }
}
