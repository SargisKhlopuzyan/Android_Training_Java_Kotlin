package com.example.mvc_eventbus_retrofit.mvc_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvc_eventbus_retrofit.R;
import com.example.mvc_eventbus_retrofit.mvc_model.ItemPhoto;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemPhotoListAdapter extends ArrayAdapter<ItemPhoto> {

    private LayoutInflater mLayoutInflater;
    private int mResource;

    public ItemPhotoListAdapter(Context context, int resource, List<ItemPhoto> objects) {
        super(context, resource, objects);
        mLayoutInflater = LayoutInflater.from(context);
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mLayoutInflater.inflate(mResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        ItemPhoto itemPhoto = getItem(position);

        Picasso.get()
                .load(itemPhoto.getThumbnailUrl())
                .resize(100, 100)
                .centerCrop()
                .into(viewHolder.mProfileImageView);
        viewHolder.mTitleTextView.setText(itemPhoto.getTitle());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.profileImageView)
        ImageView mProfileImageView;
        @BindView(R.id.titleTextView)
        TextView mTitleTextView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}