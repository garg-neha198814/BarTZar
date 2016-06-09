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

package com.bartzar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bartzar.interfaces.TryAgainInterface;
import com.bartzar.ui.user.UserActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by root on 30/5/16.
 */
public class BaseFragment extends Fragment {
    // function to display the short message in toast.
    protected void showToast(String message, int duration) {
        if (duration == 0) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }

    }

    // function to output the message
    protected void showOutput(String message) {
        System.out.println(">>>>>>>>>>>");
        System.out.println(message);
        System.out.println("<<<<<<<<<<<");
    }

    // function to check if internet connection is active or not.
    protected boolean isConnectingToInternet() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            return true;
        } else {
            return false;
        }
    }

    // function to check if the string is alpha numeric or not
    protected boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9]*$";
        if (s.matches(pattern)) {
            return true;
        }
        return false;
    }

    // function to displaySnackBar
    Snackbar snackbar = null;

    protected void showCrouton(View rootView, String message) {
        snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    protected void tryAgainCrouton(View rootView, String message, final TryAgainInterface callback, final String service) {
        snackbar = Snackbar
                .make(rootView, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callback.tryAgain(service);
                    }
                });

        snackbar.show();
    }

//    // bottom sheet dialog
//    View dialogView = null;
//    TextView messageTextView;
//    Button retry, cancel;
//    BottomSheetDialog bottomSheetDialog;
//
//    protected void showDialog(String message, final TryAgainInterface callback, final String service) {
//        if (dialogView == null) {
//            dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.retry_bottom_sheet, null);
//            messageTextView = (TextView) dialogView.findViewById(R.id.message);
//            retry = (Button) dialogView.findViewById(R.id.tryAgain);
//            cancel = (Button) dialogView.findViewById(R.id.cancel);
//        }
//
//        messageTextView.setText(message);
//        retry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheetDialog.dismiss();
//                callback.tryAgain(service);
//            }
//        });
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheetDialog.dismiss();
//            }
//        });
//        bottomSheetDialog = new BottomSheetDialog(getActivity());
//        bottomSheetDialog.setTitle("Hello");
//        bottomSheetDialog.setContentView(dialogView);
//        bottomSheetDialog.setCanceledOnTouchOutside(false);
//        bottomSheetDialog.setCancelable(false);
//        bottomSheetDialog.show();
//    }

    // function to parse the date
    protected String getDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getResources().getString(R.string.dateFormat), Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(getResources().getString(R.string.timeZone)));
            Date myDate = simpleDateFormat.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(myDate);
            date = cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH) + 1) + "." + cal.get(Calendar.YEAR) + " ";
            if (cal.get(Calendar.HOUR_OF_DAY) < 10)
                date += "0" + cal.get(Calendar.HOUR_OF_DAY);
            else
                date += cal.get(Calendar.HOUR_OF_DAY) + "";

            if (cal.get(Calendar.MINUTE) < 10)
                date += ":0" + cal.get(Calendar.MINUTE);
            else
                date += ":" + cal.get(Calendar.MINUTE);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    // function to show the progress dialog
    ProgressDialog pd;

    protected void showProgressDialog() {
        hideProgressDialog();
        pd = new ProgressDialog(getActivity(), R.style.progressDialogTheme);
        pd.setCancelable(false);
        pd.show();
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.progress_dialog, null);
        pd.setContentView(v);
    }

    // function to hide the progress dialog
    protected void hideProgressDialog() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }

    // function to gotoDashBoard after successful login, register
    protected void goToDashBoard() {
        startActivity(new Intent(getActivity(), UserActivity.class));
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (snackbar != null && snackbar.isShownOrQueued()) {
            snackbar.dismiss();
        }

//        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
//            bottomSheetDialog.dismiss();
//        }
    }
}
