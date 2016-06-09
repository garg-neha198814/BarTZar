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
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartzar.R;
import com.bartzar.beans.output.MenuCategoryResponse;
import com.bartzar.ui.user.UserActivity;
import com.bartzar.ui.user.fragments.MenuSubCategories;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 1/6/16.
 */
public class MenuCategoriesAdapter extends RecyclerView.Adapter<MenuCategoriesAdapter.Holder> {
    private Context mContext;
    private List<MenuCategoryResponse.ResponseBean.ResultBean.DataBean> mList;

    public MenuCategoriesAdapter(Context context, List<MenuCategoryResponse.ResponseBean.ResultBean.DataBean> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_menu_category, parent, false);
        Holder vh = new Holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        MenuCategoryResponse.ResponseBean.ResultBean.DataBean model = mList.get(position);
        holder.pos = position;
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if ((bitmap != null) && (holder.pos == position))
                    holder.menuIcon.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                if ((errorDrawable != null) && (holder.pos == position))
                    holder.menuIcon.setImageDrawable(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if ((placeHolderDrawable != null) && (holder.pos == position))
                    holder.menuIcon.setImageDrawable(placeHolderDrawable);
            }
        };

        holder.menuIcon.setTag(target);

        if (!TextUtils.isEmpty(model.getImage_src())) {
            Picasso.with(mContext).load(model.getImage_src()).into(target);
        } else {
            holder.menuIcon.setImageDrawable(null);
        }

        holder.menuTitle.setText(model.getName());

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
        private ImageView menuIcon;
        private TextView menuTitle;

        public Holder(View itemView) {
            super(itemView);
            menuIcon = (ImageView) itemView.findViewById(R.id.menuIcon);
            menuTitle = (TextView) itemView.findViewById(R.id.menuTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext instanceof UserActivity) {
                        ((UserActivity) mContext).setMenuID(mList.get(getAdapterPosition()).getId());
                        ((UserActivity) mContext).setMenuTitle(mList.get(getAdapterPosition()).getName());
                        ((UserActivity) mContext).setSearch(false);
                        ((UserActivity) mContext).showFragment(new MenuSubCategories(), mContext.getString(R.string.menuSubCategoryFragmentTag));
                    }
                }
            });
        }
    }

    public void updateList(Context mContext, List<MenuCategoryResponse.ResponseBean.ResultBean.DataBean> mList) {
        this.mContext = mContext;
        this.mList = new ArrayList<>();
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }
}
