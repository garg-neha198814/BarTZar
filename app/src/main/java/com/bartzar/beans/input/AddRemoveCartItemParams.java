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
 * Created by root on 1/6/16.
 */
public class AddRemoveCartItemParams {
    private String method = APIs.add_remove_cart_item;
    private String menu_item_id = "";
    private String user_id = "";

    public void setAdd_remove(String add_remove) {
        this.add_remove = add_remove;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setMenu_item_id(String menu_item_id) {
        this.menu_item_id = menu_item_id;
    }

    private String add_remove = "";
}
