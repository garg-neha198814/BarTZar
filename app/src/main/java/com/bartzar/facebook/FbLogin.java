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
package com.bartzar.facebook;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.widget.ShareDialog;

import java.util.Arrays;


/**
 * Created by Ripansharma
 */
public class FbLogin {
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    FbResult fbResult;

    public FbLogin(Activity context, FbResult callback) {
        fbResult = callback;
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("LoginActivity", "onSuccess Facebook Login" + loginResult);
//                if(loginResult.getAccessToken().getPermissions().contains("publish_actions"))
                fbResult.onLoginSuccess(loginResult);
//                else
//                    LoginManager.getInstance().logInWithPublishPermissions(context, Arrays.asList("publish_actions"));
            }

            @Override
            public void onCancel() {
                fbResult.onLoginCancel();
            }

            @Override
            public void onError(FacebookException e) {
                Log.e("LoginActivity", "ERROR: " + e.getMessage());
                fbResult.onLoginError(e);
            }
        });
        facebookLogin(context);
    }

    public void facebookLogin(Activity context) {
        LoginManager.getInstance().logInWithReadPermissions(context, Arrays.asList("public_profile", "email"));
    }

    public void OnActivityResult(int requestCode, int resultCode, Intent data) {
        if (callbackManager != null)
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
