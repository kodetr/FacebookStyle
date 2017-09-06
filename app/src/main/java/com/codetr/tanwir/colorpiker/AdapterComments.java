/*
 * Copyright (c) 2016. Tanwir. All Rights Reserver.
 * <p>
 * Save to the extent permitted by law, you may not use,copy,modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Tanwir.
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package com.codetr.tanwir.colorpiker;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanwir on 06/09/2017.
 */
public class AdapterComments extends BaseAdapter {

    private CommentsAll activity;
    private List<Comments> lComments;

    public AdapterComments(CommentsAll activity) {
        this.activity = activity;
        lComments = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return lComments.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addComments(Comments mComments) {
        lComments.add(mComments);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Comments setComments = lComments.get(position);

        ViewHolder mHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comments, parent, false);
            mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.tvName.setText(setComments.getName());
        mHolder.tvCode.setText(setComments.getCode());

        mHolder.ldisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copy",setComments.getCode() );
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(activity, "Copy", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    public Comments getComents(int position) {
        return lComments.get(position);
    }

    public class ViewHolder {
        TextView tvName;
        TextView tvCode;
        LinearLayout ldisable;

        public ViewHolder(View view) {
            ldisable = (LinearLayout) view.findViewById(R.id.ldisable);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvCode = (TextView) view.findViewById(R.id.tvCode);
        }
    }
}
