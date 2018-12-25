package com.AkmalProgrammingInc.akmality;

import com.yandex.mapkit.MapKitFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Application extends android.app.Application {

    private static Retrofit retrofitGithub, retrofitGithubApi;
    private final String baseUrlGithub = "https://github.com/";
    private final String baseUrlGithubApi = "https://api.github.com/";
    private final String API_MAP_KEY = "0ff9558e-a3a6-48bd-870b-6f253e5302ae";
    public static final String PREF_TOKEN = "token";

    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey(API_MAP_KEY);


        retrofitGithub = new Retrofit.Builder()
                .baseUrl(baseUrlGithub)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitGithubApi = new Retrofit.Builder()
                .baseUrl(baseUrlGithubApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static Retrofit getRetrofitGithub(){
        return retrofitGithub;
    }

    public static Retrofit getRetrofitGithubApi(){
        return retrofitGithubApi;
    }
}
