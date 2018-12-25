package com.AkmalProgrammingInc.akmality.MainActivity;

import java.util.HashMap;

public class MainPresenter implements MainActivityContract.IMainPresenter, MainActivityContract.IMainModel.UserInfoCallback {
    private MainActivityContract.IMainModel model;
    private MainActivityContract.IMainView view;

    public MainPresenter(MainActivityContract.IMainModel model, MainActivityContract.IMainView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void loadUserInfo(String token) {
        model.getUserInfo(token, this);
    }


    @Override
    public void onUserInfoResponse(HashMap<String, String> user) {
        if (view != null){
            view.setUserInfo(user.get("login"), user.get("url"), user.get("avatar_url"));
        }
    }

    @Override
    public void onUserInfoFailure(String errorMessage) {
        if (view != null){
            view.showToast(errorMessage);
        }

    }
}
