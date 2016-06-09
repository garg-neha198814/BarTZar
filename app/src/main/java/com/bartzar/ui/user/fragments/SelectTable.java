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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bartzar.BaseFragment;
import com.bartzar.R;
import com.bartzar.ui.user.UserActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectTable extends BaseFragment implements View.OnClickListener {
    View rootView = null;
    private Spinner tableNumberSpinner;
    private Button nextButton;

    String mTableNumber = "";

    public SelectTable() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_select_table, container, false);
        initializeView();
        setToolbarTitle();
        return rootView;
    }

    private void setToolbarTitle() {
        ((UserActivity) getActivity()).setToolbarTitle(getString(R.string.selectTableTitle), true);
    }


    private void initializeView() {
        tableNumberSpinner = (Spinner) rootView.findViewById(R.id.tableNumber);
        nextButton = (Button) rootView.findViewById(R.id.next);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.selectTableNumbers, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        tableNumberSpinner.setAdapter(adapter);

        //set click listeners
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                mTableNumber = tableNumberSpinner.getSelectedItem().toString();
                if (TextUtils.isEmpty(mTableNumber)) {
                    showCrouton(rootView, "Please select table number");
                } else {
                    if (getActivity() instanceof UserActivity) {
                        ((UserActivity) getActivity()).showFragment(new MenuCategories(), getString(R.string.menuCategoryFragmentTag));
                    }
                }
                break;
        }
    }
}
