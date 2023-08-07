package com.example.postsapi.ui.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postsapi.data.model.PostResponseItem;
import com.example.postsapi.databinding.ItemPostLayoutBinding;

import java.util.List;

public class  PostsAdpater extends RecyclerView.Adapter<PostsAdpater.PostHolder> {

    private List<PostResponseItem>posts;
    private postAction postAction;
   public PostsAdpater(postAction postAction){
        this.postAction =postAction;
    }

    public void addposts(List<PostResponseItem>posts){
        this.posts=posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostLayoutBinding   binding =
                ItemPostLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new  PostHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        PostResponseItem post =posts.get(position);
        holder.binding.txtPostId.setText(String.valueOf(post.getId()));
        holder.binding.txtPostTitile.setText(post.getTitle());

    }

    @Override
    public int getItemCount() {
        if (posts !=null)
            return posts.size();
        return 0;
    }


     class PostHolder extends RecyclerView.ViewHolder{

        ItemPostLayoutBinding binding;
        public PostHolder(@NonNull ItemPostLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postAction.postClick(posts.get(getLayoutPosition()));
                }
            });
        }
    }
   public interface postAction{

        public void postClick(PostResponseItem post);
    }
}
