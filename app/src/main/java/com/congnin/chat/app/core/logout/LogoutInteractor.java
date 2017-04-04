package com.congnin.chat.app.core.logout;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by congnc on 4/4/17.
 */

public class LogoutInteractor implements LogoutContract.Interactor {
    private LogoutContract.OnLogoutListener mOnLogoutListener;

    public LogoutInteractor(LogoutContract.OnLogoutListener onLogoutListener) {
        this.mOnLogoutListener = onLogoutListener;
    }

    @Override
    public void performFirebaseLayout() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
            mOnLogoutListener.onSuccess("Successfully logged out!");
        } else {
            mOnLogoutListener.onFailure("No user logged in yet!");
        }
    }
}
