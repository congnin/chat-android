package com.congnin.chat.app.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.congnin.chat.app.R;
import com.congnin.chat.app.core.registration.RegisterContract;
import com.congnin.chat.app.core.registration.RegisterPresenter;
import com.congnin.chat.app.core.users.adduser.AddUserContract;
import com.congnin.chat.app.core.users.adduser.AddUserPresenter;
import com.congnin.chat.app.ui.activities.UserListActivity;
import com.congnin.chat.app.ui.adapters.UsersListingPagerAdapter;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by congnc on 4/4/17.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener,
        RegisterContract.View, AddUserContract.View {
    private static final String TAG = RegisterFragment.class.getSimpleName();

    private RegisterPresenter mRegisterPresenter;
    private AddUserPresenter mAddUserPresenter;

    private EditText mETxtEmail, mETxtPassword;
    private Button mBtnRegister;

    private ProgressDialog mProgressDialog;

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        bindViews(rootView);
        return rootView;
    }

    private void bindViews(View rootView) {
        mETxtEmail = (EditText) rootView.findViewById(R.id.edit_text_email_id);
        mETxtPassword = (EditText) rootView.findViewById(R.id.edit_text_password);
        mBtnRegister = (Button) rootView.findViewById(R.id.button_register);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        mRegisterPresenter = new RegisterPresenter(this);
        mAddUserPresenter = new AddUserPresenter(this);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.button_register:
                onRegister(v);
                break;
        }
    }

    private void onRegister(View v) {
        String emailId = mETxtEmail.getText().toString();
        String password = mETxtPassword.getText().toString();

        mRegisterPresenter.register(getActivity(), emailId, password);
        mProgressDialog.show();
    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        mProgressDialog.setMessage(getString(R.string.adding_user_to_db));
        Toast.makeText(getActivity(), "Registration Successful!", Toast.LENGTH_SHORT).show();
        mAddUserPresenter.addUser(getActivity().getApplicationContext(), firebaseUser);
    }

    @Override
    public void onRegistrationFailure(String message) {
        mProgressDialog.dismiss();
        mProgressDialog.setMessage(getString(R.string.please_wait));
        Log.e(TAG, "onRegistrationFailure: " + message);
        Toast.makeText(getActivity(), "Registration failed!+\n" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddUserSuccess(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        UserListActivity.startActivity(getActivity(),
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    public void onAddUserFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
