package com.AkmalProgrammingInc.akmality.MainActivity;

import android.util.Log;

import com.google.gson.Gson;
import com.AkmalProgrammingInc.akmality.Application;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainModel implements MainActivityContract.IMainModel {

    @Override
    public void requestUser(String token, final UserInfoCallback callback) {
        Retrofit retrofit = Application.getRetrofitGithubApi();
        MainActivityContract.UserInfoApi client = retrofit.create(MainActivityContract.UserInfoApi.class);

        Call call = client.getUserInfo(token);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                JSONObject user;
                HashMap<String, String> data= new HashMap<>();
                String gson = new Gson().toJson(response.body());
                Log.d("LOG", gson);
                try {
                    user = new JSONObject(gson);

                    data.put("login", user.getString("login"));
                    data.put("url", user.getString("url"));
                    data.put("avatar_url", user.getString("avatar_url"));
                } catch (JSONException e) {
                    callback.onUserInfoFailure(e.toString());
                }

                callback.onUserInfoResponse(data);


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onUserInfoFailure(call.toString());
            }
        });
    }
}
