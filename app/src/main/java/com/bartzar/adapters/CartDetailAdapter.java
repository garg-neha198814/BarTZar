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

package com.bartzar.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartzar.R;
import com.bartzar.beans.input.AddRemoveCartItemParams;
import com.bartzar.beans.input.IncDecCartItemQuantityParams;
import com.bartzar.beans.output.AddRemoveCartItemResponse;
import com.bartzar.beans.output.CartDetailResponse;
import com.bartzar.beans.output.IncDecCartItemQuantityResponse;
import com.bartzar.io.retrofit.APIHandler;
import com.bartzar.io.retrofit.APIResponseListenerListItem;
import com.bartzar.io.retrofit.APIs;
import com.bartzar.ui.user.fragments.CartDetail;
import com.bartzar.utility.Preferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by root on 3/6/16.
 */
public class CartDetailAdapter extends RecyclerView.Adapter<CartDetailAdapter.Holder> implements APIResponseListenerListItem {
    private Context mContext;
    private List<CartDetailResponse.ResponseBean.ResultBean.DataBean> mList;
    private CartDetail cartDetail;

    public CartDetailAdapter(Context context, List<CartDetailResponse.ResponseBean.ResultBean.DataBean> mList, CartDetail cartDetail) {
        this.mContext = context;
        this.mList = mList;
        this.cartDetail = cartDetail;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_my_order_cart, parent, false);
        Holder vh = new Holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CartDetailAdapter.Holder holder, int position) {
        CartDetailResponse.ResponseBean.ResultBean.DataBean model = mList.get(position);
        holder.pos = position;
        holder.itemTitle.setText(model.getMenu_item_name());
        holder.itemPrice.setText("AED " + model.getMenu_item_price());
        holder.itemQuantity.setText(model.getCart_item_counts());

        holder.itemTotalPrice.setText("AED " + model.getTotal_sub_item_price());
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        else
            return 0;
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int pos = -1;
        private TextView itemTitle, itemQuantity, itemPrice;
        private ImageView decreaseQuantity, increaseQuantity;
        private TextView deleteItem, itemTotalPrice;

        public Holder(View itemView) {
            super(itemView);
            itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            itemQuantity = (TextView) itemView.findViewById(R.id.itemQuantity);
            itemPrice = (TextView) itemView.findViewById(R.id.itemPrice);
            deleteItem = (TextView) itemView.findViewById(R.id.delete);
            itemTotalPrice = (TextView) itemView.findViewById(R.id.itemTotalPrice);
            decreaseQuantity = (ImageView) itemView.findViewById(R.id.decrease);
            increaseQuantity = (ImageView) itemView.findViewById(R.id.increase);

            decreaseQuantity.setOnClickListener(this);
            increaseQuantity.setOnClickListener(this);
            deleteItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.decrease:
                    if (mList.get(getAdapterPosition()).getCart_item_counts().equals("1")) {
                        deleteCartItem(mList.get(getAdapterPosition()).getMenu_item_id(), getAdapterPosition());
                    } else {
                        updateQuantity(mList.get(getAdapterPosition()).getCart_item_id(), APIs.dec_qty, getAdapterPosition());
                    }
                    break;
                case R.id.increase:
                    updateQuantity(mList.get(getAdapterPosition()).getCart_item_id(), APIs.inc_qty, getAdapterPosition());
                    break;
                case R.id.delete:
                    deleteCartItem(mList.get(getAdapterPosition()).getMenu_item_id(), getAdapterPosition());
                    break;
            }
        }
    }

    // function to hit the api for removing the cart item
    private void deleteCartItem(final String menu_item_id, final int adapterPosition) {
        // show alert dialog for confirmation
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.alertDialogTitle));
        builder.setMessage(mContext.getString(R.string.alertDialogMessage));
        builder.setNegativeButton(mContext.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton(mContext.getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                AddRemoveCartItemParams params = new AddRemoveCartItemParams();
                params.setUser_id(Preferences.getInstance(mContext).getUserId(mContext));
                params.setMenu_item_id(menu_item_id);
                params.setAdd_remove(APIs.removeFromCart);

                cartDetail.showProgressDialog();
                APIHandler.getInstance().add_remove_cart_item(params, CartDetailAdapter.this, adapterPosition);
            }
        });

        builder.create().show();
    }

    // function to hit the api for updating the quantity
    private void updateQuantity(String cart_item_id, String type, int adapterPosition) {
        IncDecCartItemQuantityParams params = new IncDecCartItemQuantityParams();
        params.setCart_item_id(cart_item_id);
        params.setInc_dec_item(type);
        params.setUser_id(Preferences.getInstance(mContext).getUserId(mContext));

        cartDetail.showProgressDialog();
        APIHandler.getInstance().in_dec_cart_item_qty(params, this, adapterPosition);
    }

    // function to remove the cart list item fom local list
    private void removeCartItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    // function to update the single list item total quantity and total price
    private void updateCartItem(int position, String qunatity_updated, String price_updated) {
        mList.get(position).setTotal_sub_item_price(price_updated);
        mList.get(position).setCart_item_counts(qunatity_updated);
        notifyItemChanged(position);
    }

    // function to update the list when the whole local list changes or the fragment is popped from back stack
    public void updateList(Context mContext, List<CartDetailResponse.ResponseBean.ResultBean.DataBean> mList) {
        this.mContext = mContext;
        this.mList = new ArrayList<>();
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }

    @Override
    public void onSuccess(Call<?> call, Response<?> response, String tag, int position) {
        try {
            cartDetail.hideProgressDialog();
            if (response.isSuccessful()) {
                switch (tag) {
                    case APIs.add_remove_cart_item:
                        AddRemoveCartItemResponse addRemoveCartItemResponse = ((AddRemoveCartItemResponse) response.body());
                        if (addRemoveCartItemResponse.getResponse().getCode() == 200) {
                            if (addRemoveCartItemResponse.getResponse().getResult().get(0).getAdd_remove().equals("0")) {
                                // cart item removed successfully
                                cartDetail.showOutput(addRemoveCartItemResponse.getResponse().getMessage());
                                removeCartItem(position);
                            }
                            // set the cart count and price
                            cartDetail.updateCartCountAndPrice(addRemoveCartItemResponse.getResponse().getResult().get(0).getCart_items_count(), addRemoveCartItemResponse.getResponse().getResult().get(0).getCart_price_total());
                        } else {
                            cartDetail.showCrouton(addRemoveCartItemResponse.getResponse().getMessage());
                        }
                        break;
                    case APIs.inc_dec_cart_item_qty:
                        IncDecCartItemQuantityResponse incDecCartItemQuantityResponse = (IncDecCartItemQuantityResponse) response.body();
                        if (incDecCartItemQuantityResponse.getResponse().getCode() == 200) {
                            if (incDecCartItemQuantityResponse.getResponse().getResult().get(0).getInc_dec().equals(APIs.inc_qty)) {
                                // cart item qyantity is increased
                                cartDetail.showOutput(incDecCartItemQuantityResponse.getResponse().getMessage());
                                updateCartItem(position, incDecCartItemQuantityResponse.getResponse().getResult().get(0).getQunatity_updated(), incDecCartItemQuantityResponse.getResponse().getResult().get(0).getPrice_updated());
                            } else if (incDecCartItemQuantityResponse.getResponse().getResult().get(0).getInc_dec().equals(APIs.dec_qty)) {
                                // cart item quantity is decreased
                                updateCartItem(position, incDecCartItemQuantityResponse.getResponse().getResult().get(0).getQunatity_updated(), incDecCartItemQuantityResponse.getResponse().getResult().get(0).getPrice_updated());
                            }
                            // set the cart count and price
                            cartDetail.updateCartCountAndPrice(incDecCartItemQuantityResponse.getResponse().getResult().get(0).getCart_items_count(), incDecCartItemQuantityResponse.getResponse().getResult().get(0).getCart_price_total());
                        } else {
                            cartDetail.showCrouton(incDecCartItemQuantityResponse.getResponse().getMessage());
                        }
                        break;
                }
            } else {
                cartDetail.showCrouton(response.errorBody().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<?> call, Throwable t, String tag, int position) {
        t.fillInStackTrace();
        cartDetail.hideProgressDialog();
        switch (tag) {
            default:
                cartDetail.showCrouton("Network connection error");
                break;
        }
    }
}
