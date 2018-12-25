package com.AkmalProgrammingInc.akmality.Repositories;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReposContract {

    public interface IReposModel{
        public interface OnReposLoadedCallback{
            void onReposResponse(ArrayList<HashMap<String, String>> data);
            void onReposFailure(String errorMessage);
        }

        void getRepos(String token, OnReposLoadedCallback callback);
    }

    public interface IReposPresenter{
        void attachView(IReposView view);
        void detachView();
        void loadRepos(String token);

    }

    public interface IReposView{
        void showToast(String message);
        void setData(ArrayList<HashMap<String, String>> data);
    }


    public interface ReposApi {

        @GET("user/repos")
        Call<Object> getRepos(@Query("access_token") String access_token);
    }

}
