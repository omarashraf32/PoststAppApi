package com.example.postsapi.data.source.remote;

import com.example.postsapi.data.model.CommentResponseItem;
import com.example.postsapi.data.model.PostResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {
    @GET("posts")
    Call<List<PostResponseItem>> getPosts();

    @GET("posts/{id}")
    Call<PostResponseItem> getpost(@Path("id") int id);

    @GET("comments")
    Call<List<CommentResponseItem>>getPostComment(@Query("postId") int postId);
}
