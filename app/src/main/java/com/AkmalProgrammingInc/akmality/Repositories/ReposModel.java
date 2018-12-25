package com.AkmalProgrammingInc.akmality.Repositories;

import com.google.gson.Gson;
import com.AkmalProgrammingInc.akmality.Application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReposModel implements ReposContract.IReposModel {
    @Override
    public void getRepos(String token, final OnReposLoadedCallback callback) {
        Retrofit retrofit = Application.getRetrofitGithubApi();

        ReposContract.ReposApi client = retrofit.create(ReposContract.ReposApi.class);

        Call call = client.getRepos(token);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                JSONArray repos = null;
                ArrayList<HashMap<String, String>> data = new ArrayList<>();
                try {
                    String gson = new Gson().toJson(response.body());
                    repos = new JSONArray(gson);

                    for (int i = 0; i < repos.length(); i++){
                        JSONObject repository = repos.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<>();

                        String name = repository.getString("name");
                        String description = repository.getString("description");

                        map.put("name", name);
                        map.put("description", description);

                        data.add(map);

                    }
                } catch (JSONException e) {
                    callback.onReposFailure(e.toString());
                }

                callback.onReposResponse(data);

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onReposFailure(call.toString());
            }
        });
    }
}
