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

package com.bartzar.ui.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartzar.BaseActivity;
import com.bartzar.R;
import com.bartzar.ui.user.fragments.MenuCategories;
import com.bartzar.ui.user.fragments.SelectTable;
import com.bartzar.ui.user.fragments.UserDashboard;
import com.bartzar.utility.Preferences;

public class UserActivity extends BaseActivity {
    private Toolbar mToolbar;
    private TextView toolbarTitle;
    private ImageView backImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        inflateToolbar();

        showFragment(new UserDashboard(), getString(R.string.userDashboardFragmentTag));
    }

    private void inflateToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        backImageView = (ImageView) mToolbar.findViewById(R.id.back);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneStepBack();
            }
        });

        setToolbarTitle("", false);
    }

    // function to set the title of toolbar
    public void setToolbarTitle(String title, boolean isBackButton) {
        toolbarTitle.setText(title);
        if (isBackButton)
            backImageView.setVisibility(View.VISIBLE);
        else
            backImageView.setVisibility(View.GONE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            oneStepBack();
//            String tag = getSupportFragmentManager().findFragmentById(R.id.fragment_container).getTag();
//            if (tag.equals(getString(R.string.userDashboardFragmentTag))) {
//                doubleClickToExit();
//            } else if (tag.equals(getString(R.string.selectTableFragmentTag))) {
//                showFragment(new UserDashboard(), getString(R.string.userDashboardFragmentTag));
//            }
        }
        return false;
    }

    @Override
    protected void onResume() {
        if ((isStateSaved && !isNewIntent)) {
            String tag = Preferences.getInstance(this).getFragmentTag(this);
            if (tag.equals(getString(R.string.userDashboardFragmentTag))) {
                showFragment(new UserDashboard(), getString(R.string.userDashboardFragmentTag));
            } else if (tag.equals(getString(R.string.selectTableFragmentTag))) {
                showFragment(new SelectTable(), getString(R.string.selectTableFragmentTag));
            } else if (tag.equals(getString(R.string.menuCategoryFragmentTag))) {
                showFragment(new MenuCategories(), getString(R.string.menuCategoryFragmentTag));
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

    // store the menu item and title, so that we can use it in menu sub category fragment.
    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public boolean isSearch() {
        return isSearch;
    }

    public void setSearch(boolean search) {
        isSearch = search;
    }

    private String menuID = "";
    private String menuTitle = "";
    private boolean isSearch = false;

}
