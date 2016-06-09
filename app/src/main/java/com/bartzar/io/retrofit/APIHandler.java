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

package com.bartzar.io.retrofit;

import com.bartzar.beans.input.AddRemoveCartItemParams;
import com.bartzar.beans.input.CartDetailParams;
import com.bartzar.beans.input.FbLoginParams;
import com.bartzar.beans.input.IncDecCartItemQuantityParams;
import com.bartzar.beans.input.LoginParams;
import com.bartzar.beans.input.MenuCategoryParams;
import com.bartzar.beans.input.MenuSubCategoryParams;
import com.bartzar.beans.input.RegisterParams;
import com.bartzar.beans.output.AddRemoveCartItemResponse;
import com.bartzar.beans.output.CartDetailResponse;
import com.bartzar.beans.output.IncDecCartItemQuantityResponse;
import com.bartzar.beans.output.LoginResponse;
import com.bartzar.beans.output.MenuCategoryResponse;
import com.bartzar.beans.output.MenuSubCategoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by deepakgoyal on 21/3/16.
 */
public class APIHandler {

    private static APIHandler instance;
    private Retrofit retrofit;
    private APIs handler; // Interface where all API methods are getting called

    private APIHandler() {
        retrofit = new Retrofit.Builder()
                .baseUrl(APIs.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        handler = retrofit.create(APIs.class);
    }

    public static APIHandler getInstance() {
        if (instance == null) {
            instance = new APIHandler();
        }
        return instance;
    }

    public void login(LoginParams params, final APIResponseListener callback) {
        handler.login(params).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                callback.onSuccess(call, response, APIs.login);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onFailure(call, t, APIs.login);
            }
        });
    }

    public void fb_login(FbLoginParams params, final APIResponseListener callback) {
        handler.fb_login(params).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                callback.onSuccess(call, response, APIs.fb_login);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onFailure(call, t, APIs.fb_login);
            }
        });
    }

    public void register(RegisterParams params, final APIResponseListener callback) {
        handler.register(params).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                callback.onSuccess(call, response, APIs.register);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onFailure(call, t, APIs.register);
            }
        });
    }

    public void get_menu_categories(MenuCategoryParams params, final APIResponseListener callback) {
        handler.get_menu_categories(params).enqueue(new Callback<MenuCategoryResponse>() {
            @Override
            public void onResponse(Call<MenuCategoryResponse> call, Response<MenuCategoryResponse> response) {
                callback.onSuccess(call, response, APIs.menu_categories);
            }

            @Override
            public void onFailure(Call<MenuCategoryResponse> call, Throwable t) {
                callback.onFailure(call, t, APIs.menu_categories);
            }
        });
    }

    public void get_menu_sub_categories(MenuSubCategoryParams params, final APIResponseListener callback) {
        handler.get_menu_sub_categories(params).enqueue(new Callback<MenuSubCategoryResponse>() {
            @Override
            public void onResponse(Call<MenuSubCategoryResponse> call, Response<MenuSubCategoryResponse> response) {
                callback.onSuccess(call, response, APIs.menu_sub_categories);
            }

            @Override
            public void onFailure(Call<MenuSubCategoryResponse> call, Throwable t) {
                callback.onFailure(call, t, APIs.menu_sub_categories);
            }
        });
    }

    public void add_remove_cart_item(AddRemoveCartItemParams params, final APIResponseListenerListItem callback, final int position) {
        handler.add_remove_cart_item(params).enqueue(new Callback<AddRemoveCartItemResponse>() {
            @Override
            public void onResponse(Call<AddRemoveCartItemResponse> call, Response<AddRemoveCartItemResponse> response) {
                callback.onSuccess(call, response, APIs.add_remove_cart_item, position);
            }

            @Override
            public void onFailure(Call<AddRemoveCartItemResponse> call, Throwable t) {
                callback.onFailure(call, t, APIs.add_remove_cart_item, position);
            }
        });
    }

    public void get_cart_detail(CartDetailParams params, final APIResponseListener callback) {
        handler.get_cart_detail(params).enqueue(new Callback<CartDetailResponse>() {
            @Override
            public void onResponse(Call<CartDetailResponse> call, Response<CartDetailResponse> response) {
                callback.onSuccess(call, response, APIs.get_cart_detail);
            }

            @Override
            public void onFailure(Call<CartDetailResponse> call, Throwable t) {
                callback.onFailure(call, t, APIs.get_cart_detail);
            }
        });
    }

    public void in_dec_cart_item_qty(IncDecCartItemQuantityParams params, final APIResponseListenerListItem callback, final int position) {
        handler.inc_dec_cart_item_qty(params).enqueue(new Callback<IncDecCartItemQuantityResponse>() {
            @Override
            public void onResponse(Call<IncDecCartItemQuantityResponse> call, Response<IncDecCartItemQuantityResponse> response) {
                callback.onSuccess(call, response, APIs.inc_dec_cart_item_qty, position);
            }

            @Override
            public void onFailure(Call<IncDecCartItemQuantityResponse> call, Throwable t) {
                callback.onFailure(call, t, APIs.inc_dec_cart_item_qty, position);
            }
        });
    }
}
