package com.AkmalProgrammingInc.akmality.GitHubAuth;

public class AuthPresenter implements AuthContract.IAuthPresenter, AuthContract.IGithubModel.GetAccesTokenCallback {
    private AuthContract.IAuthView view;
    private AuthContract.IGithubModel model;

    private static final String CLIENT_ID = "2f769efe129ea881bd67";
    private final String CLIENT_SECRET = "c237cc2cde9d834a5315e034e42fc212912ae87e";
    private final String REDIRECT_URI = "akmal://callback";

    public AuthPresenter(AuthContract.IGithubModel model) {
        this.model = model;
    }

    @Override
    public void attachView(AuthContract.IAuthView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onLoginClicked() {
        String token = view.getToken();
        if (token == null){
            view.authorizeUser(CLIENT_ID, REDIRECT_URI);
        }else{
            view.showMainActivity(token);
        }

    }

    @Override
    public void authSuccess(String code) {
        model.getAccessToken(CLIENT_ID, CLIENT_SECRET, code, this);
    }

    @Override
    public void authError() {
        view.showError();
    }

    @Override
    public String getRedirectUrl() {
        return REDIRECT_URI;
    }


    @Override
    public void onGetTokenSuccess(String token) {
        if (view != null){
            view.saveToken(token);
            view.showMainActivity(token);
        }
    }

    @Override
    public void onGetTokenFailure(String errorMessage) {
        view.showToast(errorMessage);
    }
}
