/*
 *
 *  *
 *  *  * Copyright (c) 2016, Mobilyte Tech India Pvt. Ltd. and/or its affiliates. All rights reserved.
 *  *  *
 *  *  * Redistribution and use in source and binary forms, with or without
 *  *  * modification, are permitted provided that the following conditions are met:
 *  *  *
 *  *  *  - Redistributions of source code must retain the above copyright
 *  *  *    notice, this list of conditions and the following disclaimer.
 *  *  *
 *  *  *  - Redistributions in binary form must reproduce the above copyright
 *  *  *    notice, this list of conditions and the following disclaimer in the
 *  *  *    documentation and/or other materials provided with the distribution.
 *  *
 *
 */

package com.bartzar.ui.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bartzar.BaseFragment;
import com.bartzar.R;
import com.bartzar.beans.input.FbLoginParams;
import com.bartzar.beans.input.LoginParams;
import com.bartzar.beans.output.LoginResponse;
import com.bartzar.facebook.FbLogin;
import com.bartzar.facebook.FbResult;
import com.bartzar.io.retrofit.APIHandler;
import com.bartzar.io.retrofit.APIResponseListener;
import com.bartzar.io.retrofit.APIs;
import com.bartzar.ui.MainActivity;
import com.bartzar.utility.Preferences;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends BaseFragment implements View.OnClickListener, FbResult, APIResponseListener {
    View rootView = null;
    private EditText userNameEditText, passwordEditText;
    private TextView forgotPasswordTextView;
    private Button loginButton;
    private LinearLayout facebookLayout;
    private TextView newUserTextView;

    private String mUsername = "", mPassword = "";

    private FbLogin fblogin;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initializeViews();
        return rootView;
    }

    private void initializeViews() {
        userNameEditText = (EditText) rootView.findViewById(R.id.username);
        passwordEditText = (EditText) rootView.findViewById(R.id.password);
        forgotPasswordTextView = (TextView) rootView.findViewById(R.id.forgotPassword);
        loginButton = (Button) rootView.findViewById(R.id.login);
        facebookLayout = (LinearLayout) rootView.findViewById(R.id.facebook);
        newUserTextView = (TextView) rootView.findViewById(R.id.register);

        // set click listeners
        forgotPasswordTextView.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        facebookLayout.setOnClickListener(this);
        newUserTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forgotPassword:
                showCrouton(rootView, "Pending to implement");
                break;
            case R.id.login:
                mUsername = userNameEditText.getText().toString().trim();
                mPassword = passwordEditText.getText().toString();
                if (TextUtils.isEmpty(mUsername) && TextUtils.isEmpty(mPassword)) {
                    showCrouton(rootView, "Please enter the details");
                } else if (TextUtils.isEmpty(mUsername)) {
                    showCrouton(rootView, "Please enter username/Email");
                } else if (TextUtils.isEmpty(mPassword)) {
                    showCrouton(rootView, "Please enter password");
                } else {
                    // hit the api
                    showProgressDialog();
                    LoginParams params = new LoginParams();
                    params.setUsername_email(mUsername);
                    params.setPassword(mPassword);

                    APIHandler.getInstance().login(params, this);
                }
                break;
            case R.id.facebook:
                FacebookSdk.sdkInitialize(getActivity());
                fblogin = new FbLogin(getActivity(), this);
                break;
            case R.id.register:
                if (getActivity() instanceof MainActivity)
                    ((MainActivity) getActivity()).showFragment(new Register(), getString(R.string.registerFragmentTag));
                break;
        }
    }

    @Override
    public void onLoginError(FacebookException e) {
        e.printStackTrace();
        showCrouton(rootView, e.getLocalizedMessage());
    }

    @Override
    public void onLoginSuccess(LoginResult result) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                try {
                    com.facebook.Profile profile = com.facebook.Profile.getCurrentProfile();
                    // hit the api

                    showProgressDialog();
                    FbLoginParams params = new FbLoginParams();
                    params.setFacebook_id(profile.getId());

                    APIHandler.getInstance().fb_login(params, Login.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        graphRequest.executeAsync();
    }

    @Override
    public void onLoginCancel() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (fblogin != null) {
            fblogin.OnActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onSuccess(Call<?> call, Response<?> response, String tag) {
        try {
            hideProgressDialog();
            if (response.isSuccessful()) {
                switch (tag) {
                    case APIs.login:
                    case APIs.fb_login:
                        LoginResponse loginResponse = ((LoginResponse) response.body());
                        if (loginResponse.getResponse().getCode() == 200) {
                            // login successful
                            // store the data
                            showOutput(loginResponse.getResponse().getMessage());
                            Preferences.getInstance(getActivity()).storeUserId(getActivity(), loginResponse.getResponse().getResult().get(0).getData().getId());
                            Preferences.getInstance(getActivity()).setStringValue(getString(R.string.user_email), loginResponse.getResponse().getResult().get(0).getData().getEmail());
                            Preferences.getInstance(getActivity()).setStringValue(getString(R.string.user_username), loginResponse.getResponse().getResult().get(0).getData().getUsername());

                            // go to dashboard
                            goToDashBoard();
                        } else {
                            showCrouton(rootView, loginResponse.getResponse().getMessage());
                        }
                        break;
                }
            } else {
                showCrouton(rootView, response.errorBody().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<?> call, Throwable t, String tag) {
        t.fillInStackTrace();
        hideProgressDialog();
        switch (tag) {
            default:
                showCrouton(rootView, "Network connection error");
                break;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showOutput("login view created");
    }
}
