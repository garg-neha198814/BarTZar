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
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by deepakgoyal on 21/3/16.
 */
public interface APIs {

    String BASE_URL = "http://bartzarrest.betasoftdev.com/rest/";
    String login = "login_user";
    String fb_login = "login_user_fb";
    String register = "register_user";
    String menu_categories = "get_menus_categories";
    String menu_sub_categories = "get_menus_items";
    String add_remove_cart_item = "add_remove_cart_item";
    String addToCart = "add";
    String removeFromCart = "remove";
    String get_cart_detail = "get_cart_detail";
    String inc_dec_cart_item_qty = "inc_dec_cart_item";
    String inc_qty = "inc";
    String dec_qty = "dec";

    @Headers("Content-Type: application/json")
    @POST("rest_api.php")
    Call<LoginResponse> login(@Body LoginParams params);

    @Headers("Content-Type: application/json")
    @POST("rest_api.php")
    Call<LoginResponse> fb_login(@Body FbLoginParams params);

    @Headers("Content-Type: application/json")
    @POST("rest_api.php")
    Call<LoginResponse> register(@Body RegisterParams params);

    @Headers("Content-Type: application/json")
    @POST("rest_api.php")
    Call<MenuCategoryResponse> get_menu_categories(@Body MenuCategoryParams params);

    @Headers("Content-Type: application/json")
    @POST("rest_api.php")
    Call<MenuSubCategoryResponse> get_menu_sub_categories(@Body MenuSubCategoryParams params);

    @Headers("Content-Type: application/json")
    @POST("rest_api.php")
    Call<AddRemoveCartItemResponse> add_remove_cart_item(@Body AddRemoveCartItemParams params);

    @Headers("Content-Type: application/json")
    @POST("rest_api.php")
    Call<CartDetailResponse> get_cart_detail(@Body CartDetailParams params);

    @Headers("Content-Type: application/json")
    @POST("rest_api.php")
    Call<IncDecCartItemQuantityResponse> inc_dec_cart_item_qty(@Body IncDecCartItemQuantityParams params);
}