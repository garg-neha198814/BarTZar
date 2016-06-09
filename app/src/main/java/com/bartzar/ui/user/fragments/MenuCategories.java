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


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.bartzar.BaseFragment;
import com.bartzar.R;
import com.bartzar.adapters.MenuCategoriesAdapter;
import com.bartzar.beans.input.MenuCategoryParams;
import com.bartzar.beans.output.MenuCategoryResponse;
import com.bartzar.interfaces.TryAgainInterface;
import com.bartzar.io.retrofit.APIHandler;
import com.bartzar.io.retrofit.APIResponseListener;
import com.bartzar.io.retrofit.APIs;
import com.bartzar.recyclerViewDecorations.MarginDecoration;
import com.bartzar.ui.user.UserActivity;
import com.bartzar.utility.Preferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuCategories extends BaseFragment implements View.OnClickListener, APIResponseListener, TryAgainInterface {
    View rootView = null;
    private Context mContext = null;
    private EditText searchEditText;
    private RelativeLayout searchLayout;
    private RecyclerView menuRecyclerView;
    private Button fastReorderButton, cartCountButton;
    private MenuCategoriesAdapter adapter = null;
    private List<MenuCategoryResponse.ResponseBean.ResultBean.DataBean> mList;

    public MenuCategories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_menu_categories, container, false);

        mContext = getActivity();
        initializeView();
        setToolbarTitle();

        return rootView;
    }

    private void setToolbarTitle() {
        ((UserActivity) mContext).setToolbarTitle(getString(R.string.menuCategoryTitle), true);
    }


    private void initializeView() {
        searchEditText = (EditText) rootView.findViewById(R.id.searchField);
        searchLayout = (RelativeLayout) rootView.findViewById(R.id.searchIcon);
        menuRecyclerView = (RecyclerView) rootView.findViewById(R.id.menuList);
        fastReorderButton = (Button) rootView.findViewById(R.id.fastReorder);
        cartCountButton = (Button) rootView.findViewById(R.id.cartCount);

        // set click listeners
        searchLayout.setOnClickListener(this);
        fastReorderButton.setOnClickListener(this);
        cartCountButton.setOnClickListener(this);

        // set Recycler view
        menuRecyclerView.addItemDecoration(new MarginDecoration(mContext));
        menuRecyclerView.setHasFixedSize(true);
        menuRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mList = new ArrayList<>();
        adapter = new MenuCategoriesAdapter(mContext, mList);
        menuRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // hit the api
        mContext = getActivity();
        loadMenuCategories();
    }

    private void loadMenuCategories() {
        MenuCategoryParams params = new MenuCategoryParams();
        params.setUser_id(Preferences.getInstance(mContext).getUserId(mContext));
        showProgressDialog();
        APIHandler.getInstance().get_menu_categories(params, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchIcon:

                break;
            case R.id.fastReorder:

                break;
            case R.id.cartCount:
                if (mContext instanceof UserActivity)
                    ((UserActivity) mContext).showFragment(new CartDetail(), getString(R.string.cartDetailFragmentTag));
                break;
        }
    }

    @Override
    public void onSuccess(Call<?> call, Response<?> response, String tag) {
        try {
            hideProgressDialog();
            if (response.isSuccessful()) {
                switch (tag) {
                    case APIs.menu_categories:
                        MenuCategoryResponse menuCategoryResponse = ((MenuCategoryResponse) response.body());
                        if (menuCategoryResponse.getResponse().getCode() == 200) {
                            showOutput(menuCategoryResponse.getResponse().getMessage());

                            // set the cart count
                            cartCountButton.setText(menuCategoryResponse.getResponse().getResult().get(0).getCart_items_count() + "");
                            // fill the adapter
                            mList = menuCategoryResponse.getResponse().getResult().get(0).getData();
                            if (adapter == null) {
                                adapter = new MenuCategoriesAdapter(mContext, mList);
                                menuRecyclerView.setAdapter(adapter);
                            } else {
                                adapter.updateList(mContext, mList);
                            }

                        } else {
                            showCrouton(rootView, menuCategoryResponse.getResponse().getMessage());
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
        t.printStackTrace();
        t.fillInStackTrace();
        hideProgressDialog();
        switch (tag) {
            case APIs.menu_categories:
//                showDialog("Network connection error.", this, tag);
                tryAgainCrouton(rootView, "Network connection error", this, tag);
                break;
            default:
                showCrouton(rootView, "Network connection error");
                break;
        }
    }

    @Override
    public void tryAgain(String service) {
        switch (service) {
            case APIs.menu_categories:
                loadMenuCategories();
                break;
        }
    }
}
