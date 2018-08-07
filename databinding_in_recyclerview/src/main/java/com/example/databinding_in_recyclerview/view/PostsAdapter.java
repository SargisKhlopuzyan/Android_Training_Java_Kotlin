package com.example.databinding_in_recyclerview.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.databinding_in_recyclerview.R;
import com.example.databinding_in_recyclerview.model.Post;
import com.example.databinding_in_recyclerview.databinding.PostRowItemBinding;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder>{

    public interface PostsAdapterListener {
        void onPostClicked(Post post);
    }


    private List<Post> postList;
    private LayoutInflater layoutInflater;
    private PostsAdapterListener listener;


    public PostsAdapter(List<Post> postList, PostsAdapterListener listener) {
        this.postList = postList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        PostRowItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.post_row_item, viewGroup, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.binding.setPost(postList.get(position));
        holder.binding.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onPostClicked(postList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final PostRowItemBinding binding;

        public MyViewHolder(PostRowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
