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


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bartzar.BaseFragment;
import com.bartzar.R;
import com.bartzar.beans.input.RegisterParams;
import com.bartzar.beans.output.LoginResponse;
import com.bartzar.io.retrofit.APIHandler;
import com.bartzar.io.retrofit.APIResponseListener;
import com.bartzar.io.retrofit.APIs;
import com.bartzar.ui.MainActivity;
import com.bartzar.utility.Preferences;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends BaseFragment implements View.OnClickListener, APIResponseListener {
    View rootView = null;
    private EditText usernameEditText, passwordEditText, confirmPasswordEditText, emailEditText;
    private Button registerButton;
    private TextView loginTextView;

    private String mUsername = "", mPassword = "", mConfirmPassword = "", mEmail = "";

    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_register, container, false);
        initializeView();
        return rootView;
    }

    private void initializeView() {
        usernameEditText = (EditText) rootView.findViewById(R.id.username);
        passwordEditText = (EditText) rootView.findViewById(R.id.password);
        confirmPasswordEditText = (EditText) rootView.findViewById(R.id.confirmPassword);
        emailEditText = (EditText) rootView.findViewById(R.id.email);
        registerButton = (Button) rootView.findViewById(R.id.register);
        loginTextView = (TextView) rootView.findViewById(R.id.login);

        //set click listeners
        registerButton.setOnClickListener(this);
        loginTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                mUsername = usernameEditText.getText().toString().trim();
                mPassword = passwordEditText.getText().toString();
                mConfirmPassword = confirmPasswordEditText.getText().toString();
                mEmail = emailEditText.getText().toString().trim();

                if (TextUtils.isEmpty(mUsername) && TextUtils.isEmpty(mPassword) && TextUtils.isEmpty(mConfirmPassword) && TextUtils.isEmpty(mEmail)) {
                    showCrouton(rootView, "Please enter the details");
                } else if (TextUtils.isEmpty(mUsername)) {
                    showCrouton(rootView, "Please enter username");
                } else if (TextUtils.isEmpty(mPassword)) {
                    showCrouton(rootView, "Please enter password");
                } else if (mPassword.length() < 6 || mPassword.length() > 12) {
                    showCrouton(rootView, "Password length must be 6 to 12 characters");
                } else if (!mPassword.equals(mConfirmPassword)) {
                    showCrouton(rootView, "Password not matched");
                } else if (!(Patterns.EMAIL_ADDRESS.matcher(mEmail).matches())) {
                    showCrouton(rootView, "Please enter valid email");
                } else {
                    // hit the api
                    RegisterParams registerParams = new RegisterParams();
                    registerParams.setPassword(mPassword);
                    registerParams.setEmail(mEmail);
                    registerParams.setUsername(mUsername);

                    showProgressDialog();
                    APIHandler.getInstance().register(registerParams, this);
                }
                break;
            case R.id.login:
                if (getActivity() instanceof MainActivity)
                    ((MainActivity) getActivity()).showFragment(new Login(), getString(R.string.loginFragmentTag));
                break;
        }
    }

    @Override
    public void onSuccess(Call<?> call, Response<?> response, String tag) {
        try {
            hideProgressDialog();
            if (response.isSuccessful()) {
                switch (tag) {
                    case APIs.register:
                        LoginResponse loginResponse = ((LoginResponse) response.body());
                        if (loginResponse.getResponse().getCode() == 200) {
                            // registration successful
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
}
