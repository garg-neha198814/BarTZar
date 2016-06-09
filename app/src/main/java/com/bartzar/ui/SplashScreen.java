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

package com.bartzar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.bartzar.BaseActivity;
import com.bartzar.R;
import com.bartzar.ui.user.UserActivity;
import com.bartzar.utility.Preferences;

/* Created By deepak on May 30, 2016  */
public class SplashScreen extends BaseActivity {
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        getFacebookHashKey();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (timer != null)
            timer.cancel();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (timer != null)
            timer.cancel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        showOutput("start called");
        timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (Preferences.getInstance(SplashScreen.this).checkUserSession(SplashScreen.this)) {
                    startActivity(new Intent(getApplicationContext(), UserActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        };
        timer.start();
    }
}
