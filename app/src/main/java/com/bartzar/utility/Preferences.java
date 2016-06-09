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

package com.bartzar.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.bartzar.R;

/**
 * Created by deepakgoyal on 24/02/16.
 */
public class Preferences {
    private static SharedPreferences sp;
    private static Preferences preference;

    public static Preferences getInstance(Context ctx) {
        if (preference == null) {
            preference = new Preferences();
        }
        if (sp == null) {
            sp = ctx.getSharedPreferences(ctx.getResources().getString(R.string.preferencesFile), Context.MODE_PRIVATE);
        }
        return preference;
    }

    private Preferences() {

    }

    // store the user id to shared preferences
    public void storeUserId(Context context, String id) {
        sp.edit().putString(context.getString(R.string.user_id), id).commit();
    }

    // get user id from shared preferences
    public String getUserId(Context context) {
        return sp.getString(context.getString(R.string.user_id), "");
    }

    // check if user id exists or not
    public boolean checkUserSession(Context context) {
        return sp.contains(context.getString(R.string.user_id));
    }

    // function to clear all the shared preferences when user logout.
    public void clearPreferences() {
        sp.edit().clear().commit();
    }

    public String getStringValue(String key) {
        return sp.getString(key, "");
    }

    public void setStringValue(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public String getFragmentTag(Context context) {
        return sp.getString(context.getString(R.string.fragment), "");
    }

    public void storeFragmentTag(Context context, String tag) {
        sp.edit().putString(context.getString(R.string.fragment), tag).apply();
    }
}
