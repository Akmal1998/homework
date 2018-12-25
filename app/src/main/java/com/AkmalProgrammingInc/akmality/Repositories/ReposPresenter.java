package com.AkmalProgrammingInc.akmality.Repositories;

import java.util.ArrayList;
import java.util.HashMap;

public class ReposPresenter implements ReposContract.IReposPresenter, ReposContract.IReposModel.OnReposLoadedCallback {
    ReposContract.IReposModel model;
    ReposContract.IReposView view;

    public ReposPresenter(ReposContract.IReposModel model, ReposContract.IReposView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void attachView(ReposContract.IReposView view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void loadRepos(String token) {
        model.getRepos(token, this);
    }

    @Override
    public void onReposResponse(ArrayList<HashMap<String, String>> data) {
        if (view != null){
            view.setData(data);
        }
    }

    @Override
    public void onReposFailure(String errorMessage) {
        if (view != null){
            view.showToast(errorMessage);
        }
    }
}
