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

package com.bartzar.beans.input;

import com.bartzar.io.retrofit.APIs;

/**
 * Created by root on 3/6/16.
 */
public class IncDecCartItemQuantityParams {

    /**
     * method : inc_dec_cart_item
     * cart_item_id : 10
     * inc_dec_item : inc
     * user_id : 4
     */

    private String method = APIs.inc_dec_cart_item_qty;
    private String cart_item_id = "";
    private String inc_dec_item = "";
    private String user_id = "";

    public void setCart_item_id(String cart_item_id) {
        this.cart_item_id = cart_item_id;
    }

    public void setInc_dec_item(String inc_dec_item) {
        this.inc_dec_item = inc_dec_item;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
