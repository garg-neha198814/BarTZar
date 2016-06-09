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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import com.bartzar.BaseActivity;
import com.bartzar.R;
import com.bartzar.ui.Fragments.Login;
import com.bartzar.ui.Fragments.Register;
import com.bartzar.utility.Preferences;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragment(new Login(), getString(R.string.loginFragmentTag));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            oneStepBack();
        }
        return false;
    }

    @Override
    protected void onResume() {
        showOutput("OnResumed Main called");
        if ((isStateSaved && !isNewIntent)) {
            String tag = Preferences.getInstance(this).getFragmentTag(this);
            if (tag.equals(getString(R.string.loginFragmentTag))) {
                showFragment(new Login(), getString(R.string.loginFragmentTag));
            } else if (tag.equals(getString(R.string.registerFragmentTag))) {
                showFragment(new Register(), getString(R.string.registerFragmentTag));
            }
        }
        super.onResume();
    }

    @Override
    public void showFragment(Fragment name, String tag) {
        super.showFragment(name, tag);
    }

    @Override
    public void showFragment(Fragment name, String tag, Bundle bundle) {
        super.showFragment(name, tag, bundle);
    }
}
