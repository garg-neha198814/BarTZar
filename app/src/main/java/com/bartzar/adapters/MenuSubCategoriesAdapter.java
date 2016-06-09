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
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartzar.R;
import com.bartzar.beans.input.AddRemoveCartItemParams;
import com.bartzar.beans.output.AddRemoveCartItemResponse;
import com.bartzar.beans.output.MenuSubCategoryResponse;
import com.bartzar.io.retrofit.APIHandler;
import com.bartzar.io.retrofit.APIResponseListenerListItem;
import com.bartzar.io.retrofit.APIs;
import com.bartzar.ui.user.fragments.MenuSubCategories;
import com.bartzar.utility.Preferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by root on 1/6/16.
 */
public class MenuSubCategoriesAdapter extends RecyclerView.Adapter<MenuSubCategoriesAdapter.Holder> implements APIResponseListenerListItem {
    private Context mContext;
    private List<MenuSubCategoryResponse.ResponseBean.ResultBean.DataBean> mList;
    private MenuSubCategories menuSubCategories;

    public MenuSubCategoriesAdapter(Context context, List<MenuSubCategoryResponse.ResponseBean.ResultBean.DataBean> mList, MenuSubCategories menuSubCategories) {
        this.mContext = context;
        this.mList = mList;
        this.menuSubCategories = menuSubCategories;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_menu_sub_category, parent, false);
        Holder vh = new Holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.pos = position;
        MenuSubCategoryResponse.ResponseBean.ResultBean.DataBean model = mList.get(position);

        holder.itemTitle.setText(model.getName());
        holder.itemQuantity.setText(model.getQuantity());
        holder.itemPrice.setText("AED " + model.getPrice());

        if (model.getIs_cart_item() == 0) {
            holder.itemCart.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.not_in_cart));
        } else {
            holder.itemCart.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.in_cart));
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        else
            return 0;
    }

    class Holder extends RecyclerView.ViewHolder {
        private int pos = -1;
        private TextView itemTitle, itemQuantity, itemPrice;
        private ImageView itemCart;

        public Holder(View itemView) {
            super(itemView);
            itemTitle = (TextView) itemView.findViewById(R.id.title);
            itemQuantity = (TextView) itemView.findViewById(R.id.quantity);
            itemPrice = (TextView) itemView.findViewById(R.id.price);
            itemCart = (ImageView) itemView.findViewById(R.id.cart);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // add/remove the item on cart
                    if (mList.get(getAdapterPosition()).getIs_cart_item() == 0) {
                        // add to cart
                        addRemoveCartItem(mList.get(getAdapterPosition()).getId(), APIs.addToCart, getAdapterPosition());
                    } else {
                        // remove from cart
                        addRemoveCartItem(mList.get(getAdapterPosition()).getId(), APIs.removeFromCart, getAdapterPosition());
                    }
                }
            });
        }
    }

    public void updateList(Context mContext, List<MenuSubCategoryResponse.ResponseBean.ResultBean.DataBean> mList) {
        this.mContext = mContext;
        this.mList = new ArrayList<>();
        this.mList.addAll(mList);
    }

    private void addRemoveCartItem(String menuId, String type, int position) {
        AddRemoveCartItemParams params = new AddRemoveCartItemParams();
        params.setAdd_remove(type);
        params.setMenu_item_id(menuId);
        params.setUser_id(Preferences.getInstance(mContext).getUserId(mContext));

        menuSubCategories.showProgressDialog();
        APIHandler.getInstance().add_remove_cart_item(params, this, position);
    }

    @Override
    public void onSuccess(Call<?> call, Response<?> response, String tag, int position) {
        try {
            menuSubCategories.hideProgressDialog();
            if (response.isSuccessful()) {
                switch (tag) {
                    case APIs.add_remove_cart_item:
                        AddRemoveCartItemResponse addRemoveCartItemResponse = ((AddRemoveCartItemResponse) response.body());
                        if (addRemoveCartItemResponse.getResponse().getCode() == 200) {
                            if (addRemoveCartItemResponse.getResponse().getResult().get(0).getAdd_remove().equals("0")) {
                                // cart item removed successfully
                                menuSubCategories.showOutput(addRemoveCartItemResponse.getResponse().getMessage());
                                updateListItem(0, position);
                            } else {
                                // cart item added successfully
                                menuSubCategories.showOutput(addRemoveCartItemResponse.getResponse().getMessage());
                                updateListItem(1, position);
                            }

                            // set the cart count and price
                            menuSubCategories.updateCartCountAndPrice(addRemoveCartItemResponse.getResponse().getResult().get(0).getCart_items_count(), addRemoveCartItemResponse.getResponse().getResult().get(0).getCart_price_total());
                        } else {
                            menuSubCategories.showCrouton(addRemoveCartItemResponse.getResponse().getMessage());
                        }
                        break;
                }
            } else {
                menuSubCategories.showCrouton(response.errorBody().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateListItem(int i, int position) {
        mList.get(position).setIs_cart_item(i);
        notifyItemChanged(position);
    }

    @Override
    public void onFailure(Call<?> call, Throwable t, String tag, int position) {
        t.fillInStackTrace();
        menuSubCategories.hideProgressDialog();
        switch (tag) {
            default:
                menuSubCategories.showCrouton("Network connection error");
                break;
        }
    }

}
