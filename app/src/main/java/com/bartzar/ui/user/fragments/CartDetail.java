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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bartzar.BaseFragment;
import com.bartzar.R;
import com.bartzar.adapters.CartDetailAdapter;
import com.bartzar.beans.input.CartDetailParams;
import com.bartzar.beans.output.CartDetailResponse;
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
public class CartDetail extends BaseFragment implements View.OnClickListener, APIResponseListener, TryAgainInterface {
    View rootView = null;
    private Context mContext = null;
    private RecyclerView cartList;
    private TextView cartCountTextView, moneyCounTextView;
    private EditText noteEditText;
    private Button orderNowButton, scheduleOrderButton;
    private CartDetailAdapter adapter = null;
    private List<CartDetailResponse.ResponseBean.ResultBean.DataBean> mList;

    public CartDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cart_detail, container, false);

        mContext = getActivity();
        initializeView();
        setToolbarTitle();
        return rootView;
    }

    private void setToolbarTitle() {
        ((UserActivity) mContext).setToolbarTitle(getString(R.string.cartDetailTitle), true);
    }


    private void initializeView() {
        cartList = (RecyclerView) rootView.findViewById(R.id.cartList);
        cartCountTextView = (TextView) rootView.findViewById(R.id.cartCount);
        moneyCounTextView = (TextView) rootView.findViewById(R.id.moneyCount);
        noteEditText = (EditText) rootView.findViewById(R.id.note);
        orderNowButton = (Button) rootView.findViewById(R.id.orderNow);
        scheduleOrderButton = (Button) rootView.findViewById(R.id.schedule);

        // set click listeners
        orderNowButton.setOnClickListener(this);
        scheduleOrderButton.setOnClickListener(this);

        // set Recycler view
        cartList.addItemDecoration(new MarginDecoration(mContext));
        cartList.setHasFixedSize(true);
        cartList.setLayoutManager(new LinearLayoutManager(mContext));
        mList = new ArrayList<>();
        adapter = new CartDetailAdapter(mContext, mList, this);
        cartList.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // hit the api
        mContext = getActivity();
        loadCartDetailList();
    }

    private void loadCartDetailList() {
        CartDetailParams params = new CartDetailParams();
        params.setUser_id(Preferences.getInstance(mContext).getUserId(mContext));
        showProgressDialog();
        APIHandler.getInstance().get_cart_detail(params, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.orderNow:

                break;
            case R.id.schedule:

                break;
        }
    }

    @Override
    public void onSuccess(Call<?> call, Response<?> response, String tag) {
        try {
            hideProgressDialog();
            if (response.isSuccessful()) {
                switch (tag) {
                    case APIs.get_cart_detail:
                        CartDetailResponse cartDetailResponse = ((CartDetailResponse) response.body());
                        if (cartDetailResponse.getResponse().getCode() == 200) {
                            showOutput(cartDetailResponse.getResponse().getMessage());

                            // set the cart count
                            cartCountTextView.setText(cartDetailResponse.getResponse().getResult().get(0).getCart_items_count() + "");
                            moneyCounTextView.setText(cartDetailResponse.getResponse().getResult().get(0).getCart_price_total() + "");
                            // fill the adapter
                            mList = cartDetailResponse.getResponse().getResult().get(0).getData();
                            if (adapter == null) {
                                adapter = new CartDetailAdapter(mContext, mList, CartDetail.this);
                                cartList.setAdapter(adapter);
                            } else {
                                adapter.updateList(mContext, mList);
                            }

                        } else {
                            showCrouton(rootView, cartDetailResponse.getResponse().getMessage());
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
            case APIs.get_cart_detail:
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
            case APIs.get_cart_detail:
                loadCartDetailList();
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
