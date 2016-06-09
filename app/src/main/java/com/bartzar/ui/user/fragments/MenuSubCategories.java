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
import android.widget.TextView;

import com.bartzar.BaseFragment;
import com.bartzar.R;
import com.bartzar.adapters.MenuSubCategoriesAdapter;
import com.bartzar.beans.input.MenuSubCategoryParams;
import com.bartzar.beans.output.MenuSubCategoryResponse;
import com.bartzar.interfaces.TryAgainInterface;
import com.bartzar.io.retrofit.APIHandler;
import com.bartzar.io.retrofit.APIResponseListener;
import com.bartzar.io.retrofit.APIs;
import com.bartzar.recyclerViewDecorations.MarginDecoration;
import com.bartzar.ui.user.UserActivity;
import com.bartzar.utility.Preferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuSubCategories extends BaseFragment implements View.OnClickListener, APIResponseListener, TryAgainInterface {
    View rootView = null;
    private Context mContext = null;
    private EditText searchEditText;
    private RelativeLayout searchLayout;
    private RecyclerView menuRecyclerView;
    private TextView cartCountTextView, moneyCounTextView;
    private Button reviewAndOrderButton;

    private String menuTitle = "", menuID = "";


    private MenuSubCategoriesAdapter adapter = null;
    private List<MenuSubCategoryResponse.ResponseBean.ResultBean.DataBean> mList;

    public MenuSubCategories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_menu_sub_categories, container, false);

        mContext = getActivity();
        initializeView();
        return rootView;
    }

    private void setToolbarTitle(String title) {
        ((UserActivity) mContext).setToolbarTitle(title, true);
    }


    private void initializeView() {
        searchEditText = (EditText) rootView.findViewById(R.id.searchField);
        searchLayout = (RelativeLayout) rootView.findViewById(R.id.searchIcon);
        menuRecyclerView = (RecyclerView) rootView.findViewById(R.id.menuList);
        cartCountTextView = (TextView) rootView.findViewById(R.id.cartCount);
        moneyCounTextView = (TextView) rootView.findViewById(R.id.moneyCount);
        reviewAndOrderButton = (Button) rootView.findViewById(R.id.reviewAndOrder);

        // set click listeners
        searchLayout.setOnClickListener(this);
        reviewAndOrderButton.setOnClickListener(this);

        // set Recycler view
        menuRecyclerView.addItemDecoration(new MarginDecoration(mContext));
        menuRecyclerView.setHasFixedSize(true);
        menuRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        // get the selected menu title and id
        if (mContext instanceof UserActivity) {
            if (!((UserActivity) mContext).isSearch()) {
                menuTitle = ((UserActivity) mContext).getMenuTitle();
                menuID = ((UserActivity) mContext).getMenuID();

                // set the title
                setToolbarTitle(menuTitle);

                // hit the api to load sub categories
                loadMenuSubCategories();
            } else {
                // add logic for searching
            }
        }
    }

    private void loadMenuSubCategories() {
        MenuSubCategoryParams params = new MenuSubCategoryParams();
        params.setUser_id(Preferences.getInstance(mContext).getUserId(mContext));
        params.setMenu_cat_id(menuID);
        showProgressDialog();
        APIHandler.getInstance().get_menu_sub_categories(params, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchIcon:

                break;
            case R.id.reviewAndOrder:
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
                    case APIs.menu_sub_categories:
                        MenuSubCategoryResponse menuSubCategoryResponse = ((MenuSubCategoryResponse) response.body());
                        if (menuSubCategoryResponse.getResponse().getCode() == 200) {
                            showOutput(menuSubCategoryResponse.getResponse().getMessage());

                            // set the cart count and price
                            cartCountTextView.setText(menuSubCategoryResponse.getResponse().getResult().get(0).getCart_items_count() + "");
                            moneyCounTextView.setText(menuSubCategoryResponse.getResponse().getResult().get(0).getTotal_cart_price() + "");
                            // fill the adapter
                            mList = menuSubCategoryResponse.getResponse().getResult().get(0).getData();
                            if (adapter == null) {
                                adapter = new MenuSubCategoriesAdapter(mContext, mList, MenuSubCategories.this);
                                menuRecyclerView.setAdapter(adapter);
                            } else {
                                adapter.updateList(mContext, mList);
                                menuRecyclerView.setAdapter(adapter);
                            }

                        } else {
                            showCrouton(rootView, menuSubCategoryResponse.getResponse().getMessage());
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
        hideProgressDialog();
        switch (tag) {
            case APIs.menu_sub_categories:
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
            case APIs.menu_sub_categories:
                loadMenuSubCategories();
                break;
        }
    }

    @Override
    public void showProgressDialog() {
        super.showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        super.hideProgressDialog();
    }

    @Override
    public void showOutput(String message) {
        super.showOutput(message);
    }

    public void showCrouton(String message) {
        super.showCrouton(rootView, message);
    }

    public void updateCartCountAndPrice(int cart_items_count, int cart_price_total) {
        cartCountTextView.setText(cart_items_count + "");
        moneyCounTextView.setText(cart_price_total + "");
    }
}