package com.congnin.chat.app.core.logout;

import com.congnin.chat.app.core.logout.LogoutContract.OnLogoutListener;

/**
 * Created by congnc on 4/4/17.
 */

public class LogoutPresenter implements LogoutContract.Presenter, OnLogoutListener {
    private LogoutContract.View mLogoutView;
    private LogoutInteractor mLogoutInteractor;

    public LogoutPresenter(LogoutContract.View logoutView) {
        mLogoutView = logoutView;
        mLogoutInteractor = new LogoutInteractor(this);
    }

    @Override
    public void logout() {
        mLogoutInteractor.performFirebaseLayout();
    }

    @Override
    public void onSuccess(String message) {
        mLogoutView.onLogoutSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        mLogoutView.onLogoutFailure(message);
    }
}
