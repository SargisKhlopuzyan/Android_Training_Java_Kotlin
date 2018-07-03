package com.example.sargiskh.android_training_java_kotlin.retrofit.java;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sargiskh.android_training_java_kotlin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Java extends RecyclerView.Adapter<Adapter_Java.ViewHolder_Java> {

    private Context context;
    private List<Category> dataList;

    public Adapter_Java(Context context, List<Category> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder_Java onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new ViewHolder_Java(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Java holder, int position) {
        holder.textViewTitle.setText(dataList.get(position).getTitle());

        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.downloader(new OkHttp3Downloader(context));
        builder.downloader(new com.squareup.picasso.OkHttp3Downloader(context));
        builder.build()
                .load(dataList.get(position).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageViewCover);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder_Java extends RecyclerView.ViewHolder {

        public final View mView;

        private TextView textViewTitle;
        private ImageView imageViewCover;

        public ViewHolder_Java(View itemView) {
            super(itemView);
            mView = itemView;

            textViewTitle = mView.findViewById(R.id.title);
            imageViewCover = mView.findViewById(R.id.imageViewCover);
        }
    }

}
