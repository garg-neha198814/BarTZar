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

package com.bartzar.ui.user.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bartzar.BaseFragment;
import com.bartzar.R;
import com.bartzar.ui.user.UserActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDashboard extends BaseFragment implements View.OnClickListener {
    View rootView = null;
    private Button orderNowButton, eventsCalendarButton, myOrdersButton, reservationButton;

    public UserDashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_dashboard, container, false);
        initializeView();
        setToolbarTitle();
        return rootView;
    }

    private void setToolbarTitle() {
        ((UserActivity) getActivity()).setToolbarTitle(getString(R.string.userDashboardTitle), false);
    }

    private void initializeView() {
        orderNowButton = (Button) rootView.findViewById(R.id.orderNow);
        eventsCalendarButton = (Button) rootView.findViewById(R.id.eventsCalendar);
        myOrdersButton = (Button) rootView.findViewById(R.id.myOrders);
        reservationButton = (Button) rootView.findViewById(R.id.reservation);

        // set click listeners
        orderNowButton.setOnClickListener(this);
        eventsCalendarButton.setOnClickListener(this);
        myOrdersButton.setOnClickListener(this);
        reservationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.orderNow:
                if (getActivity() instanceof UserActivity) {
                    ((UserActivity) getActivity()).showFragment(new SelectTable(), getString(R.string.selectTableFragmentTag));
                }
                break;
            case R.id.eventsCalendar:

                break;
            case R.id.myOrders:

                break;
            case R.id.reservation:

                break;
        }
    }
}
