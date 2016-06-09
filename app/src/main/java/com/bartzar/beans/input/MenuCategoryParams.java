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
public class MenuCategoryParams {
    private String method = APIs.menu_categories;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private String user_id = "";

}
